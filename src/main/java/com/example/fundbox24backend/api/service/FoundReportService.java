package com.example.fundbox24backend.api.service;

import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportConverter;
import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportDtoRequest;
import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportDtoResponse;
import com.example.fundbox24backend.api.model.FoundReport;
import com.example.fundbox24backend.api.repository.FoundReportRepository;
import com.example.fundbox24backend.api.service.exceptions.ReportNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<FoundReportDtoResponse> getAllFoundReports(String q, String filterCategory, String sort) {
        // TODO: Filter für location implementieren
        List<FoundReport> results = repository.findAll();
        ArrayList<FoundReport> filteredResults = new ArrayList<>();
        ArrayList<FoundReport> finalResults = new ArrayList<>();
        if(q != null && !q.isEmpty()) {
            filteredResults.addAll(results.stream().filter(report -> report.getTitle().toLowerCase().contains(q.toLowerCase()) || report.getDescription().toLowerCase().contains(q.toLowerCase())).toList());
        }
        else {
            filteredResults.addAll(results);
        }
        if(filterCategory != null && !filterCategory.isEmpty()) {
            finalResults.addAll(filteredResults.stream().filter(report -> report.getCategory().getId().toString().equals(filterCategory)).toList());
        }
        else {
            finalResults.addAll(filteredResults);
        }
        if(sort != null && !sort.isEmpty()) {
            if(sort.equals("alphabetical")) {
                finalResults.sort((o1, o2) -> o1.getTitle().toLowerCase().compareTo(o2.getTitle().toLowerCase()));
            }
            if(sort.equals("found-date")) {
                finalResults.sort((o1, o2) -> o2.getFoundDate().compareTo(o1.getFoundDate()));
            }
        }
        return finalResults.stream().map(foundReportConverter::convertToDtoResponse).toList();
    }

    public FoundReportDtoResponse createFoundReport(FoundReportDtoRequest foundReportDtoRequest) {
        FoundReport report = repository.save(foundReportConverter.convertToEntity(foundReportDtoRequest, userService.getCurrentUserEntity()));
        userService.addFoundReport(report);
        return foundReportConverter.convertToDtoResponse(report);
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
