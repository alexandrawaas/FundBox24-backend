package com.example.fundbox24backend.api.datatransfer.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatPartnerDtoResponse
{
    @JsonProperty("id") private Long id;
    @JsonProperty("username") private String username;
}
