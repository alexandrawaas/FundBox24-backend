package com.example.fundbox24backend.api.datatransfer.report;

import com.example.fundbox24backend.api.model.Category;
import com.example.fundbox24backend.api.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

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
    @JsonProperty("lostRadius") private int lostRadius;
    @JsonProperty("myChats") private Long myChats;
}
