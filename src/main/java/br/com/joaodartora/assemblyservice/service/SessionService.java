package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.exception.SessionAlreadyClosedException;
import br.com.joaodartora.assemblyservice.exception.SessionAlreadyOpenException;
import br.com.joaodartora.assemblyservice.exception.SessionNotFoundException;
import br.com.joaodartora.assemblyservice.mapper.SessionMapper;
import br.com.joaodartora.assemblyservice.repository.SessionRepository;
import br.com.joaodartora.assemblyservice.repository.entity.SessionEntity;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);
    private final AgendaService agendaService;
    private final SessionRepository sessionRepository;

    public SessionService(AgendaService agendaService, SessionRepository sessionRepository) {
        this.agendaService = agendaService;
        this.sessionRepository = sessionRepository;
    }

    public void openSession(Long agendaId, Integer duration) {
        verifySessionNotExists(agendaId);
        agendaService.get(agendaId);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusMinutes(duration.longValue());
        SessionEntity sessionEntity = SessionMapper.buildEntity(agendaId, startTime, endTime);
        sessionRepository.save(sessionEntity);
    }

    public void validateOpenSession(Long agendaId) {
        List<SessionEntity> sessions = sessionRepository.findAllByAgendaId(agendaId);
        if (CollectionUtils.isEmpty(sessions))
            throw new SessionNotFoundException();

        sessions.stream()
                .filter(this::isSessionOpen)
                .findFirst()
                .orElseThrow(SessionAlreadyClosedException::new);
    }

    public SessionEntity getClosedSession(Long agendaId) {
        return sessionRepository.findAllByAgendaId(agendaId)
                .stream()
                .filter(session -> !isSessionOpen(session))
                .findFirst()
                .orElseThrow(SessionAlreadyOpenException::new);
    }

    public void saveSessionResult(SessionEntity sessionEntity, VotesResultEnum result) {
        sessionEntity.setResult(result);
        sessionRepository.save(sessionEntity);
    }

    private Boolean isSessionOpen(SessionEntity session) {
        return session.getEndTime().isAfter(LocalDateTime.now());
    }

    private void verifySessionNotExists(Long agendaId) {
        sessionRepository.findById(agendaId)
                .ifPresent(sessionEntity -> {
                    LOGGER.error("Session with ID {} is already open", agendaId);
                    throw new SessionAlreadyOpenException();
                });
    }
}
