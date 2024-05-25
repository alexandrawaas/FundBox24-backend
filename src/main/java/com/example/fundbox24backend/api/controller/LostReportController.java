package com.example.fundbox24backend.api.controller;


import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportDtoRequest;
import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportDtoResponse;
import com.example.fundbox24backend.api.service.LostReportService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public
class LostReportController {

    private final LostReportService lostReportService;

    LostReportController(LostReportService lostReportService) {
        this.lostReportService = lostReportService;
    }


    @GetMapping("/report/lost")
    List<LostReportDtoResponse> getAllLostReports() {
        return lostReportService.getAllLostReports();
    }

    @PostMapping("/report/lost")
    @ResponseStatus(HttpStatus.CREATED)
    LostReportDtoResponse createLostReport(@RequestBody LostReportDtoRequest newLostReport) {
        return lostReportService.createLostReport(newLostReport);
    }

    // Single item

    @GetMapping("/report/lost/{id}")
    LostReportDtoResponse getLostReport(@PathVariable Long id) {

        try {
            return lostReportService.getLostReport(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PutMapping("/report/lost/{id}")
    LostReportDtoResponse replaceLostReport(@RequestBody LostReportDtoRequest newLostReport, @PathVariable Long id) {
        try {
            return lostReportService.replaceLostReport(newLostReport, id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @DeleteMapping("/report/lost/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteLostReport(@PathVariable Long id) {
        lostReportService.deleteLostReport(id);
    }
}
