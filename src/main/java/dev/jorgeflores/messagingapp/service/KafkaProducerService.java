package dev.jorgeflores.messagingapp.service;

import dev.jorgeflores.messagingapp.model.KafkaMessage;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static java.lang.String.format;

@Service
public class KafkaProducerService {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducerService.class);

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;
    private final String topicName;

    public KafkaProducerService(KafkaTemplate<String, KafkaMessage> kafkaTemplate, @Value("${kafka.topic.name}") String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void produce(KafkaMessage kafkaMessage) {
        kafkaTemplate.send(topicName, kafkaMessage);

        ListenableFuture<SendResult<String, KafkaMessage>> future = kafkaTemplate.send(topicName, kafkaMessage);

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onSuccess(SendResult<String, KafkaMessage> result) {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                long offset = recordMetadata.offset();
                LOG.info(format("Sent kafka message with suid=%d ruid=%d and offset=%d",
                        kafkaMessage.getSenderUid(),
                        kafkaMessage.getReceiverUid(),
                        offset));
            }

            @Override
            public void onFailure(Throwable throwable) {
                LOG.error(format("Unable to send kafka message=[%s] due to: %s", kafkaMessage.getContent(), throwable.getMessage()));
            }
        });
    }
}
