package com.example.fundbox24backend.api.service;

import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportConverter;
import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportDtoRequest;
import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportDtoResponse;
import com.example.fundbox24backend.api.repository.LostReportRepository;
import com.example.fundbox24backend.api.service.exceptions.ReportNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostReportService
{
    private final LostReportRepository repository;
    private final CategoryService categoryService;
    private final UserService userService;

    private final LostReportConverter lostReportConverter;

    LostReportService(LostReportRepository repository, CategoryService categoryService, UserService userService, LostReportConverter lostReportConverter) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.lostReportConverter = lostReportConverter;
    }


    public List<LostReportDtoResponse> getAllLostReports() {
        // TODO: Filter implementieren
        return repository.findAll().stream().map(lostReportConverter::convertToDtoResponse).toList();
    }

    public LostReportDtoResponse createLostReport(LostReportDtoRequest lostReportDtoRequest) {
        return lostReportConverter.convertToDtoResponse(
                repository.save(lostReportConverter.convertToEntity(lostReportDtoRequest, userService.getCurrentUserEntity()))
        );
    }

    public LostReportDtoResponse getLostReport(Long id) {

        return lostReportConverter.convertToDtoResponse(
                repository.findById(id)
                        .orElseThrow(ReportNotFoundException::new)
        );
    }

    public LostReportDtoResponse replaceLostReport(LostReportDtoRequest newLostReport, Long id) {

        return lostReportConverter.convertToDtoResponse(
                repository.findById(id)
                        .map(report -> {
                            report.setTitle(newLostReport.getTitle());
                            report.setDescription(newLostReport.getDescription());
                            report.setImagePath(newLostReport.getImagePath());
                            report.setFinished(newLostReport.isFinished());
                            try {
                                report.setCategory(categoryService.getCategory(newLostReport.getCategoryId()));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            report.setLostLocation(newLostReport.getLostLocation());
                            report.setLastSeenDate(newLostReport.getLastSeenDate());
                            report.setLastSeenLocation(newLostReport.getLastSeenLocation());
                            report.setLostRadius(newLostReport.getLostRadius());
                            return repository.save(report);
                        })
                        .orElseThrow(ReportNotFoundException::new)
        );
    }

    public void deleteLostReport(Long id) {
        repository.deleteById(id);
    }

}
