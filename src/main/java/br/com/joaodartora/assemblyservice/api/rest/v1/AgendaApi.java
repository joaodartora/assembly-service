package br.com.joaodartora.assemblyservice.api.rest.v1;

import br.com.joaodartora.assemblyservice.api.rest.v1.request.AgendaRequest;
import br.com.joaodartora.assemblyservice.api.rest.v1.response.AgendaResponse;
import br.com.joaodartora.assemblyservice.api.rest.v1.response.AgendaResultsResponse;
import br.com.joaodartora.assemblyservice.dto.AgendaDto;
import br.com.joaodartora.assemblyservice.dto.AgendaResultsDto;
import br.com.joaodartora.assemblyservice.service.AgendaService;
import br.com.joaodartora.assemblyservice.service.SessionService;
import br.com.joaodartora.assemblyservice.service.VoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/agendas")
public class AgendaApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(AgendaApi.class);
    private final AgendaService agendaService;
    private final VoteService voteService;
    private final ObjectMapper objectMapper;

    public AgendaApi(AgendaService agendaService, VoteService voteService, ObjectMapper objectMapper) {
        this.agendaService = agendaService;
        this.voteService = voteService;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    @ApiOperation(value = "Creates a new agenda.", notes = "Receives a new agenda with description and returns the ID of the created agenda", response = AgendaResponse.class)
    public ResponseEntity<AgendaResponse> create(@RequestBody @Valid AgendaRequest request) {
        LOGGER.info("Starting the creation of new agenda with description [{}]", request.getDescription());
        AgendaDto agendaDto = objectMapper.convertValue(request, AgendaDto.class);
        Long agendaId = agendaService.create(agendaDto);
        LOGGER.info("Finishing the creation of new agenda with description [{}] and ID: {}", request.getDescription(), agendaId);
        return ResponseEntity.ok(new AgendaResponse(agendaId));
    }

    @GetMapping("/{agendaId}/result")
    @ApiOperation(value = "Get the agenda total votes count and results.", notes = "Receives an agenda ID, count the votes and return the totals and result", response = AgendaResultsResponse.class)
    public ResponseEntity<AgendaResultsResponse> getResult(@PathVariable Long agendaId) {
        LOGGER.info("Starting the agenda {} result processing.", agendaId);
        AgendaResultsDto resultsDto = voteService.getResult(agendaId);
        AgendaResultsResponse response = objectMapper.convertValue(resultsDto, AgendaResultsResponse.class);
        LOGGER.info("Finishing the agenda {} result processing.", agendaId);
        return ResponseEntity.ok(response);
    }
}
