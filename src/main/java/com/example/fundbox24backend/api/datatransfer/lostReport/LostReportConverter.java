package com.example.fundbox24backend.api.datatransfer.lostReport;

import com.example.fundbox24backend.api.datatransfer.chat.ChatConverter;
import com.example.fundbox24backend.api.model.LostReport;
import com.example.fundbox24backend.api.repository.CategoryRepository;
import com.example.fundbox24backend.api.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class LostReportConverter
{

    private CategoryRepository categoryRepository;
    private AuthService authService;
    private ChatConverter chatConverter;

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
                report.getChats().stream().map(chat -> chatConverter.convertToDtoResponse(chat)).toList()
        );
    }

    public LostReport convertToEntity(LostReportDtoRequest reportDtoRequest)
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
                authService.getCurrentUser()
        );
    }

}
