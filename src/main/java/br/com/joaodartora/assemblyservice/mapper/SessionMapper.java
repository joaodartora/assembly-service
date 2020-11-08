package br.com.joaodartora.assemblyservice.mapper;

import br.com.joaodartora.assemblyservice.repository.entity.SessionEntity;

import java.time.LocalDateTime;

public class SessionMapper {

    public static SessionEntity buildEntity(Long agendaId, LocalDateTime startTime, LocalDateTime endTime) {
        return SessionEntity.with()
                .agendaId(agendaId)
                .startTime(startTime)
                .endTime(endTime)
                .build();
    }
}
