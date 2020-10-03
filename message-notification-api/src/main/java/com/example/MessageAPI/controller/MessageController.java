package com.example.MessageAPI.controller;

import com.example.MessageAPI.services.MessageService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Kennung REST-API
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "/sendMessage")
    public ResponseEntity<String> sendMessage(@RequestBody @NotNull String messageJson) {
        return messageService.saveMessage(messageJson);
    }

    @GetMapping(value = "/getAllMessages")
    public ResponseEntity<String> getAllMessages() {
        return messageService.findAllMessages();
    }

    @GetMapping(value = "/getMessages/{type}")
    public ResponseEntity<String> getMessagesByType(@PathVariable @NotNull String type) {
        return messageService.findMessagesByType(type);
    }
}
