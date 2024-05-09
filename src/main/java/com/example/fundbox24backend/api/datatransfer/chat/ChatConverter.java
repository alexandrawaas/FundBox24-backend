package com.example.fundbox24backend.api.datatransfer.chat;

import com.example.fundbox24backend.api.datatransfer.message.MessageConverter;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.model.Message;
import com.example.fundbox24backend.api.model.Report;
import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.ReportRepository;
import com.example.fundbox24backend.api.repository.UserRepository;
import com.example.fundbox24backend.api.service.AuthService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ChatConverter
{
    public ReportRepository reportRepository;
    public AuthService authService;
    public MessageConverter messageConverter;


    public ChatDtoResponse convertToDtoResponse(Chat chat)
    {
        return new ChatDtoResponse(
                chat.getId(),
                chat.getReport().getId(),
                chat.getMessages().stream().map(messageConverter::convertToDtoResponse).toList(),
                convertToChatPartnerDtoResponse(chat.getReportVisitor()),
                convertToChatPartnerDtoResponse(chat.getReportCreator()));
    }

    public ChatPartnerDtoResponse convertToChatPartnerDtoResponse(User user)
    {
        return new ChatPartnerDtoResponse(user.getId(), user.getName());
    }

    public Chat convertToEntity(ChatDtoRequest chatDtoRequest)
    {
        Report report = reportRepository.getReferenceById(chatDtoRequest.getReportId());
        return new Chat(
                report,
                authService.getCurrentUser(),
                report.getCreator()
        );
    }
}
