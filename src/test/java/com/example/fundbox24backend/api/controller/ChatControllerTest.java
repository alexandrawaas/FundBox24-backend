package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.controller.exceptions.ChatNotFoundException;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.model.Message;
import com.example.fundbox24backend.api.model.TextMessage;
import com.example.fundbox24backend.api.model.ImageMessage;
import com.example.fundbox24backend.api.repository.ChatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChatControllerTest {

    @Mock
    private ChatRepository repository;

    private ChatController chatController;

    @BeforeEach
    void setUp() {
        chatController = new ChatController(repository);
    }

    @Test
    void GetAllChatsTest() {
        // Mock some chats
        List<Chat> mockChats = new ArrayList<>();
        mockChats.add(new Chat());
        mockChats.add(new Chat());

        // Mock the repository behavior
        when(repository.findAll()).thenReturn(mockChats);

        // Call the controller method
        List<Chat> allChats = chatController.getAllChats();

        // Assertions
        assertEquals(mockChats, allChats);
    }

    @Test
    void CreateChatTest() {
        // Create a new chat object
        Chat newChat = new Chat();

        // Mock the repository behavior
        when(repository.save(newChat)).thenReturn(newChat);

        // Call the controller method
        Chat createdChat = chatController.createChat(newChat);

        // Assertions
        assertNotNull(createdChat);
        assertEquals(newChat, createdChat);
    }

    @Test
    void GetChatTest() {
        // Define an existing chat id
        Long chatId = 1L;

        // Mock a chat object
        Chat mockChat = new Chat();
        mockChat.setId(chatId);

        // Mock the repository behavior
        when(repository.findById(chatId)).thenReturn(Optional.of(mockChat));

        // Call the controller method
        Chat foundChat = chatController.getChat(chatId);

        // Assertions
        assertNotNull(foundChat);
        assertEquals(mockChat, foundChat);
    }

    @Test
    void shouldThrowChatNotFoundExceptionForNonexistentChat() {
        // Define a non-existing chat id
        Long chatId = 1L;

        // Mock the repository behavior
        when(repository.findById(chatId)).thenReturn(Optional.empty());

        // Expected exception
        assertThrows(ChatNotFoundException.class, () -> chatController.getChat(chatId));
    }

    @Test
    void DeleteChatTest() {
        // Define an existing chat id
        Long chatId = 1L;

        // Mock the repository behavior
        doNothing().when(repository).deleteById(chatId);

        // Call the controller method
        chatController.deleteChat(chatId);

        // Verify repository interaction
        verify(repository, times(1)).deleteById(chatId);
    }

    @Test
    void GetMessagesTest() {
        // Define an existing chat id
        Long chatId = 1L;

        // Mock a chat object with messages
        Chat mockChat = new Chat();
        List<Message> messages = new ArrayList<>();
        messages.add(new TextMessage());
        mockChat.setMessages(messages);

        // Mock the repository behavior
        when(repository.findById(chatId)).thenReturn(Optional.of(mockChat));

        // Call the controller method
        List<Message> retrievedMessages = chatController.getMessages(chatId);

        // Assertions
        assertEquals(messages, retrievedMessages);
    }

    @Test
    void shouldThrowChatNotFoundExceptionForGettingMessagesOfNonexistentChat() {
        // Define a non-existing chat id
        Long chatId = 1L;

        // Mock the repository behavior
        when(repository.findById(chatId)).thenReturn(Optional.empty());

        // Expected exception
        assertThrows(ChatNotFoundException.class, () -> chatController.getMessages(chatId));
    }

    @Test
    void shouldAddTextMessageToExistingChat() {
        // Define an existing chat id and a new text message
        Long chatId = 1L;
        String messageText = "This is a test message";
        TextMessage newMessage = new TextMessage(null, null, messageText); // Assuming sentAt and sender are not required

        // Mock a chat object
        Chat mockChat = new Chat();
        mockChat.setId(chatId);
        List<Message> existingMessages = new ArrayList<>();
        mockChat.setMessages(existingMessages);

        // Mock the repository behavior to find the chat
        when(repository.findById(chatId)).thenReturn(Optional.of(mockChat));

        // Mock the repository behavior to save the updated chat
        when(repository.save(any(Chat.class))).thenAnswer(invocation -> {
            Chat updatedChat = invocation.getArgument(0);
            assertEquals(1, updatedChat.getMessages().size());
            TextMessage addedMessage = (TextMessage) updatedChat.getMessages().get(0);
            assertEquals(messageText, addedMessage.getText());
            return updatedChat;
        });

        // Call the controller method
        Chat updatedChat = chatController.addMessage(newMessage, chatId);

        // Assertions
        assertNotNull(updatedChat);
        assertEquals(chatId, updatedChat.getId());
    }

    @Test
    void shouldAddImageMessageToExistingChat() {
        // Define an existing chat id and a new image message
        Long chatId = 1L;
        String imagePath = "path/to/image.jpg";
        ImageMessage newMessage = new ImageMessage(null, null, imagePath); // Assuming sentAt and sender are not required

        // Mock a chat object
        Chat mockChat = new Chat();
        mockChat.setId(chatId);
        List<Message> existingMessages = new ArrayList<>();
        mockChat.setMessages(existingMessages);

        // Mock the repository behavior to find the chat
        when(repository.findById(chatId)).thenReturn(Optional.of(mockChat));

        // Mock the repository behavior to save the updated chat
        when(repository.save(any(Chat.class))).thenAnswer(invocation -> {
            Chat updatedChat = invocation.getArgument(0);
            assertEquals(1, updatedChat.getMessages().size());
            ImageMessage addedMessage = (ImageMessage) updatedChat.getMessages().get(0);
            assertEquals(imagePath, addedMessage.getImagePath());
            return updatedChat;
        });

        // Call the controller method
        Chat updatedChat = chatController.addMessage(newMessage, chatId);

        // Assertions
        assertNotNull(updatedChat);
        assertEquals(chatId, updatedChat.getId());
    }
}
