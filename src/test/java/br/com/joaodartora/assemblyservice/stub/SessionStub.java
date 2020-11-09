package br.com.joaodartora.assemblyservice.stub;

import br.com.joaodartora.assemblyservice.dto.SessionDto;
import br.com.joaodartora.assemblyservice.repository.entity.SessionEntity;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;

import java.time.LocalDateTime;

public class SessionStub {

    public static SessionDto createDto() {
        return SessionDto.with()
                .id(1L)
                .agendaId(3131L)
                .startTime(LocalDateTime.parse("2020-11-08T09:19:37.474372"))
                .endTime(LocalDateTime.parse("2020-11-08T09:49:37.474372"))
                .build();
    }

    public static SessionEntity createEntity() {
        return SessionEntity.with()
                .id(1L)
                .agendaId(3131L)
                .startTime(LocalDateTime.parse("2020-11-08T09:19:37.474372"))
                .endTime(LocalDateTime.parse("2020-11-08T09:49:37.474372"))
                .build();
    }

    public static SessionEntity createEntityWithResult() {
        SessionEntity entity = createEntity();
        entity.setResult(VotesResultEnum.DRAW);
        return entity;
    }
}
