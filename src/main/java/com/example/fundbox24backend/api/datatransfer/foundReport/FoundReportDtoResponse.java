package com.example.fundbox24backend.api.datatransfer.foundReport;

import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoResponse;
import com.example.fundbox24backend.api.model.Category;
import com.example.fundbox24backend.api.model.Location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class FoundReportDtoResponse
{
    @JsonProperty("id") private Long id;
    @JsonProperty("title") private String title;
    @JsonProperty("description") private String description;
    @JsonProperty("imagePath") private String imagePath;
    @JsonProperty("createdAt") private LocalDateTime createdAt;
    @JsonProperty("isFinished") private Boolean isFinished;
    @JsonProperty("category") private Category category;
    @JsonProperty("foundDate") private LocalDateTime foundDate;
    @JsonProperty("foundLocation") private Location foundLocation;
    @JsonProperty("currentLocation") private Location currentLocation;
    // TODO: currentLocation für alle verschleiern außer den creator
    @JsonProperty("myChats") private List<ChatDtoResponse> myChats;

}
