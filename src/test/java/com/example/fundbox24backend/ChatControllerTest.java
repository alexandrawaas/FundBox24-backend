package com.example.fundbox24backend;

import com.example.fundbox24backend.api.controller.ChatController;
import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoRequest;
import com.example.fundbox24backend.api.datatransfer.chat.ChatDtoResponse;
import com.example.fundbox24backend.api.service.ChatService;
import com.example.fundbox24backend.api.service.exceptions.ChatNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChatController.class)
class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatService chatService;

    @Test
    void testCreateChat() throws Exception {
        ChatDtoRequest request = new ChatDtoRequest();
        ChatDtoResponse response = new ChatDtoResponse(
                1L,
                1L,
                "Test Report Title",
                Collections.emptyList(),
                null,
                null
        );
        when(chatService.createChat(any(ChatDtoRequest.class))).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.post("/chat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.reportId").value(1))
                .andExpect(jsonPath("$.reportTitle").value("Test Report Title"))
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.messages").isEmpty());
    }

    @Test
    void testGetChat() throws Exception {
        ChatDtoResponse response = new ChatDtoResponse(
                1L,
                1L,
                "Test Report Title",
                Collections.emptyList(),
                null,
                null
        );
        when(chatService.getChat(1L)).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.get("/chat/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.reportId").value(1))
                .andExpect(jsonPath("$.reportTitle").value("Test Report Title"))
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.messages").isEmpty());
    }

    @Test
    void testGetChat_NotFound() throws Exception {
        when(chatService.getChat(1L)).thenThrow(new ChatNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get("/chat/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddMessage() throws Exception {
        ChatDtoRequest request = new ChatDtoRequest();
        ChatDtoResponse response = new ChatDtoResponse(
                1L,
                1L,
                "Test Report Title",
                Collections.emptyList(),
                null,
                null
        );
        when(chatService.addMessage(any(), any())).thenReturn(response);
        mockMvc.perform(MockMvcRequestBuilders.patch("/chat/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.reportId").value(1))
                .andExpect(jsonPath("$.reportTitle").value("Test Report Title"))
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.messages").isEmpty());
    }

    @Test
    void testAddMessage_ChatNotFound() throws Exception {
        when(chatService.addMessage(any(), any())).thenThrow(new ChatNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.patch("/chat/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isNotFound());
    }
}
