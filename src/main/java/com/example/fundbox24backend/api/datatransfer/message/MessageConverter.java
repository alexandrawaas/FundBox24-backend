package com.example.fundbox24backend.api.datatransfer.message;

import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.model.ImageMessage;
import com.example.fundbox24backend.api.model.Message;
import com.example.fundbox24backend.api.model.TextMessage;
import com.example.fundbox24backend.api.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class MessageConverter
{
    private ChatConverter chatConverter;
    private AuthService authService;
    public MessageDtoResponse convertToDtoResponse(Message message)
    {
        return new MessageDtoResponse(
                message.getId(),
                message.getContent(),
                message instanceof ImageMessage,
                message.getSentAt(),
                chatConverter.convertToChatPartnerDtoResponse(message.getSender())
                );
    }

    public Message convertToEntity(MessageDtoRequest messageDtoRequest)
    {
        if (messageDtoRequest.getIsImage())
        {
            return new ImageMessage(
                    messageDtoRequest.getSentAt(),
                    authService.getCurrentUser(),
                    messageDtoRequest.getContent()
            );
        }

        return new TextMessage(
                messageDtoRequest.getSentAt(),
                authService.getCurrentUser(),
                messageDtoRequest.getContent()
        );
    }
}
