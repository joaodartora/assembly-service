package br.com.joaodartora.assemblyservice.stub;

import br.com.joaodartora.assemblyservice.dto.AgendaResultsDto;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;

public class AgendaResultsStub {

    public static AgendaResultsDto createDto(){
        return AgendaResultsDto.with()
                .totalVotes(2L)
                .yesVotes(1L)
                .noVotes(1L)
                .result(VotesResultEnum.DRAW)
                .build();
    }

}
