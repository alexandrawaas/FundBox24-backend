package com.example.fundbox24backend.api.datatransfer.foundReport;

import com.example.fundbox24backend.api.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FoundReportDtoRequest
{
    @JsonProperty("title") private String title;
    @JsonProperty("description") private String description;
    @JsonProperty("imagePath") private String imagePath;
    @JsonProperty("categoryId") private Long categoryId;
    @JsonProperty("isFinished") private boolean isFinished;
    @JsonProperty("foundDate") private LocalDateTime foundDate;
    @JsonProperty("foundLocation") private Location foundLocation;
    @JsonProperty("currentLocation") private Location currentLocation;

    public FoundReportDtoRequest(String title, String description, String imagePath, Long categoryId, boolean isFinished, LocalDateTime foundDate, Location foundLocation, Location currentLocation)
    {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.categoryId = categoryId;
        this.isFinished = isFinished;
        this.foundDate = foundDate;
        this.foundLocation = foundLocation;
        this.currentLocation = currentLocation;
    }

}
