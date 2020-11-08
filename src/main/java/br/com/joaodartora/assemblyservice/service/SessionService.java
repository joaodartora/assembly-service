package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.mapper.SessionMapper;
import br.com.joaodartora.assemblyservice.repository.SessionRepository;
import br.com.joaodartora.assemblyservice.repository.entity.SessionEntity;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);
    private final AgendaService agendaService;
    private final SessionRepository sessionRepository;
    private final ObjectMapper objectMapper;

    public SessionService(AgendaService agendaService, SessionRepository sessionRepository, ObjectMapper objectMapper) {
        this.agendaService = agendaService;
        this.sessionRepository = sessionRepository;
        this.objectMapper = objectMapper;
    }

    public void openSession(Long agendaId, Integer duration) {
//        existsOpenSession(agendaId);
        agendaService.get(agendaId);
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = startTime.plusMinutes(duration.longValue());
        SessionEntity sessionEntity = SessionMapper.buildEntity(agendaId, startTime, endTime);
        sessionRepository.save(sessionEntity);
    }

    public SessionEntity getOpenSession(Long agendaId) {
        return sessionRepository.findAllByAgendaId(agendaId)
                .stream()
                .filter(this::isSessionOpen)
                .findFirst()
                .orElseThrow(RuntimeException::new); // TODO: 08/11/2020 Tratar com exception personalizada para sessão já fechada.
    }

    public SessionEntity getClosedSession(Long agendaId) {
        return sessionRepository.findAllByAgendaId(agendaId)
                .stream()
                .filter(session -> !isSessionOpen(session))
                .findFirst()
                .orElseThrow(RuntimeException::new); // TODO: 08/11/2020 Tratar com exception personalizada para sessão ainda aberta.
    }

    public void saveSessionResult(SessionEntity sessionEntity, VotesResultEnum result) {
        sessionEntity.setResult(result);
        sessionRepository.save(sessionEntity);
    }

    private Boolean isSessionOpen(SessionEntity session) {
        return session.getEndTime().isAfter(LocalDateTime.now());
    }

}
