package dev.jorgeflores.messagingapp.model;

public class KafkaMessage {
    private Long senderUid;
    private Long receiverUid;
    private String content;

    public KafkaMessage() {
    }

    public KafkaMessage(Long senderUid, Long receiverUid, String content) {
        this.senderUid = senderUid;
        this.receiverUid = receiverUid;
        this.content = content;
    }

    public Long getSenderUid() {
        return senderUid;
    }

    public Long getReceiverUid() {
        return receiverUid;
    }

    public String getContent() {
        return content;
    }

    public Message mapToDto() {
        return new Message(senderUid, receiverUid, content);
    }
}
