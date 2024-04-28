package com.example.fundbox24backend.api.controller.exceptions;

public class ReportNotFoundException extends RuntimeException {

    public ReportNotFoundException() {
        super("Could not find report");
    }
}