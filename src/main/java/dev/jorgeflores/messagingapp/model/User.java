package dev.jorgeflores.messagingapp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import dev.jorgeflores.messagingapp.entity.UserEntity;

import javax.validation.constraints.NotBlank;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@JsonInclude(NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public class User {
    private Long uid;
    @NotBlank
    private String nickname;

    public User() {
    }

    public User(Long uid, String nickname) {
        this.uid = uid;
        this.nickname = nickname;
    }

    public Long getUid() {
        return uid;
    }

    public String getNickname() {
        return nickname;
    }

    public UserEntity mapToEntity() {
        return new UserEntity(nickname);
    }
}
