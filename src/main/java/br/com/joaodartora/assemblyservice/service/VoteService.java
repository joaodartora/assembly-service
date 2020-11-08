package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.AgendaResultsDto;
import br.com.joaodartora.assemblyservice.dto.VoteDto;
import br.com.joaodartora.assemblyservice.dto.VoteResultsDto;
import br.com.joaodartora.assemblyservice.mapper.AgendaResultsMapper;
import br.com.joaodartora.assemblyservice.mapper.VoteMapper;
import br.com.joaodartora.assemblyservice.mapper.VoteResultsMapper;
import br.com.joaodartora.assemblyservice.repository.VoteRepository;
import br.com.joaodartora.assemblyservice.repository.entity.SessionEntity;
import br.com.joaodartora.assemblyservice.repository.entity.VoteEntity;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;
import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);
    private final SessionService sessionService;
    private final VoteRepository voteRepository;
    private final ObjectMapper objectMapper;

    public VoteService(SessionService sessionService, VoteRepository voteRepository, ObjectMapper objectMapper) {
        this.sessionService = sessionService;
        this.voteRepository = voteRepository;
        this.objectMapper = objectMapper;
    }

    public Long vote(Long agendaId, VoteDto voteDto) {
        sessionService.getOpenSession(agendaId);
        VoteEntity voteEntity = VoteMapper.build(agendaId, voteDto);
        return voteRepository.save(voteEntity).getId(); // TODO: 08/11/2020 tratar erro de voto duplicado
    }
    public AgendaResultsDto getResult(Long agendaId) {
        SessionEntity sessionEntity = sessionService.getClosedSession(agendaId);
        VoteResultsDto voteResults = getVoteResults(agendaId);
        VotesResultEnum result = defineAgendaResult(voteResults);
        sessionService.saveSessionResult(sessionEntity, result);
        // TODO: 08/11/2020 enviar evento de finalização de contagem
        return AgendaResultsMapper.build(result, voteResults);
    }

    private VoteResultsDto getVoteResults(Long agendaId) {
        List<VoteEntity> totalVotes = voteRepository.findAllByAgendaId(agendaId);
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
