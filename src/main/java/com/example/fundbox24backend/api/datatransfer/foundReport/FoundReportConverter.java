package com.example.fundbox24backend.api.datatransfer.foundReport;

import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.model.FoundReport;
import com.example.fundbox24backend.api.repository.CategoryRepository;
import com.example.fundbox24backend.api.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class FoundReportConverter
{
    private CategoryRepository categoryRepository;
    private AuthService authService;
    private ChatConverter chatConverter;

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
                report.getChats().stream().map(chat -> chatConverter.convertToDtoResponse(chat)).toList()
        );
    }

    public FoundReport convertToEntity(FoundReportDtoRequest reportDtoRequest)
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
                authService.getCurrentUser()
        );
    }

}
