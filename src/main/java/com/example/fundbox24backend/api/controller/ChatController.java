package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoResponse;
import com.example.fundbox24backend.api.service.exceptions.ChatNotFoundException;
import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoRequest;
import com.example.fundbox24backend.api.datatransfer.message.MessageDtoRequest;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class ChatController {

    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    ChatDtoResponse createChat(@RequestBody ChatDtoRequest chatDtoRequest) {
        return chatService.createChat(chatDtoRequest);
    }

    @GetMapping("/chat/{chatId}")
    ChatDtoResponse getChat(@PathVariable Long chatId) {
        try {
            return chatService.getChat(chatId);
        } catch (ChatNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PatchMapping("/chat/{chatId}")
    ChatDtoResponse addMessage(@RequestBody MessageDtoRequest newMessage, @PathVariable Long chatId)
    {
        try {
            return chatService.addMessage(newMessage, chatId);
        } catch (ChatNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
        }
    }
}
