package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.controller.exceptions.ChatNotFoundException;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.model.FoundReport;
import com.example.fundbox24backend.api.model.Message;
import com.example.fundbox24backend.api.repository.ChatRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class ChatController {

    private final ChatRepository repository;

    ChatController(ChatRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/chat")
    List<Chat> getAllChats() {
        //TODO: Implement filtered by user id
        return repository.findAll();
    }

    @PostMapping("/chat")
    Chat createChat(@RequestBody Chat newChat) {

        return repository.save(newChat);
    }

    @GetMapping("/chat/{id}")
    Chat getChat(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(ChatNotFoundException::new);
    }

    @DeleteMapping("/chat/{id}")
    void deleteChat(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/chat/{id}/messages")
    List<Message> getMessages(@PathVariable Long id)
    {
        return repository.findById(id)
                .orElseThrow(ChatNotFoundException::new)
                .getMessages();
    }

    @PostMapping("/chat/{id}/messages")
    Chat addMessage(@RequestBody Message newMessage, @PathVariable Long id)
    {
        return repository.findById(id)
                .map(chat ->
                {
                    chat.addMessage(newMessage);
                    return repository.save(chat);
                })
                .orElseThrow(ChatNotFoundException::new);
    }
}
