package com.example.fundbox24backend.api.datatransfer.chat;

import com.example.fundbox24backend.api.datatransfer.message.MessageConverter;
import com.example.fundbox24backend.api.model.Chat;
import com.example.fundbox24backend.api.model.Report;
import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.ReportRepository;
import com.example.fundbox24backend.api.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class ChatConverter
{
    public final ReportRepository reportRepository;
    public final MessageConverter messageConverter;

    public ChatConverter(ReportRepository reportRepository, MessageConverter messageConverter)
    {
        this.reportRepository = reportRepository;
        this.messageConverter = messageConverter;
    }


    public ChatDtoResponse convertToDtoResponse(Chat chat)
    {
        return new ChatDtoResponse(
                chat.getId(),
                chat.getReport().getId(),
                chat.getReport().getTitle(),
                chat.getMessages().stream().map(message -> messageConverter.convertToDtoResponse(message, convertToChatPartnerDtoResponse(message.getSender()))).toList(),
                convertToChatPartnerDtoResponse(chat.getReportVisitor()),
                convertToChatPartnerDtoResponse(chat.getReportCreator()));
    }

    public ChatPartnerDtoResponse convertToChatPartnerDtoResponse(User user)
    {
        return new ChatPartnerDtoResponse(user.getId(), user.getName());
    }

    public Chat convertToEntity(ChatDtoRequest chatDtoRequest, User user)
    {
        Report report = reportRepository.getReferenceById(chatDtoRequest.getReportId());
        return new Chat(
                report,
                user,
                report.getCreator()
        );
    }
}
