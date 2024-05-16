package com.example.fundbox24backend.api.datatransfer.foundReport;

import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.model.FoundReport;
import com.example.fundbox24backend.api.model.User;
import com.example.fundbox24backend.api.repository.CategoryRepository;
import com.example.fundbox24backend.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class FoundReportConverter
{
    private final CategoryRepository categoryRepository;
    private final ChatConverter chatConverter;

    public FoundReportConverter(CategoryRepository categoryRepository, ChatConverter chatConverter)
    {
        this.categoryRepository = categoryRepository;
        this.chatConverter = chatConverter;
    }

    public FoundReportDtoResponse convertToDtoResponse(FoundReport report)
    {
        return new FoundReportDtoResponse(
                report.getId(),
                report.getTitle(),
                report.getDescription(),
                report.getImagePath(),
                report.getCreatedAt(),
                report.isFinished(),
                report.getCategory(),
                report.getFoundDate(),
                report.getFoundLocation(),
                report.getCurrentLocation(),
                report.getChats().stream().map(chatConverter::convertToDtoResponse).toList()
        );
    }

    public FoundReport convertToEntity(FoundReportDtoRequest reportDtoRequest, User user)
    {
        return new FoundReport(
                reportDtoRequest.getTitle(),
                reportDtoRequest.getDescription(),
                reportDtoRequest.getImagePath(),
                false,
                categoryRepository.getReferenceById(reportDtoRequest.getCategoryId()),
                reportDtoRequest.getFoundDate(),
                reportDtoRequest.getFoundLocation(),
                reportDtoRequest.getCurrentLocation(),
                user
        );
    }

}
