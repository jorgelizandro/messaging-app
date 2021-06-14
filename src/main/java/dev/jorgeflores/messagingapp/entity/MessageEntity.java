package dev.jorgeflores.messagingapp.entity;

import dev.jorgeflores.messagingapp.model.Message;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long messageId;
    private Long senderUid;
    private Long receiverUid;
    private String content;
    @Column(insertable = false)
    private LocalDateTime created;
    @Column(insertable = false)
    private LocalDateTime modified;

    public MessageEntity() {
    }

    public MessageEntity(Long senderUid, Long receiverUid, String content) {
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.content = content;
    }

    public Long getMessageId() {
        return messageId;
    }

    public String getContent() {
        return content;
    }

    public Message mapToDto() {
        return new Message(messageId, senderUid, receiverUid, content);
    }
}
