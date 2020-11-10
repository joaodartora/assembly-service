package br.com.joaodartora.assemblyservice.mapper;

import br.com.joaodartora.assemblyservice.dto.AgendaResultsDto;
import br.com.joaodartora.assemblyservice.dto.SessionDto;
import br.com.joaodartora.assemblyservice.dto.VoteResultsDto;
import br.com.joaodartora.assemblyservice.producer.event.AgendaResultsEvent;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;

public class AgendaResultsMapper {

    public static AgendaResultsDto buildDto(VotesResultEnum result, VoteResultsDto voteResults) {
        return AgendaResultsDto.with()
                .result(result)
                .totalVotes(voteResults.getTotalVotes())
                .yesVotes(voteResults.getYesVotes())
                .noVotes(voteResults.getNoVotes())
                .build();
    }

    public static AgendaResultsEvent buildEvent(SessionDto session, VoteResultsDto voteResults, VotesResultEnum votesResult) {
        return AgendaResultsEvent.with()
                .agendaId(session.getAgendaId())
                .sessionId(session.getId())
                .result(votesResult)
                .totalVotes(voteResults.getTotalVotes())
                .yesVotes(voteResults.getYesVotes())
                .noVotes(voteResults.getNoVotes())
                .build();
    }

}
