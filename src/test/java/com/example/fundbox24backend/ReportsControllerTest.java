package com.example.fundbox24backend;

import com.example.fundbox24backend.api.controller.LostReportController;
import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportDtoRequest;
import com.example.fundbox24backend.api.datatransfer.lostReport.LostReportDtoResponse;
import com.example.fundbox24backend.api.model.Category;
import com.example.fundbox24backend.api.model.Location;
import com.example.fundbox24backend.api.model.ValueType;
import com.example.fundbox24backend.api.service.CategoryService;
import com.example.fundbox24backend.api.service.LostReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(LostReportController.class)
class ReportsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LostReportService service;

    @MockBean
    private CategoryService categoryService;

    private LostReportDtoRequest lostReportDtoRequest;
    private LostReportDtoResponse lostReportDtoResponse;


    protected String mapToJson(Object obj)
    {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
                .create();
        return gson.toJson(obj);
    }
    void setup() {
        Category category = new Category("Bag", ValueType.LOW);
        when(categoryService.createCategory(any(Category.class))).thenReturn(category);
        lostReportDtoRequest = new LostReportDtoRequest(
                "Red Bag",
                "Description",
                "imagePath",
                category.getId(),
                false,
                LocalDateTime.now(),
                new Location(1.0, 1.0),
                new Location(1.0, 1.0),
                1.0
        );
        lostReportDtoResponse = new LostReportDtoResponse(
                1L,
                "Red Bag",
                "Description",
                "imagePath",
                LocalDateTime.now(),
                false,
                category,
                LocalDateTime.now(),
                new Location(1.0, 1.0),
                new Location(1.0, 1.0),
                1.0
        );

    }

    @Test
    void testPostLostReport() throws Exception {
        setup();
        when(service.createLostReport(lostReportDtoRequest)).thenReturn(lostReportDtoResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/report/lost").contentType(MediaType.APPLICATION_JSON_VALUE).content(mapToJson(lostReportDtoRequest))).andDo(print()).andExpect(status().isCreated())
                .andExpect(content().contentType("application/json")).andExpect(content().string(containsString("Red Bag")));
    }

    @Test
    void testGetLostReport() throws Exception {
        setup();
        when(service.getLostReport(1L)).thenReturn(lostReportDtoResponse);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/report/lost/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().contentType("application/json")).andExpect(content().string(containsString("Red Bag")));
    }
}
