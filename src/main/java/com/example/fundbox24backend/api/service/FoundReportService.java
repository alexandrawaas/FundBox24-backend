package com.example.fundbox24backend.api.service;

import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportConverter;
import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportDtoRequest;
import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportDtoResponse;
import com.example.fundbox24backend.api.repository.FoundReportRepository;
import com.example.fundbox24backend.api.service.exceptions.ReportNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoundReportService
{
    private final FoundReportRepository repository;
    private final CategoryService categoryService;

    private final UserService userService;

    private final FoundReportConverter foundReportConverter;

    FoundReportService(FoundReportRepository repository, CategoryService categoryService, UserService userService, FoundReportConverter foundReportConverter) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.userService = userService;
        this.foundReportConverter = foundReportConverter;
    }


    public List<FoundReportDtoResponse> getAllFoundReports() {
        return repository.findAll().stream().map(foundReportConverter::convertToDtoResponse).toList();
    }

    public FoundReportDtoResponse createFoundReport(FoundReportDtoRequest foundReportDtoRequest) {
        return foundReportConverter.convertToDtoResponse(
                repository.save(foundReportConverter.convertToEntity(foundReportDtoRequest, userService.getCurrentUserEntity()))
        );
    }

    public FoundReportDtoResponse getFoundReport(Long id) {

        return foundReportConverter.convertToDtoResponse(
                repository.findById(id)
                .orElseThrow(ReportNotFoundException::new)
        );
    }

    public FoundReportDtoResponse replaceFoundReport(FoundReportDtoRequest newFoundReport, Long id) {

        return foundReportConverter.convertToDtoResponse(
                repository.findById(id)
                .map(report -> {
                    report.setTitle(newFoundReport.getTitle());
                    report.setDescription(newFoundReport.getDescription());
                    report.setImagePath(newFoundReport.getImagePath());
                    report.setFinished(newFoundReport.isFinished());
                    try {
                        report.setCategory(categoryService.getCategory(newFoundReport.getCategoryId()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    report.setFoundDate(newFoundReport.getFoundDate());
                    report.setFoundLocation(newFoundReport.getFoundLocation());
                    report.setCurrentLocation(newFoundReport.getCurrentLocation());
                    return repository.save(report);
                })
                .orElseThrow(ReportNotFoundException::new)
        );
    }

    public void deleteFoundReport(Long id) {
        repository.deleteById(id);
    }

}
