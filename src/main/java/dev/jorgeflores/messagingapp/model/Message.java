package dev.jorgeflores.messagingapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.jorgeflores.messagingapp.entity.MessageEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public class Message {
    private Long messageId;
    private Long senderUid;
    @Positive(message = "Invalid receiver uid")
    private Long receiverUid;
    @NotBlank(message = "Invalid content")
    private String content;

    public Message() {
    }

    public Message(Long senderUid, Long receiverUid, String content) {
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.content = content;
    }

    public Message(Long messageId, Long senderUid, Long receiverUid, String content) {
        this.messageId = messageId;
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.content = content;
    }

    public Long getMessageId() {
        return messageId;
    }

    public Long getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(Long senderUid) {
        this.senderUid = senderUid;
    }

    public Long getReceiverUid() {
        return receiverUid;
    }

    public String getContent() {
        return content;
    }

    public MessageEntity mapToEntity() {
        return new MessageEntity(senderUid, receiverUid, content);
    }

    public KafkaMessage mapToKafkaMessage() {
        return new KafkaMessage(senderUid, receiverUid, content);
    }
}
