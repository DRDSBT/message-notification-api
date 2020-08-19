package com.example.StatusFailureApp.services;

import com.example.StatusFailureApp.models.Message;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private Gson gson = new Gson();

    public ResponseEntity<String> saveMessage(String messageJson) {
        Message message;

        try {
            message = Message.createMessageFromJson(messageJson);
        } catch (JsonSyntaxException jse) {
            return new ResponseEntity<>("{'Error-Message': '" + jse.getMessage() + "'}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(messageJson, HttpStatus.CREATED);
    }
}
