package com.example.StatusFailureApp.models;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Bauplan für Datei-Format (Message)
@NoArgsConstructor //Kein Konstruktor notwendig
@AllArgsConstructor //Getter-Methoden für Google-gson
public class Message {

    private String messageContent;
    private String type;

    //Umwandlung von JSON-String in Google-gson-Objekt
    //Formatanpassung
    public static final Message createMessageFromJson(String messageJson) {
        Gson gson = new Gson();
        Message message = gson.fromJson(messageJson, Message.class);
        return new Message(message.getMessageContent(), message.getType());
    }
}
