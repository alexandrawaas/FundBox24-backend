package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.controller.exceptions.ReportNotFoundException;
import com.example.fundbox24backend.api.model.LostReport;
import com.example.fundbox24backend.api.repository.LostReportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class LostReportController {

    private final LostReportRepository repository;

    LostReportController(LostReportRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/report/lost")
    List<LostReport> all() {
        return repository.findAll();
    }

    @PostMapping("/report/lost")
    LostReport newLostReport(@RequestBody LostReport newLostReport) {
        return repository.save(newLostReport);
    }

    // Single item

    @GetMapping("/report/lost/{id}")
    LostReport one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(ReportNotFoundException::new);
    }

    @PutMapping("/report/lost/{id}")
    LostReport replaceLostReport(@RequestBody LostReport newLostReport, @PathVariable Long id) {

        return repository.findById(id)
                .map(report -> {
                    report.setTitle(newLostReport.getTitle());
                    report.setDescription(newLostReport.getDescription());
                    report.setImagePath(newLostReport.getImagePath());
                    report.setCreatedAt(newLostReport.getCreatedAt());
                    report.setFinished(newLostReport.isFinished());
                    report.setCategory(newLostReport.getCategory());
                    report.setChats(newLostReport.getChats());
                    report.setLastSeenDate(newLostReport.getLastSeenDate());
                    report.setLastSeenLocation(newLostReport.getLastSeenLocation());
                    report.setLostRadius(newLostReport.getLostRadius());
                    return repository.save(report);
                })
                .orElseGet(() -> {
                    newLostReport.setId(id);
                    return repository.save(newLostReport);
                });
    }

    @DeleteMapping("/report/lost/{id}")
    void deleteLostReport(@PathVariable Long id) {
        repository.deleteById(id);
    }
}