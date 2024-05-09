package com.example.fundbox24backend.api.service;

import com.example.fundbox24backend.api.controller.exceptions.ChatNotFoundException;
import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoRequest;
import com.example.fundbox24backend.api.datatransfer.message.MessageConverter;
import com.example.fundbox24backend.api.datatransfer.message.MessageDtoRequest;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.model.Message;
import com.example.fundbox24backend.api.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService
{
    private final ChatRepository repository;
    private final ChatConverter chatConverter;
    private final MessageConverter messageConverter;

    public ChatService(ChatRepository repository, ChatConverter chatConverter, MessageConverter messageConverter)
    {
        this.repository = repository;
        this.chatConverter = chatConverter;
        this.messageConverter = messageConverter;
    }

    public List<Chat> getChats()
    {
        return repository.findAll();
    }

    public Chat getChat(Long id)
    {
        return repository.findById(id).orElseThrow(ChatNotFoundException::new);
    }

    public Chat createChat(ChatDtoRequest chatDtoRequest)
    {
        return repository.save(chatConverter.convertToEntity(chatDtoRequest));
    }

    public Chat addMessage(MessageDtoRequest newMessage, Long id)
    {
        return repository.findById(id)
                .map(chat ->
                {
                    chat.addMessage(messageConverter.convertToEntity(newMessage));
                    return repository.save(chat);
                })
                .orElseThrow(ChatNotFoundException::new);
    }

}
