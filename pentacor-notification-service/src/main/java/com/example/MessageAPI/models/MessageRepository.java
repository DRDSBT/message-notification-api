package com.example.MessageAPI.models;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MessageRepository extends PagingAndSortingRepository<Message, String> {
    /**
     * Sucht alle {@link Message}
     * @param type Type
     * @return Liste aller gefundenen {@link Message}
     */
    List<Message> findByType(String type);
}
