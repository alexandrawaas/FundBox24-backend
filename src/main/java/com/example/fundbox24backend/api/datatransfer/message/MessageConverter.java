package com.example.fundbox24backend.api.datatransfer.message;

import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.datatransfer.chat.ChatPartnerDtoResponse;
import com.example.fundbox24backend.api.model.ImageMessage;
import com.example.fundbox24backend.api.model.Message;
import com.example.fundbox24backend.api.model.TextMessage;
import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class MessageConverter
{
    public MessageDtoResponse convertToDtoResponse(Message message, ChatPartnerDtoResponse sender)
    {
        return new MessageDtoResponse(
                message.getId(),
                message.getContent(),
                message instanceof ImageMessage,
                message.getSentAt(),
                sender
                );
    }

    public Message convertToEntity(MessageDtoRequest messageDtoRequest, User user)
    {
        if (messageDtoRequest.getIsImage())
        {
            return new ImageMessage(
                    messageDtoRequest.getSentAt(),
                    user,
                    messageDtoRequest.getContent()
            );
        }

        return new TextMessage(
                messageDtoRequest.getSentAt(),
                user,
                messageDtoRequest.getContent()
        );
    }
}
