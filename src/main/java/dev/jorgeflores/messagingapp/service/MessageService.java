package dev.jorgeflores.messagingapp.service;

import dev.jorgeflores.messagingapp.entity.MessageEntity;
import dev.jorgeflores.messagingapp.model.KafkaMessage;
import dev.jorgeflores.messagingapp.model.Message;
import dev.jorgeflores.messagingapp.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class MessageService {
    private static final Logger LOG = LoggerFactory.getLogger(MessageService.class);

    private final MessageRepository messageRepository;
    private final KafkaProducerService kafkaProducerService;

    public MessageService(MessageRepository messageRepository, KafkaProducerService kafkaProducerService) {
        this.messageRepository = messageRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public List<Message> findAll() {
        List<MessageEntity> messageList = messageRepository.findAll();

        return messageList.stream()
                .map(MessageEntity::mapToDto)
                .collect(toList());
    }

    public List<Message> findBySenderUid(Long senderUid) {
        return messageRepository.findBySenderUid(senderUid).stream()
                .map(MessageEntity::mapToDto)
                .collect(toList());
    }

    public List<Message> findByReceiverUid(Long receiverUid) {
        return messageRepository.findByReceiverUid(receiverUid).stream()
                .map(MessageEntity::mapToDto)
                .collect(toList());
    }

    public List<Message> findBySenderUidAndReceiverUid(Long senderUid, Long receiverUid) {
        return messageRepository.findBySenderUidAndReceiverUid(senderUid, receiverUid).stream()
                .map(MessageEntity::mapToDto)
                .collect(toList());
    }

    public Message saveMessage(Message message) {
        MessageEntity messageEntity = message.mapToEntity();
        MessageEntity savedMessage = messageRepository.save(messageEntity);

        return savedMessage.mapToDto();
    }

    public Message sendMessage(Message message) {
        KafkaMessage kafkaMessage = message.mapToKafkaMessage();

        kafkaProducerService.produce(kafkaMessage);
        return message;
    }
}
