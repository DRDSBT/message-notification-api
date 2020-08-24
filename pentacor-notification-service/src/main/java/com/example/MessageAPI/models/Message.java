package com.example.MessageAPI.models;

import com.google.gson.Gson;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data //Bauplan für Datei-Format (Message)
@NoArgsConstructor //Kein Konstruktor notwendig
@AllArgsConstructor //Getter-Methoden für Google-gson
@Getter
@Setter
@Document(collection = "Messages")
public class Message {

    private String messageContent;
    private String type;

    //Umwandlung von JSON-String in Google-gson-Objekt
    //Formatanpassung
    public static Message createMessageFromJson(String messageJson) {
        Gson gson = new Gson();
        Message message = gson.fromJson(messageJson, Message.class);
        return new Message(message.getMessageContent(), message.getType());
    }
}
