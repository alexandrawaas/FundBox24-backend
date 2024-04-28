package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.controller.exceptions.ChatNotFoundException;
import com.example.fundbox24backend.api.model.Chat;
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
    List<Chat> all() {
        return repository.findAll();
    }

    @PostMapping("/chat")
    Chat newChat(@RequestBody Chat newChat) {
        return repository.save(newChat);
    }

    // Single item

    @GetMapping("/chat/{id}")
    Chat one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(ChatNotFoundException::new);
    }

    @PutMapping("/chat/{id}")
    Chat replaceChat(@RequestBody Chat newChat, @PathVariable Long id) {

        return repository.findById(id)
                .map(chat -> {
                    chat.setMessages(newChat.getMessages());
                    return repository.save(chat);
                })
                .orElseGet(() -> {
                    newChat.setId(id);
                    return repository.save(newChat);
                });
    }

    @DeleteMapping("/chat/{id}")
    void deleteChat(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
