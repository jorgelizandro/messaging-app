package dev.jorgeflores.messagingapp.controller;

import dev.jorgeflores.messagingapp.model.Message;
import dev.jorgeflores.messagingapp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "v1/messages",
        produces = APPLICATION_JSON_VALUE
)
public class MessageController {
    private static final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.findAll();
    }
}
