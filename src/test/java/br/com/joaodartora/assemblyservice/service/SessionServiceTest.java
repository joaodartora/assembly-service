package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.SessionDto;
import br.com.joaodartora.assemblyservice.exception.SessionAlreadyClosedException;
import br.com.joaodartora.assemblyservice.exception.SessionAlreadyOpenException;
import br.com.joaodartora.assemblyservice.exception.SessionNotFoundException;
import br.com.joaodartora.assemblyservice.repository.SessionRepository;
import br.com.joaodartora.assemblyservice.repository.entity.SessionEntity;
import br.com.joaodartora.assemblyservice.stub.AgendaStub;
import br.com.joaodartora.assemblyservice.stub.SessionStub;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SessionServiceTest {

    private final SessionService sessionService;
    private final AgendaService agendaService;
    private final SessionRepository sessionRepository;

    public SessionServiceTest() {
        this.sessionRepository = mock(SessionRepository.class);
        this.agendaService = mock(AgendaService.class);
        this.sessionService = new SessionService(agendaService, sessionRepository);
    }

    @Test
    void openSessionWithSuccessShouldOpenSessionWithCorrectData() {
        when(sessionRepository.findById(3131L)).thenReturn(Optional.empty());
        when(agendaService.get(3131L)).thenReturn(AgendaStub.createDto());
        when(sessionRepository.save(any(SessionEntity.class))).thenReturn(SessionStub.createEntity());

        sessionService.openSession(3131L, 30);

        ArgumentCaptor<SessionEntity> entityCaptor = ArgumentCaptor.forClass(SessionEntity.class);
        verify(sessionRepository, times(1)).save(entityCaptor.capture());
        SessionEntity capturedEntity = entityCaptor.getValue();

        assertAll("Assert capturedEntity fields",
                () -> assertNull(capturedEntity.getId()),
                () -> assertEquals(3131L, capturedEntity.getAgendaId()),
                () -> assertEquals(capturedEntity.getEndTime(), capturedEntity.getStartTime().plusMinutes(30)),
                () -> assertNull(capturedEntity.getResult()));
    }

    @Test
    void openSessionWithSessionAlreadyOpenShouldThrowSessionAlreadyOpenException() {
        when(sessionRepository.findById(3131L)).thenReturn(Optional.of(SessionStub.createEntity()));

        SessionAlreadyOpenException exception = assertThrows(SessionAlreadyOpenException.class, () -> sessionService.openSession(3131L, 30));

        assertEquals("error.sessionAlreadyOpen", exception.getMessage());
    }

    @Test
    void validateOpenSessionWithSuccessShouldNotThrowNothing() {
        SessionEntity stub = SessionStub.createEntity();
        stub.setEndTime(LocalDateTime.now().plusMinutes(360));
        when(sessionRepository.findAllByAgendaId(3131L)).thenReturn(Collections.singletonList(stub));

        sessionService.validateOpenSession(3131L);
    }

    @Test
    void validateOpenSessionWithInvalidSessionEndTimeShouldThrowSessionAlreadyClosedException() {
        SessionEntity stub = SessionStub.createEntity();
        stub.setEndTime(LocalDateTime.now().minusMinutes(360));
        when(sessionRepository.findAllByAgendaId(3131L)).thenReturn(Collections.singletonList(stub));

        SessionAlreadyClosedException exception = assertThrows(SessionAlreadyClosedException.class, () -> sessionService.validateOpenSession(3131L));

        assertEquals("error.sessionAlreadyClosed", exception.getMessage());
    }

    @Test
    void validateOpenSessionWithNoFoundedSessionEndTimeShouldThrowSessionNotFoundException() {
        when(sessionRepository.findAllByAgendaId(3131L)).thenReturn(Collections.emptyList());

        SessionNotFoundException exception = assertThrows(SessionNotFoundException.class, () -> sessionService.validateOpenSession(3131L));

        assertEquals("error.sessionNotFound", exception.getMessage());
    }

    @Test
    void getClosedSessionWithSuccessShouldReturnSessionEntity() {
        SessionEntity stub = SessionStub.createEntity();
        stub.setEndTime(LocalDateTime.now().minusMinutes(360));
        when(sessionRepository.findAllByAgendaId(3131L)).thenReturn(Collections.singletonList(stub));

        SessionDto result = sessionService.getClosedSession(3131L);

        assertAll("Assert result fields",
                () -> assertEquals(1L, result.getId()),
                () -> assertEquals(3131L, result.getAgendaId()),
                () -> assertNotNull(result.getStartTime()),
                () -> assertNotNull(result.getEndTime()),
                () -> assertNull(result.getResult()));
    }

    @Test
    void getClosedSessionWithSessionOpenShouldThrowSessionAlreadyOpenException() {
        SessionEntity stub = SessionStub.createEntity();
        stub.setEndTime(LocalDateTime.now().plusMinutes(360));
        when(sessionRepository.findAllByAgendaId(3131L)).thenReturn(Collections.singletonList(stub));

        SessionAlreadyOpenException exception = assertThrows(SessionAlreadyOpenException.class, () -> sessionService.getClosedSession(3131L));

        assertEquals("error.sessionAlreadyOpen", exception.getMessage());
    }

    @Test
    void saveSessionResultWithSuccessShouldSaveSessionResultWithCorrectData() {
        when(sessionRepository.save(any(SessionEntity.class))).thenReturn(SessionStub.createEntityWithResult());

        sessionService.saveSessionResult(SessionStub.createDto(), VotesResultEnum.DRAW);

        ArgumentCaptor<SessionEntity> entityCaptor = ArgumentCaptor.forClass(SessionEntity.class);
        verify(sessionRepository, times(1)).save(entityCaptor.capture());
        SessionEntity capturedEntity = entityCaptor.getValue();

        assertAll("Assert capturedEntity fields",
                () -> assertEquals(1L, capturedEntity.getId()),
                () -> assertEquals(3131L, capturedEntity.getAgendaId()),
                () -> assertEquals("2020-11-08T09:19:37.474372", capturedEntity.getStartTime().toString()),
                () -> assertEquals("2020-11-08T09:49:37.474372", capturedEntity.getEndTime().toString()),
                () -> assertEquals(VotesResultEnum.DRAW, capturedEntity.getResult()));
    }

}
