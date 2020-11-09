package br.com.joaodartora.assemblyservice.stub;

import br.com.joaodartora.assemblyservice.dto.AgendaDto;
import br.com.joaodartora.assemblyservice.repository.entity.AgendaEntity;

public class AgendaStub {

    public static AgendaEntity createEntity() {
        AgendaEntity entity = new AgendaEntity();
        entity.setId(1L);
        entity.setDescription("First Agenda");
        return entity;
    }

    public static AgendaDto createDto() {
        AgendaDto dto = new AgendaDto();
        dto.setId(1L);
        dto.setDescription("First Agenda");
        return dto;
    }
}
