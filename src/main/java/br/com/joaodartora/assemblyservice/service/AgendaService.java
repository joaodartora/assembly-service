package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.AgendaDto;
import br.com.joaodartora.assemblyservice.exception.AgendaNotFoundException;
import br.com.joaodartora.assemblyservice.repository.AgendaRepository;
import br.com.joaodartora.assemblyservice.repository.entity.AgendaEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

    private final AgendaRepository agendaRepository;
    private final ObjectMapper objectMapper;

    public AgendaService(AgendaRepository agendaRepository, ObjectMapper objectMapper) {
        this.agendaRepository = agendaRepository;
        this.objectMapper = objectMapper;
    }

    public Long create(AgendaDto agendaDto) {
        AgendaEntity entity = objectMapper.convertValue(agendaDto, AgendaEntity.class);
        return agendaRepository.save(entity)
                .getId();
    }

    public AgendaDto get(Long id) {
        return agendaRepository.findById(id)
                .map(agendaEntity -> objectMapper.convertValue(agendaEntity, AgendaDto.class))
                .orElseThrow(AgendaNotFoundException::new);
    }
}
