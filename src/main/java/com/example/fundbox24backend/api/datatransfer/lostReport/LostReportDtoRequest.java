package com.example.fundbox24backend.api.datatransfer.lostReport;

import com.example.fundbox24backend.api.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LostReportDtoRequest
{
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("imagePath")
    private String imagePath;
    @JsonProperty("categoryId")
    private Long categoryId;
    @JsonProperty("isFinished")
    private boolean isFinished;
    @JsonProperty("lastSeenDate")
    private LocalDateTime lastSeenDate;
    @JsonProperty("lastSeenLocation")
    private Location lastSeenLocation;
    @JsonProperty("lostLocation")
    private Location lostLocation;
    @JsonProperty("lostRadius")
    private double lostRadius;


    public LostReportDtoRequest(String title, String description, String imagePath, Long categoryId, boolean isFinished, LocalDateTime lastSeenDate, Location lastSeenLocation, Location lostLocation, double lostRadius)
    {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.categoryId = categoryId;
        this.isFinished = isFinished;
        this.lastSeenDate = lastSeenDate;
        this.lastSeenLocation = lastSeenLocation;
        this.lostLocation = lostLocation;
        this.lostRadius = lostRadius;
    }
}

