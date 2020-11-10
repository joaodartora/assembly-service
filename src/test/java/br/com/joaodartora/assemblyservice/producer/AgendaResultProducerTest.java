package br.com.joaodartora.assemblyservice.producer;

import br.com.joaodartora.assemblyservice.producer.event.AgendaResultsEvent;
import br.com.joaodartora.assemblyservice.stub.AgendaResultsStub;
import br.com.joaodartora.assemblyservice.type.VotesResultEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AgendaResultProducerTest {

    private AgendaResultProducer agendaResultProducer;
    private KafkaTemplate kafkaTemplate;
    private ObjectMapper objectMapper;

    public AgendaResultProducerTest() {
        this.kafkaTemplate = mock(KafkaTemplate.class);
        this.objectMapper = new ObjectMapper();
        agendaResultProducer = new AgendaResultProducer(kafkaTemplate, "kafka.topic-name.agenda-result");
    }

    @Test
    public void sendAgendaResultEventWithSuccess() {
        when(kafkaTemplate.send(any(Message.class))).thenReturn(null);

        agendaResultProducer.sendAgendaResultEvent(AgendaResultsStub.createEvent());

        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(kafkaTemplate, times(1)).send(captor.capture());
        Message message = captor.getValue();
        AgendaResultsEvent capturedEventBody = objectMapper.convertValue(message.getPayload(), AgendaResultsEvent.class);

        assertAll("Assert sended message data",
                () -> assertEquals("kafka.topic-name.agenda-result", message.getHeaders().get(KafkaHeaders.TOPIC)),
                () -> assertEquals(1L, capturedEventBody.getSessionId()),
                () -> assertEquals(3131L, capturedEventBody.getAgendaId()),
                () -> assertEquals(1L, capturedEventBody.getYesVotes()),
                () -> assertEquals(1L, capturedEventBody.getNoVotes()),
                () -> assertEquals(2L, capturedEventBody.getTotalVotes()),
                () -> assertEquals(VotesResultEnum.DRAW, capturedEventBody.getResult()));
    }
}
