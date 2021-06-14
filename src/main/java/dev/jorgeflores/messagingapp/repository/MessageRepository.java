package dev.jorgeflores.messagingapp.repository;

import dev.jorgeflores.messagingapp.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, Long> {
    List<MessageEntity> findBySenderUid(Long senderUid);
    List<MessageEntity> findByReceiverUid(Long senderUid);
}
