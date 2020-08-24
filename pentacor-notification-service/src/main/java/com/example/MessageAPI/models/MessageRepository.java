package com.example.MessageAPI.models;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

// Repository für MongoDB, um Nachrichten abzuspeichern und herauszulesen
public interface MessageRepository extends PagingAndSortingRepository<Message, String> {

    // Zugriff auf Messages anhand des Typs
    List<Message> findByType(String type);
}
