package br.com.joaodartora.assemblyservice.api.rest.v1;

import br.com.joaodartora.assemblyservice.service.SessionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/sessions")
public class SessionApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionApi.class);
    private final SessionService sessionService;

    public SessionApi(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping("/agenda/{agendaId}/open")
    @ApiOperation(value = "Open new voting session on an agenda.", notes = "Receives an agenda ID and optionally the duration of the new session, then open a new session", response = Void.class)
    public ResponseEntity<Void> open(@ApiParam(value = "ID of the agenda", required = true, example = "3108")
                                     @PathVariable Long agendaId,
                                     @ApiParam(value = "Duration of agenda's session in minutes.", defaultValue = "1", example = "60")
                                     @RequestParam(required = false, defaultValue = "1") Integer durationTime) {
        LOGGER.info("Starting the opening of new voting session for the agenda {}, with duration of {} minutes", agendaId, durationTime);
        sessionService.openSession(agendaId, durationTime);
        LOGGER.info("Finishing the opening of new voting session for the agenda {}, with duration of {} minutes", agendaId, durationTime);
        return ResponseEntity.ok().build();
    }

}
