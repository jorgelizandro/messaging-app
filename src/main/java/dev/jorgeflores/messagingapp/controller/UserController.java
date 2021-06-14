package dev.jorgeflores.messagingapp.controller;

import dev.jorgeflores.messagingapp.model.Message;
import dev.jorgeflores.messagingapp.model.User;
import dev.jorgeflores.messagingapp.service.MessageService;
import dev.jorgeflores.messagingapp.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.lang.String.format;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "v1/users",
        produces = APPLICATION_JSON_VALUE
)
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final MessageService messageService;

    public UserController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User createAccount(@RequestBody User user) {
        LOG.info(format("Creating account %s", user.getNickname()));
        User savedUser = userService.saveUser(user);

        return savedUser;
    }

    @GetMapping("/{uid}/messages")
    public List<Message> getMessages() {
        LOG.info("Retrieving messages");
        return messageService.findAll();
    }

    @GetMapping("/{uid}/messages/sent")
    public List<Message> getSentMessagesByUid(@PathVariable Long uid) {
        LOG.info(format("Retrieving sent messages by uid=%s", uid));
        return messageService.findBySenderUid(uid);
    }

    @GetMapping("/{uid}/messages/received")
    public List<Message> getReceivedMessagesByUid(@PathVariable Long uid) {
        LOG.info(format("Retrieving received messages by uid=%s", uid));
        return messageService.findByReceiverUid(uid);
    }

    @GetMapping("/{uid}/messages/received/{senderUid}")
    public List<Message> getReceivedMessagesBySenderAndReceiver(@PathVariable Long uid, @PathVariable Long senderUid) {
        LOG.info(format("Retrieving received messages by sender uid=%s and receiver uid=%s", senderUid, uid));
        return messageService.findBySenderUidAndReceiverUid(senderUid, uid);
    }

    @PostMapping("/{uid}/messages")
    public Message sendMessage(@PathVariable Long uid, @RequestBody Message message) {
        LOG.info("Sending message");
        message.setSenderUid(uid);
        messageService.sendMessage(message);

        return message;
    }
}
