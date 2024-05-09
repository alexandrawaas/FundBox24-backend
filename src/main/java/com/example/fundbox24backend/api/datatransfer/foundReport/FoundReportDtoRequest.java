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
    @JsonProperty("foundDate") private LocalDateTime foundDate;
    @JsonProperty("foundLocation") private Location foundLocation;
    @JsonProperty("currentLocation") private Location currentLocation;
}
