package br.com.joaodartora.assemblyservice.mapper;

import br.com.joaodartora.assemblyservice.dto.SessionDto;
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

    public static SessionEntity buildEntityFromDto(SessionDto dto) {
        return SessionEntity.with()
                .id(dto.getId())
                .agendaId(dto.getAgendaId())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .result(dto.getResult())
                .build();
    }

    public static SessionDto buildDtoFromEntity(SessionEntity entity) {
        return SessionDto.with()
                .id(entity.getId())
                .agendaId(entity.getAgendaId())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .result(entity.getResult())
                .build();
    }
}
