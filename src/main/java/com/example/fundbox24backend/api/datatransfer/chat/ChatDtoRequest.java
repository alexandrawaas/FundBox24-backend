package com.example.fundbox24backend.api.datatransfer.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatDtoRequest
{
    @JsonProperty("reportId") private Long reportId;
}
