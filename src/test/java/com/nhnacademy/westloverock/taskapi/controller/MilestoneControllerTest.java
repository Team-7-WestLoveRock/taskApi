package com.nhnacademy.westloverock.taskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nhnacademy.westloverock.taskapi.dto.CreateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.dto.MilestoneResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.UpdateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.service.MilestoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
class MilestoneControllerTest {
    @Mock
    MilestoneService milestoneService;
    MockMvc mockMvc;
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

        mockMvc = MockMvcBuilders.standaloneSetup(new MilestoneController(milestoneService))
                .build();
    }

    @Test
    @DisplayName("milestones 조회")
    void findAllMilestone() throws Exception {

        MilestoneResponseDto milestoneResponseDto = MilestoneResponseDto.builder()
                .name("qaz")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        List<MilestoneResponseDto> milestoneResponseDtoList = new ArrayList<>();
        milestoneResponseDtoList.add(milestoneResponseDto);

        when(milestoneService.findAllMilestone(any())).thenReturn(milestoneResponseDtoList);

        RequestBuilder request = MockMvcRequestBuilders.get("/project/api/projects/1/milestones")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void createMilestone() throws Exception {
        CreateMilestoneRequest createMilestoneRequest = CreateMilestoneRequest.builder()
                .name("JongMyeong")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now())
                .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/project/api/projects/1/milestone")
                .content(objectMapper.writeValueAsString(createMilestoneRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void updateMilestone() throws Exception {
        UpdateMilestoneRequest updateMilestoneRequest =
                UpdateMilestoneRequest.builder()
                        .name("qwe")
                        .startDate(LocalDate.now())
                        .endDate(LocalDate.now())
                        .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/project/api/projects/1/milestone/1")
                .content(objectMapper.writeValueAsString(updateMilestoneRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void deleteMilestone() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/project/api/projects/milestone/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}