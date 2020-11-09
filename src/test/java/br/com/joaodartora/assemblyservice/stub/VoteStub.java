package br.com.joaodartora.assemblyservice.stub;

import br.com.joaodartora.assemblyservice.dto.VoteDto;
import br.com.joaodartora.assemblyservice.repository.entity.VoteEntity;
import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;

import java.util.Arrays;
import java.util.List;

public class VoteStub {

    public static VoteDto createDto() {
        VoteDto dto = new VoteDto();
        dto.setVote(VoteChoiceEnum.YES);
        dto.setAssociated(AssociatedStub.createDto());
        return dto;
    }

    public static VoteEntity createEntityWithVote(VoteChoiceEnum vote) {
        return VoteEntity.with()
                .id(1L)
                .agendaId(3131L)
                .vote(vote)
                .associatedId(31081998L)
                .associatedCpf("24440570019")
                .build();
    }

    public static List<VoteEntity> createEntitiesListWithYesWinning() {
        return Arrays.asList(createEntityWithVote(VoteChoiceEnum.YES),
                createEntityWithVote(VoteChoiceEnum.YES),
                createEntityWithVote(VoteChoiceEnum.NO));
    }

    public static List<VoteEntity> createEntitiesListWithNoWinning() {
        return Arrays.asList(createEntityWithVote(VoteChoiceEnum.NO),
                createEntityWithVote(VoteChoiceEnum.NO),
                createEntityWithVote(VoteChoiceEnum.YES));
    }

    public static List<VoteEntity> createEntitiesListWithDraw() {
        return Arrays.asList(createEntityWithVote(VoteChoiceEnum.NO),
                createEntityWithVote(VoteChoiceEnum.YES));
    }
}
