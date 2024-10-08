package com.example.fundbox24backend.api.service;

import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoResponse;
import com.example.fundbox24backend.api.service.exceptions.ChatNotFoundException;
import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoRequest;
import com.example.fundbox24backend.api.datatransfer.message.MessageConverter;
import com.example.fundbox24backend.api.datatransfer.message.MessageDtoRequest;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService
{
    private final ChatRepository repository;
    private final UserService userService;
    private final ChatConverter chatConverter;
    private final MessageConverter messageConverter;

    public ChatService(ChatRepository repository, UserService userService, ChatConverter chatConverter, MessageConverter messageConverter)
    {
        this.repository = repository;
        this.userService = userService;
        this.chatConverter = chatConverter;
        this.messageConverter = messageConverter;
    }

    public ChatDtoResponse getChat(Long id)
    {
        return chatConverter.convertToDtoResponse(repository.findById(id).orElseThrow(ChatNotFoundException::new));
    }

    public ChatDtoResponse createChat(ChatDtoRequest chatDtoRequest)
    {
        return chatConverter.convertToDtoResponse(repository.save(chatConverter.convertToEntity(chatDtoRequest, userService.getCurrentUserEntity())));
    }

    public ChatDtoResponse addMessage(MessageDtoRequest newMessage, Long id)
    {
        return chatConverter.convertToDtoResponse(
                repository.findById(id)
                .map(chat ->
                {
                    chat.addMessage(messageConverter.convertToEntity(newMessage, userService.getCurrentUserEntity()));
                    return repository.save(chat);
                })
                .orElseThrow(ChatNotFoundException::new)
        );
    }

}
