package dev.jorgeflores.messagingapp.service;

import dev.jorgeflores.messagingapp.entity.UserEntity;
import dev.jorgeflores.messagingapp.model.User;
import dev.jorgeflores.messagingapp.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        LOG.info("Retrieving users");
        List<UserEntity> userList = userRepository.findAll();

        return userList.stream()
                .map(UserEntity::mapToDto)
                .collect(toList());
    }

    public User saveUser(User user) {
        UserEntity userEntity = user.mapToEntity();
        UserEntity savedUser = userRepository.save(userEntity);

        return savedUser.mapToDto();
    }
}
