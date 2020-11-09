package br.com.joaodartora.assemblyservice.api.rest.v1;

import br.com.joaodartora.assemblyservice.config.exception.ErrorTranslator;
import br.com.joaodartora.assemblyservice.config.exception.GlobalExceptionHandler;
import br.com.joaodartora.assemblyservice.dto.VoteDto;
import br.com.joaodartora.assemblyservice.service.VoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class VoteApiTest {

    private final VoteService voteService;
    private final ObjectMapper objectMapper;
    private final VoteApi voteApi;
    private MockMvc mockMvc;

    public VoteApiTest() {
        this.voteService = mock(VoteService.class);
        this.objectMapper = spy(ObjectMapper.class);
        this.voteApi = new VoteApi(voteService, objectMapper);
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(voteApi)
                .setControllerAdvice(new GlobalExceptionHandler(new ErrorTranslator()))
                .build();
    }

    @Test
    void voteOnAgendaWithSuccessShouldReturnStatusOk() throws Exception {
        String request = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request/voteRequest.json")));
        String response = new String(Files.readAllBytes(Paths.get("src/test/resources/json/response/voteResponse.json")));

        when(voteService.vote(eq(3131L), any(VoteDto.class))).thenReturn(20L);

        mockMvc.perform(post("/v1/votes/agenda/3131")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(response));
    }

    @Test
    void voteOnAgendaWithoutAssociatedIdShouldReturnStatusBadRequest() throws Exception {
        String request = new String(Files.readAllBytes(Paths.get("src/test/resources/json/request/voteRequestWithoutAssociatedId.json")));
        String response = new String(Files.readAllBytes(Paths.get("src/test/resources/json/response/voteWithoutAssociatedIdErrorResponse.json")));

        when(voteService.vote(eq(3131L), any(VoteDto.class))).thenReturn(20L);

        mockMvc.perform(post("/v1/votes/agenda/3131")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(response));
    }
}
