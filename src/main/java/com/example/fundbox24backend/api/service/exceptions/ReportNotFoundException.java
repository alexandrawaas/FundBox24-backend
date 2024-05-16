package com.example.fundbox24backend.api.service.exceptions;

public class ReportNotFoundException extends RuntimeException {

    public ReportNotFoundException() {
        super("Could not find report");
    }
}