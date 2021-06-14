package dev.jorgeflores.messagingapp.service;

import dev.jorgeflores.messagingapp.model.KafkaMessage;
import dev.jorgeflores.messagingapp.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class KafkaConsumerService {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerService.class);

    private final MessageService messageService;

    public KafkaConsumerService(MessageService messageService) {
        this.messageService = messageService;
    }

    @KafkaListener(topics = "#{'${kafka.topic.name}'}", containerFactory = "kafkaListenerContainerFactory")
    public void consume(KafkaMessage kafkaMessage) {
        LOG.info(format("Received kafka message suid=%d ruid=%d",
                kafkaMessage.getSenderUid(),
                kafkaMessage.getReceiverUid()));
        Message message = kafkaMessage.mapToDto();

        messageService.saveMessage(message);
    }
}
