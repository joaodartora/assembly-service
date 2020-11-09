package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.AgendaDto;
import br.com.joaodartora.assemblyservice.exception.AgendaNotFoundException;
import br.com.joaodartora.assemblyservice.repository.AgendaRepository;
import br.com.joaodartora.assemblyservice.repository.entity.AgendaEntity;
import br.com.joaodartora.assemblyservice.stub.AgendaStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class AgendaServiceTest {

    private final AgendaService agendaService;
    private final AgendaRepository agendaRepository;
    private final ObjectMapper objectMapper;

    public AgendaServiceTest() {
        this.agendaRepository = mock(AgendaRepository.class);
        this.objectMapper = spy(ObjectMapper.class);
        this.agendaService = new AgendaService(agendaRepository, objectMapper);
    }

    @Test
    void createAgendaWithSuccessShouldReturnNewAgendaId() {
        when(agendaRepository.save(any(AgendaEntity.class))).thenReturn(AgendaStub.createEntity());

        Long result = agendaService.create(new AgendaDto("First Agenda"));

        assertEquals(1L, result);
    }

    @Test
    void getAgendaWithSuccessShouldReturnFindedAgenda() {
        when(agendaRepository.findById(1L)).thenReturn(Optional.of(AgendaStub.createEntity()));

        AgendaDto result = agendaService.get(1L);

        assertAll("Assert result fields",
                () -> assertEquals("First Agenda", result.getDescription()),
                () -> assertEquals(1L, result.getId()));
    }

    @Test
    void getAgendaWithoutResultFromDatabaseShouldThrowAgendaNotFoundException() {
        when(agendaRepository.findById(1L)).thenReturn(Optional.empty());

        AgendaNotFoundException exception = assertThrows(AgendaNotFoundException.class, () -> agendaService.get(1L));

        assertEquals("error.agendaNotFound", exception.getMessage());
    }
}
