package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.controller.exceptions.ReportNotFoundException;
import com.example.fundbox24backend.api.model.FoundReport;
import com.example.fundbox24backend.api.repository.FoundReportRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
class FoundReportController {

    private final FoundReportRepository repository;

    FoundReportController(FoundReportRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/report/found")
    List<FoundReport> all() {
        return repository.findAll();
    }

    @PostMapping("/report/found")
    FoundReport newFoundReport(@RequestBody FoundReport newFoundReport) {
        return repository.save(newFoundReport);
    }

    // Single item

    @GetMapping("/report/found/{id}")
    FoundReport one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(ReportNotFoundException::new);
    }

    @PutMapping("/report/found/{id}")
    FoundReport replaceFoundReport(@RequestBody FoundReport newFoundReport, @PathVariable Long id) {

        return repository.findById(id)
                .map(report -> {
                    report.setTitle(newFoundReport.getTitle());
                    report.setDescription(newFoundReport.getDescription());
                    report.setImagePath(newFoundReport.getImagePath());
                    report.setCreatedAt(newFoundReport.getCreatedAt());
                    report.setFinished(newFoundReport.isFinished());
                    report.setCategory(newFoundReport.getCategory());
                    report.setChats(newFoundReport.getChats());
                    report.setFoundDate(newFoundReport.getFoundDate());
                    report.setFoundLocation(newFoundReport.getFoundLocation());
                    report.setCurrentLocation(newFoundReport.getCurrentLocation());
                    return repository.save(report);
                })
                .orElseGet(() -> {
                    newFoundReport.setId(id);
                    return repository.save(newFoundReport);
                });
    }

    @DeleteMapping("/report/found/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
