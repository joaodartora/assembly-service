package br.com.joaodartora.assemblyservice.mapper;

import br.com.joaodartora.assemblyservice.dto.VoteDto;
import br.com.joaodartora.assemblyservice.repository.entity.VoteEntity;

public class VoteMapper {

    public static VoteEntity build(Long agendaId, VoteDto voteDto) {
        return VoteEntity.with()
                .agendaId(agendaId)
                .vote(voteDto.getVote())
                .associatedCpf(voteDto.getAssociated().getCpf())
                .associatedId(voteDto.getAssociated().getId())
                .build();
    }

}
