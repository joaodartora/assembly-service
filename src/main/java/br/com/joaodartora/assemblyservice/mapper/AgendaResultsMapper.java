package br.com.joaodartora.assemblyservice.mapper;

import br.com.joaodartora.assemblyservice.dto.AgendaResultsDto;
import br.com.joaodartora.assemblyservice.dto.VoteResultsDto;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;

public class AgendaResultsMapper {

    public static AgendaResultsDto build(VotesResultEnum result, VoteResultsDto voteResults) {
        return AgendaResultsDto.with()
                .result(result)
                .totalVotes(voteResults.getTotalVotes())
                .yesVotes(voteResults.getYesVotes())
                .noVotes(voteResults.getNoVotes())
                .build();
    }

}
