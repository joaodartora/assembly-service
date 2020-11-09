package br.com.joaodartora.assemblyservice.api.rest.v1;

import br.com.joaodartora.assemblyservice.api.rest.v1.request.VoteRequest;
import br.com.joaodartora.assemblyservice.api.rest.v1.response.VoteResponse;
import br.com.joaodartora.assemblyservice.dto.VoteDto;
import br.com.joaodartora.assemblyservice.service.VoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/votes")
public class VoteApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionApi.class);
    private final VoteService voteService;
    private final ObjectMapper objectMapper;

    public VoteApi(VoteService voteService, ObjectMapper objectMapper) {
        this.voteService = voteService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/agenda/{agendaId}")
    @ApiOperation(value = "Vote on a selected agenda.", notes = "Receives a vote request and the agenda ID, then register the associated vote.", response = VoteResponse.class)
    public ResponseEntity<VoteResponse> vote(@ApiParam(value = "ID of the agenda", required = true, example = "3108")
                                             @PathVariable Long agendaId,
                                             @RequestBody @Valid VoteRequest request) {
        LOGGER.info("Starting the vote from the associated with ID: {} for the agenda: {}", request.getAssociated().getId(), agendaId);
        VoteDto voteDto = objectMapper.convertValue(request, VoteDto.class);
        Long ballotReceipt = voteService.vote(agendaId, voteDto);
        LOGGER.info("Finishing the vote from the associated with ID: {} for the agenda: {}", request.getAssociated().getId(), agendaId);
        return ResponseEntity.ok(new VoteResponse(ballotReceipt));
    }
}
