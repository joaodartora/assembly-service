package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.AgendaResultsDto;
import br.com.joaodartora.assemblyservice.dto.SessionDto;
import br.com.joaodartora.assemblyservice.dto.VoteDto;
import br.com.joaodartora.assemblyservice.dto.VoteResultsDto;
import br.com.joaodartora.assemblyservice.exception.AssociatedAlreadyVotedException;
import br.com.joaodartora.assemblyservice.exception.NoVotesFoundException;
import br.com.joaodartora.assemblyservice.mapper.AgendaResultsMapper;
import br.com.joaodartora.assemblyservice.mapper.VoteMapper;
import br.com.joaodartora.assemblyservice.mapper.VoteResultsMapper;
import br.com.joaodartora.assemblyservice.producer.AgendaResultProducer;
import br.com.joaodartora.assemblyservice.repository.VoteRepository;
import br.com.joaodartora.assemblyservice.repository.entity.VoteEntity;
import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);
    private final SessionService sessionService;
    private final VoteRepository voteRepository;
    private final AssociatedService associatedService;
    private final AgendaResultProducer agendaResultProducer;

    public VoteService(SessionService sessionService, VoteRepository voteRepository, AssociatedService associatedService, AgendaResultProducer agendaResultProducer) {
        this.sessionService = sessionService;
        this.voteRepository = voteRepository;
        this.associatedService = associatedService;
        this.agendaResultProducer = agendaResultProducer;
    }

    public Long vote(Long agendaId, VoteDto voteDto) {
        associatedService.verifyAssociatedPermissionToVote(voteDto.getAssociated().getCpf());
        sessionService.validateOpenSession(agendaId);
        VoteEntity voteEntity = VoteMapper.build(agendaId, voteDto);
        try {
            return voteRepository.save(voteEntity).getId();
        } catch (DataIntegrityViolationException exception) {
            LOGGER.error("Error when trying to save vote, associated already voted", exception);
            throw new AssociatedAlreadyVotedException();
        }
    }

    public AgendaResultsDto getResult(Long agendaId) {
        SessionDto session = sessionService.getClosedSession(agendaId);
        VoteResultsDto voteResults = getVoteResults(agendaId);
        VotesResultEnum result = defineAgendaResult(voteResults);
        sessionService.saveSessionResult(session, result);
        agendaResultProducer.sendAgendaResultEvent(AgendaResultsMapper.buildEvent(session, voteResults, result));
        return AgendaResultsMapper.buildDto(result, voteResults);
    }

    private VoteResultsDto getVoteResults(Long agendaId) {
        List<VoteEntity> totalVotes = voteRepository.findAllByAgendaId(agendaId);
        if (CollectionUtils.isEmpty(totalVotes))
            throw new NoVotesFoundException();

        Long yesVotes = totalVotes.stream()
                .filter(voteEntity -> VoteChoiceEnum.YES == voteEntity.getVote())
                .count();
        Long noVotes = totalVotes.size() - yesVotes;
        return VoteResultsMapper.build((long) totalVotes.size(), yesVotes, noVotes);
    }

    private VotesResultEnum defineAgendaResult(VoteResultsDto voteResults) {
        if (voteResults.getYesVotes().equals(voteResults.getNoVotes()))
            return VotesResultEnum.DRAW;
        return voteResults.getYesVotes() > voteResults.getNoVotes()
                ? VotesResultEnum.YES
                : VotesResultEnum.NO;
    }
}
