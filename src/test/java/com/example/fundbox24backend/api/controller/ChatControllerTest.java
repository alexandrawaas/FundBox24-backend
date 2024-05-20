package com.example.fundbox24backend.api.controller;

import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoRequest;
import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoResponse;
import com.example.fundbox24backend.api.datatransfer.chat.ChatPartnerDtoResponse;
import com.example.fundbox24backend.api.datatransfer.message.MessageDtoRequest;
import com.example.fundbox24backend.api.datatransfer.message.MessageDtoResponse;
import com.example.fundbox24backend.api.service.ChatService;
import com.example.fundbox24backend.api.service.exceptions.ChatNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @InjectMocks
    private ChatController chatController;

    private MockMvc mockMvc;

    AutoCloseable openMocks;

    private static final Logger logger = LoggerFactory.getLogger(ChatControllerTest.class);

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(chatController).build();
    }

    @AfterEach
    void tearDown() {
        try {
            openMocks.close();
        } catch (Exception e) {
            logger.error("Error occurred while closing mocks", e);
        }
    }

    @Test
    void createChatTest() throws Exception {
        ChatDtoRequest chatDtoRequest = new ChatDtoRequest();
        ChatDtoResponse chatDtoResponse = new ChatDtoResponse(
                1L,
                1L,
                "Test Report Title",
                Collections.emptyList(),
                new ChatPartnerDtoResponse(1L, "Visitor"),
                new ChatPartnerDtoResponse(2L, "Creator")
        );

        when(chatService.createChat(chatDtoRequest)).thenReturn(chatDtoResponse);

        mockMvc.perform(post("/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"reportId\": 1, \"reportVisitorId\": 1, \"reportCreatorId\": 1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(chatDtoResponse.getId()))
                .andExpect(jsonPath("$.reportId").value(chatDtoResponse.getReportId()))
                .andExpect(jsonPath("$.reportTitle").value(chatDtoResponse.getReportTitle()))
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.reportVisitor.id").value(chatDtoResponse.getReportVisitor().getId()))
                .andExpect(jsonPath("$.reportVisitor.name").value(chatDtoResponse.getReportVisitor().getUsername()))
                .andExpect(jsonPath("$.reportCreator.id").value(chatDtoResponse.getReportCreator().getId()))
                .andExpect(jsonPath("$.reportCreator.name").value(chatDtoResponse.getReportCreator().getUsername()));

        verify(chatService, times(1)).createChat(any(ChatDtoRequest.class));
    }

    @Test
    void getChatTest() throws Exception {
        Long chatId = 1L;
        ChatDtoResponse chatDtoResponse = new ChatDtoResponse(
                chatId,
                1L,
                "Test Report Title",
                Collections.emptyList(),
                new ChatPartnerDtoResponse(1L, "Visitor"),
                new ChatPartnerDtoResponse(2L, "Creator")
        );

        when(chatService.getChat(chatId)).thenReturn(chatDtoResponse);

        mockMvc.perform(get("/chat/{chatId}", chatId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(chatDtoResponse.getId()))
                .andExpect(jsonPath("$.reportId").value(chatDtoResponse.getReportId()))
                .andExpect(jsonPath("$.reportTitle").value(chatDtoResponse.getReportTitle()))
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.reportVisitor.id").value(chatDtoResponse.getReportVisitor().getId()))
                .andExpect(jsonPath("$.reportVisitor.name").value(chatDtoResponse.getReportVisitor().getUsername()))
                .andExpect(jsonPath("$.reportCreator.id").value(chatDtoResponse.getReportCreator().getId()))
                .andExpect(jsonPath("$.reportCreator.name").value(chatDtoResponse.getReportCreator().getUsername()));

        verify(chatService, times(1)).getChat(chatId);
    }

    @Test
    void getChatNotFoundTest() throws Exception {
        Long chatId = 1L;

        when(chatService.getChat(chatId)).thenThrow(new ChatNotFoundException());

        mockMvc.perform(get("/chat/{chatId}", chatId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(chatService, times(1)).getChat(chatId);
    }

    @Test
    void addMessageTest() throws Exception {
        Long chatId = 1L;
        MessageDtoRequest messageDtoRequest = new MessageDtoRequest();
        ChatPartnerDtoResponse chatPartnerDtoResponse = new ChatPartnerDtoResponse(1L, "User");
        LocalDateTime sentAt = LocalDateTime.now();

        MessageDtoResponse messageDtoResponse = new MessageDtoResponse(
                1L,
                "This is a test message",
                false,
                sentAt,
                chatPartnerDtoResponse
        );

        ChatDtoResponse chatDtoResponse = new ChatDtoResponse(
                chatId,
                1L,
                "Test Report Title",
                Collections.singletonList(messageDtoResponse),
                new ChatPartnerDtoResponse(1L, "Visitor"),
                new ChatPartnerDtoResponse(2L, "Creator")
        );

        when(chatService.addMessage(any(messageDtoRequest.getClass()), eq(chatId))).thenReturn(chatDtoResponse);

        mockMvc.perform(patch("/chat/{chatId}", chatId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"This is a test message\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(chatDtoResponse.getId()))
                .andExpect(jsonPath("$.reportId").value(chatDtoResponse.getReportId()))
                .andExpect(jsonPath("$.reportTitle").value(chatDtoResponse.getReportTitle()))
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.messages[0].id").value(messageDtoResponse.getId()))
                .andExpect(jsonPath("$.messages[0].content").value(messageDtoResponse.getContent()))
                .andExpect(jsonPath("$.messages[0].isImage").value(messageDtoResponse.getIsImage()))
                .andExpect(jsonPath("$.messages[0].sentAt").value(messageDtoResponse.getSentAt().toString()))
                .andExpect(jsonPath("$.messages[0].chatPartnerDtoResponse.id").value(messageDtoResponse.getSender().getId()))
                .andExpect(jsonPath("$.messages[0].chatPartnerDtoResponse.name").value(messageDtoResponse.getSender().getUsername()))
                .andExpect(jsonPath("$.reportVisitor.id").value(chatDtoResponse.getReportVisitor().getId()))
                .andExpect(jsonPath("$.reportVisitor.name").value(chatDtoResponse.getReportVisitor().getUsername()))
                .andExpect(jsonPath("$.reportCreator.id").value(chatDtoResponse.getReportCreator().getId()))
                .andExpect(jsonPath("$.reportCreator.name").value(chatDtoResponse.getReportCreator().getUsername()));

        verify(chatService, times(1)).addMessage(any(messageDtoRequest.getClass()), eq(chatId));
    }

    @Test
    void addMessageChatNotFoundTest() throws Exception {
        Long chatId = 1L;
        MessageDtoRequest messageDtoRequest = new MessageDtoRequest();

        when(chatService.addMessage(any(messageDtoRequest.getClass()), eq(chatId))).thenThrow(new ChatNotFoundException());

        mockMvc.perform(patch("/chat/{chatId}", chatId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"text\":\"This is a test message\"}"))
                .andExpect(status().isNotFound());

        verify(chatService, times(1)).addMessage(any(messageDtoRequest.getClass()), eq(chatId));
    }
}
