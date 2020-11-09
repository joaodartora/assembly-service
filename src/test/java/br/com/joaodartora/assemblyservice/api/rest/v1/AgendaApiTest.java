package br.com.joaodartora.assemblyservice.api.rest.v1;

import br.com.joaodartora.assemblyservice.config.exception.ErrorTranslator;
import br.com.joaodartora.assemblyservice.config.exception.GlobalExceptionHandler;
import br.com.joaodartora.assemblyservice.dto.AgendaDto;
import br.com.joaodartora.assemblyservice.service.AgendaService;
import br.com.joaodartora.assemblyservice.service.VoteService;
import br.com.joaodartora.assemblyservice.stub.AgendaResultsStub;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AgendaApiTest {

    private final AgendaService agendaService;
    private final VoteService voteService;
    private final ObjectMapper objectMapper;
    private final AgendaApi agendaApi;
    private MockMvc mockMvc;

    public AgendaApiTest() {
        this.agendaService = mock(AgendaService.class);
        this.voteService = mock(VoteService.class);
        this.objectMapper = spy(ObjectMapper.class);
        this.agendaApi = new AgendaApi(agendaService, voteService, objectMapper);
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(agendaApi)
                .setControllerAdvice(new GlobalExceptionHandler(new ErrorTranslator()))
                .build();
    }

    @Test
    void createNewAgendaWithSuccessShouldReturnStatusOk() throws Exception {
        String request = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request/createAgendaRequest.json")));
        String response = new String(Files.readAllBytes(Paths.get("src/test/resources/json/response/createAgendaResponse.json")));

        when(agendaService.create(any(AgendaDto.class))).thenReturn(3131L);

        mockMvc.perform(post("/v1/agendas")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    void createNewAgendaWithoutRequiredDescriptionShouldReturnStatusBadRequest() throws Exception {
        String request = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request/createAgendaWithoutDescriptionRequest.json")));
        String response = new String(Files.readAllBytes(Paths.get("src/test/resources/json/response/createAgendaWithoutDescriptionErrorResponse.json")));

        mockMvc.perform(post("/v1/agendas")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(response));
    }

    @Test
    void getAgendaResultsWithSuccessShouldReturnStatusOk() throws Exception {
        String response = new String(Files.readAllBytes(Paths.get("src/test/resources/json/response/getAgendaResultsResponse.json")));

        when(voteService.getResult(3131L)).thenReturn(AgendaResultsStub.createDto());

        mockMvc.perform(get("/v1/agendas/3131/result")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }
}
