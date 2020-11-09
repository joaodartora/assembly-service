package br.com.joaodartora.assemblyservice.api.rest.v1;

import br.com.joaodartora.assemblyservice.config.exception.ErrorTranslator;
import br.com.joaodartora.assemblyservice.config.exception.GlobalExceptionHandler;
import br.com.joaodartora.assemblyservice.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SessionApiTest {

    private final SessionService sessionService;
    private final SessionApi sessionApi;
    private MockMvc mockMvc;

    public SessionApiTest() {
        this.sessionService = mock(SessionService.class);
        this.sessionApi = new SessionApi(sessionService);
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(sessionApi)
                .setControllerAdvice(new GlobalExceptionHandler(new ErrorTranslator()))
                .build();
    }

    @Test
    void openNewSessionWithSuccessShouldReturnStatusOk() throws Exception {
        doNothing().when(sessionService).openSession(3131L, 5);

        mockMvc.perform(post("/v1/sessions/agenda/3131/open")
                .queryParam("durationTime", "5")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
