package com.example.MessageAPI;

import com.example.MessageAPI.models.MessageRepository;
import com.example.MessageAPI.services.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.Assert.*;

@SpringBootTest
class MessageAPITest {

	private MessageService cut;
	@Mock
	private MessageRepository messageRepository;

	@Test
	void sendMessageWithInvalidJson_ResponseBadRequest() {
		cut = new MessageService(messageRepository);

		String wrongJson = "{'messageContent': 'Disk space is 80% full' 'type': 'Warn'}";
		ResponseEntity<String> actual = cut.saveMessage(wrongJson);

		assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
	}

	@Test
	void sendMessageWithValidJson_ResponseCreated() {
		cut = new MessageService(messageRepository);

		String wrongJson = "{'messageContent': 'Disk space is 80% full', 'type': 'Warn'}";
		ResponseEntity<String> actual = cut.saveMessage(wrongJson);

		assertEquals(HttpStatus.CREATED, actual.getStatusCode());
	}
}
