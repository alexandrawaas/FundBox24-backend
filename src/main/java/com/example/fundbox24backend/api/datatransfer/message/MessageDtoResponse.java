package com.example.fundbox24backend.api.datatransfer.message;

import com.example.fundbox24backend.api.datatransfer.chat.ChatPartnerDtoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDtoResponse
{
    @JsonProperty("id") private Long id;
    @JsonProperty("isImage") private Boolean isImage;
    @JsonProperty("content") private String content;
    @JsonProperty("sentAt") private LocalDateTime sentAt;
    @JsonProperty("sender") private ChatPartnerDtoResponse sender;
}
