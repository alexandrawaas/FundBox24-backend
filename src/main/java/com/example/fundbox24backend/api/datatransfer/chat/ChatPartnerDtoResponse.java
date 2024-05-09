package com.example.fundbox24backend.api.datatransfer.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class ChatPartnerDtoResponse
{
    @NotNull @JsonProperty("id") private Long id;
    @NotNull @JsonProperty("username") private String username;

    public ChatPartnerDtoResponse(Long id, String name)
    {
        this.id = id;
        this.username = name;
    }
}
