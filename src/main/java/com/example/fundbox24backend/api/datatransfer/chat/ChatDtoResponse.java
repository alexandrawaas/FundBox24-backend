package com.example.fundbox24backend.api.datatransfer.chat;

import com.example.fundbox24backend.api.datatransfer.message.MessageDtoResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChatDtoResponse
{
    @JsonProperty("id") private Long id;
    @JsonProperty("reportId") private Long reportId;
    @JsonProperty("reportTitle") private String reportTitle;
    @JsonProperty("messages") private List<MessageDtoResponse> messages;
    @JsonProperty("reportVisitor") private ChatPartnerDtoResponse reportVisitor;
    @JsonProperty("reportCreator") private ChatPartnerDtoResponse reportCreator;

    public ChatDtoResponse(Long id, Long reportId, String reportTitle, List<MessageDtoResponse> messages, ChatPartnerDtoResponse reportVisitor, ChatPartnerDtoResponse reportCreator)
    {
        this.id = id;
        this.reportId = reportId;
        this.reportTitle = reportTitle;
        this.messages = messages;
        this.reportVisitor = reportVisitor;
        this.reportCreator = reportCreator;
    }
}
