package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.AgendaDto;
import br.com.joaodartora.assemblyservice.repository.AgendaRepository;
import br.com.joaodartora.assemblyservice.repository.entity.AgendaEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AgendaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaService.class);
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
                .orElse(null);
        // TODO: 07/11/2020 Tratar orElse com exception
    }


}
