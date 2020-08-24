package com.example.MessageAPI.services;

import com.example.MessageAPI.models.Message;
import com.example.MessageAPI.models.MessageRepository;
import com.github.seratch.jslack.Slack;
import com.github.seratch.jslack.api.webhook.Payload;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class MessageService {

    MessageRepository messageRepository;

    private String slackHookError = "https://hooks.slack.com/services/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";
    private String slackHookWarning = "https://hooks.slack.com/services/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";
    private String slackHookInfo = "https://hooks.slack.com/services/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";
    private final Gson gson = new Gson();

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    // Methode, die den Inhalt des POST-Endpunkts verarbeitet,
    public ResponseEntity<String> saveMessage(String messageJson) {
        Message message;

        // Fehlerbehandlung
        try {
            message = Message.createMessageFromJson(messageJson);
        } catch (JsonSyntaxException jse) { // Falls kein JSON-Format verwendet wurde
            return new ResponseEntity<>("{'Error when processing message': '" + jse.getMessage() + "'}", HttpStatus.BAD_REQUEST);
        }

        // Fallunterscheidung anhand des Typs

        //Info-Fall
        if(message.getType().equals("info")) {
            sendMessageToSlack(message, "#info-messages", slackHookInfo);
            log.error("Infomeldungw: " + message.getMessageContent());
        }

        //Warining-Fall
        if(message.getType().equals("warning")) {
            sendMessageToSlack(message, "#warning-messages", slackHookWarning);
            log.error("Warnmeldung: " + message.getMessageContent());
        }

        //Error-Fall
        if(message.getType().equals("error")) {
            sendMessageToSlack(message, "#error-messages", slackHookError);
            log.error("Fehlermeldung: " + message.getMessageContent());
        }

        // Speicherung
        messageRepository.save(message);
        // Return Message, HttpStatus.CREATED = Erfolgreiche Verarbeitung und Speicherung der Message
        return new ResponseEntity<>(messageJson, HttpStatus.CREATED);
    }

    // Message wird an Test-Slack weitergeleitet
    public void sendMessageToSlack(Message message, String channel, String webHook) {
        String messageBuilder = "New message: " + message.getMessageContent() + ", type: " + message.getType();
        // Payload-Bauplan für Zugriff auf Slack
        Payload payload = Payload.builder()
                .channel(channel)
                .username("Notification-Robot")
                .iconEmoji(":rocket:")
                .text(messageBuilder)
                .build();
        try {
            //Weiterleitung an Slack
            Slack.getInstance().send(webHook, payload);
        } catch (IOException exception) {
            log.error("Couldn't send to Slack: " + exception);
        }
    }

    public ResponseEntity<String> findAllMessages() {
        // Iterable = List -> aufgrund findAll()
        Iterable<Message> tempMessages = messageRepository.findAll();
        if (Iterables.size(tempMessages) == 0) {
            return new ResponseEntity<>("No messages found", HttpStatus.NOT_FOUND);
        }
        //GSON wieder zu JSON umwandeln
        return new ResponseEntity<>(gson.toJson(messageRepository.findAll()), HttpStatus.OK);
    }

    public ResponseEntity<String> findMessagesByType(String type) {
        //List -> aufgrund findByType(type)
        List<Message> tempMessages = messageRepository.findByType(type);
        if (tempMessages.isEmpty()) {
            return new ResponseEntity<>("No messages found", HttpStatus.NOT_FOUND);
        }
        //GSON wieder zu JSON umwandeln
        return new ResponseEntity<>(gson.toJson(tempMessages), HttpStatus.OK);
    }

}
