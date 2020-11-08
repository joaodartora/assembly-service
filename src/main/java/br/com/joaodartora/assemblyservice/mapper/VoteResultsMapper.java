package br.com.joaodartora.assemblyservice.mapper;

import br.com.joaodartora.assemblyservice.dto.VoteResultsDto;

public class VoteResultsMapper {

    public static VoteResultsDto build(Long totalVotes, Long yesVotes, Long noVotes) {
        return VoteResultsDto.with()
                .totalVotes(totalVotes)
                .yesVotes(yesVotes)
                .noVotes(noVotes)
                .build();
    }
}
