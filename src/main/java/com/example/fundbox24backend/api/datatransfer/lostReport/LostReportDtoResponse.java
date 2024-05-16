package com.example.fundbox24backend.api.datatransfer.lostReport;

import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoResponse;
import com.example.fundbox24backend.api.model.Category;
import com.example.fundbox24backend.api.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LostReportDtoResponse
{
    @JsonProperty("id") private Long id;
    @JsonProperty("title") private String title;
    @JsonProperty("description") private String description;
    @JsonProperty("imagePath") private String imagePath;
    @JsonProperty("createdAt") private LocalDateTime createdAt;
    @JsonProperty("isFinished") private Boolean isFinished;
    @JsonProperty("category") private Category category;
    @JsonProperty("lastSeenDate") private LocalDateTime lastSeenDate;
    @JsonProperty("lastSeenLocation") private Location lastSeenLocation;
    @JsonProperty("lostLocation") private Location lostLocation;
    @JsonProperty("lostRadius") private double lostRadius;
    @JsonProperty("myChats") private List<ChatDtoResponse> myChats;

    public LostReportDtoResponse(Long id, String title, String description, String imagePath, LocalDateTime createdAt, boolean finished, Category category, LocalDateTime lastSeenDate, Location lastSeenLocation, Location lostLocation, double lostRadius, List<ChatDtoResponse> myChats)
    {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.createdAt = createdAt;
        this.isFinished = finished;
        this.category = category;
        this.lastSeenDate = lastSeenDate;
        this.lastSeenLocation = lastSeenLocation;
        this.lostLocation = lostLocation;
        this.lostRadius = lostRadius;
        this.myChats = myChats;
    }
}
