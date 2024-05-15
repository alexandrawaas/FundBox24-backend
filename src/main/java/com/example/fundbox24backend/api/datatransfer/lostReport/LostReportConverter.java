package com.example.fundbox24backend.api.datatransfer.lostReport;

import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.model.LostReport;
import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.CategoryRepository;
import com.example.fundbox24backend.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class LostReportConverter
{

    private final CategoryRepository categoryRepository;
    private final ChatConverter chatConverter;

    public LostReportConverter(CategoryRepository categoryRepository, ChatConverter chatConverter)
    {
        this.categoryRepository = categoryRepository;
        this.chatConverter = chatConverter;
    }

    public LostReportDtoResponse convertToDtoResponse(LostReport report)
    {
        return new LostReportDtoResponse(
                report.getId(),
                report.getTitle(),
                report.getDescription(),
                report.getImagePath(),
                report.getCreatedAt(),
                report.isFinished(),
                report.getCategory(),
                report.getLastSeenDate(),
                report.getLastSeenLocation(),
                report.getLostLocation(),
                report.getLostRadius(),
                report.getChats().stream().map(chatConverter::convertToDtoResponse).toList()
        );
    }

    public LostReport convertToEntity(LostReportDtoRequest reportDtoRequest, User user)
    {
        return new LostReport(
                reportDtoRequest.getTitle(),
                reportDtoRequest.getDescription(),
                reportDtoRequest.getImagePath(),
                false,
                categoryRepository.getReferenceById(reportDtoRequest.getCategoryId()),
                reportDtoRequest.getLastSeenDate(),
                reportDtoRequest.getLastSeenLocation(),
                reportDtoRequest.getLostLocation(),
                reportDtoRequest.getLostRadius(),
                user
        );
    }

}
