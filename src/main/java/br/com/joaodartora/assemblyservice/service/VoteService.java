package br.com.joaodartora.assemblyservice.service;

import br.com.joaodartora.assemblyservice.dto.VoteDto;
import br.com.joaodartora.assemblyservice.repository.SessionRepository;
import br.com.joaodartora.assemblyservice.repository.VoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VoteService.class);
    private final SessionRepository sessionRepository;
    private final VoteRepository voteRepository;
    private final ObjectMapper objectMapper;

    public VoteService(SessionRepository sessionRepository, VoteRepository voteRepository, ObjectMapper objectMapper) {
        this.sessionRepository = sessionRepository;
        this.voteRepository = voteRepository;
        this.objectMapper = objectMapper;
    }

    public Long vote(VoteDto voteDto) {
//        verificar se sessão está aberta, caso não, exception
//        verificar se associado já votou, caso já, exception
        return null;
    }
}
