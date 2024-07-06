package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportDtoRequest;
import com.example.fundbox24backend.api.datatransfer.foundReport.FoundReportDtoResponse;
import com.example.fundbox24backend.api.service.FoundReportService;
import com.example.fundbox24backend.api.service.exceptions.ReportNotFoundException;
import com.example.fundbox24backend.api.model.FoundReport;
import com.example.fundbox24backend.api.repository.FoundReportRepository;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
class FoundReportController {

    private final FoundReportService foundReportService;

    FoundReportController(FoundReportService foundReportService) {
        this.foundReportService = foundReportService;
    }


    @GetMapping("/report/found")
    List<FoundReportDtoResponse> getAllFoundReports(@RequestParam @Nullable String q, @RequestParam @Nullable String category, @RequestParam @Nullable String sort) {
        return foundReportService.getAllFoundReports(q, category, sort);
    }

    @PostMapping("/report/found")
    @ResponseStatus(HttpStatus.CREATED)
    FoundReportDtoResponse createFoundReport(@RequestBody FoundReportDtoRequest newFoundReport) {
        return foundReportService.createFoundReport(newFoundReport);
    }

    // Single item

    @GetMapping("/report/found/{id}")
    FoundReportDtoResponse getFoundReport(@PathVariable Long id) {

        try {
            return foundReportService.getFoundReport(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/report/found/{id}")
    FoundReportDtoResponse replaceFoundReport(@RequestBody FoundReportDtoRequest newFoundReport, @PathVariable Long id) {
        try {
           return foundReportService.replaceFoundReport(newFoundReport, id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/report/found/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteFoundReport(@PathVariable Long id) {
        foundReportService.deleteFoundReport(id);
    }
}
