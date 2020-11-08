package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.SessionDto;
import br.com.joaodartora.assemblyservice.repository.AgendaRepository;
import br.com.joaodartora.assemblyservice.repository.SessionRepository;
import br.com.joaodartora.assemblyservice.type.VoteChoiceEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionService.class);
    private final AgendaRepository agendaRepository;
    private final SessionRepository sessionRepository;
    private final ObjectMapper objectMapper;

    public SessionService(AgendaRepository agendaRepository, SessionRepository sessionRepository, ObjectMapper objectMapper) {
        this.agendaRepository = agendaRepository;
        this.sessionRepository = sessionRepository;
        this.objectMapper = objectMapper;
    }

    public Long openSession(SessionDto sessionDto) {
//        get agenda
//        salvar inicio de sessão
        return null;
    }

    public VoteChoiceEnum countVotes() {
//        buscar sessão
//        verificar se sessão já encerrou, caso não, lançar expcetion
//        contabilizar votos
//        salvar resultado no banco de dados
//        enviar evento de encerramento de votação
//        retornar resultado da votação
        return null;
    }

}
