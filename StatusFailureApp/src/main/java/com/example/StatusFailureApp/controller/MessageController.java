package com.example.StatusFailureApp.controller;

import com.example.StatusFailureApp.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private static final String PATH = "/sendmessage";

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = PATH)
    public ResponseEntity<String> message(@RequestBody String messageJson) {

        ResponseEntity<String> response = messageService.saveMessage(messageJson);

        return response;
    }
}
