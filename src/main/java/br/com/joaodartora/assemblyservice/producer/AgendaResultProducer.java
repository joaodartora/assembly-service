package br.com.joaodartora.assemblyservice.producer;

import br.com.joaodartora.assemblyservice.producer.event.AgendaResultsEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AgendaResultProducer {

    private final Logger LOGGER = LoggerFactory.getLogger(AgendaResultProducer.class);
    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final String agendaResultTopicName;

    public AgendaResultProducer(KafkaTemplate<String, Message> kafkaTemplate,
                                @Value("${kafka.topic-name.agenda-result}") String agendaResultTopicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.agendaResultTopicName = agendaResultTopicName;
    }

    @Async
    public void sendAgendaResultEvent(AgendaResultsEvent event) {
        try {
            kafkaTemplate.send(buildMessage(event));
            LOGGER.info("Sended agenda result event of the agenda {} with success to topic [{}]", event.getAgendaId(), agendaResultTopicName);
        } catch (KafkaProducerException ex) {
            LOGGER.error("An error occurred when trying to send agenda result event of the agenda {} to topic [{}]", event.getAgendaId(), agendaResultTopicName);
        }
    }

    private Message<AgendaResultsEvent> buildMessage(AgendaResultsEvent event) {
        return MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, agendaResultTopicName)
                .build();
    }
}
