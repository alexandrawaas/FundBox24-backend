package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.controller.exceptions.ChatNotFoundException;
import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoRequest;
import com.example.fundbox24backend.api.datatransfer.message.MessageDtoRequest;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.model.FoundReport;
import com.example.fundbox24backend.api.model.Message;
import com.example.fundbox24backend.api.repository.ChatRepository;
import com.example.fundbox24backend.api.service.ChatService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
class ChatController {

    private final ChatService chatService;

    ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    List<Chat> getAllChats() {
        //TODO: Implement filtered by user id
        return chatService.getChats();
    }

    @PostMapping("/chat")
    Chat createChat(@RequestBody ChatDtoRequest chatDtoRequest) {
        return chatService.createChat(chatDtoRequest);
    }

    @GetMapping("/chat/{chatId}")
    Chat getChat(@PathVariable Long chatId) {
        try {
            return chatService.getChat(chatId);
        } catch (ChatNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
        }
    }

    @PatchMapping("/chat/{chatId}")
    Chat addMessage(@RequestBody MessageDtoRequest newMessage, @PathVariable Long chatId)
    {
        try {
            return chatService.addMessage(newMessage, chatId);
        } catch (ChatNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chat not found");
        }
    }
}
