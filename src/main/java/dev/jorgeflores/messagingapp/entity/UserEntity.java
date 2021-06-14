package dev.jorgeflores.messagingapp.entity;

import dev.jorgeflores.messagingapp.model.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "user_account")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long uid;
    private String nickname;
    @Column(insertable = false)
    private LocalDateTime created;
    @Column(insertable = false)
    private LocalDateTime modified;

    public UserEntity() {
    }

    public UserEntity(String nickname) {
        this.nickname = nickname;
    }

    public Long getUid() {
        return uid;
    }

    public String getNickname() {
        return nickname;
    }

    public User mapToDto() {
        return new User(uid, nickname);
    }
}
