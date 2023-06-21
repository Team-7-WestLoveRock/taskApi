package com.nhnacademy.westloverock.taskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.westloverock.taskapi.dto.CommentContentRequest;
import com.nhnacademy.westloverock.taskapi.dto.CommentResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.CommentWrittenDateResponseDto;
import com.nhnacademy.westloverock.taskapi.service.CommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CommentService commentService;
    @Test
    @DisplayName("commentList 조회 - 성공")
    void findCommentList() throws Exception {
        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .userId("test")
                .writtenDate(LocalDateTime.now())
                .content("test")
                .build();

        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();
        commentResponseDtoList.add(commentResponseDto);

        when(commentService.findCommentList(anyInt())).thenReturn(commentResponseDtoList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/project/api/projects/1/task/1/comments")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
        ;
    }

    @Test
    @DisplayName("comment 생성 - 성공")
    void createComment() throws Exception {
        CommentWrittenDateResponseDto commentWrittenDateResponseDto =
                CommentWrittenDateResponseDto.builder()
                        .writtenDate(LocalDateTime.now())
                        .build();

        when(commentService.createComment(any())).thenReturn(commentWrittenDateResponseDto);

        CommentContentRequest commentContentRequest =
                CommentContentRequest.builder()
                        .userId("test")
                        .content("testContent")
                        .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/project/api/projects/1/task/1/comment")
                .content(objectMapper.writeValueAsString(commentContentRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.writtenDate").exists());

    }
    @Test
    @DisplayName("comment 생성 - 실패")
    void createCommentValidationException() throws Exception {
        CommentContentRequest commentContentRequest = CommentContentRequest.builder().build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/project/api/projects/1/task/1/comment")
                .content(objectMapper.writeValueAsString(commentContentRequest))
                .contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> {
            mockMvc.perform(requestBuilder);
        }).hasCauseInstanceOf(ValidationException.class);

    }

    @Test
    @DisplayName("comment 갱신 - 실패")
    void updateCommentValidationException() throws Exception {
        CommentContentRequest commentContentRequest =
                CommentContentRequest.builder().build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/project/api/projects/1/task/1/comment/1")
                .content(objectMapper.writeValueAsString(commentContentRequest))
                .contentType(MediaType.APPLICATION_JSON);

        assertThatThrownBy(() -> {
            mockMvc.perform(requestBuilder);
        }).hasCauseInstanceOf(ValidationException.class);
    }
    @Test
    @DisplayName("comment 갱신 - 성공")
    void updateComment() throws Exception {

        CommentWrittenDateResponseDto commentWrittenDateResponseDto =
                CommentWrittenDateResponseDto.builder()
                        .writtenDate(LocalDateTime.now())
                        .build();

        when(commentService.updateComment(any())).thenReturn(commentWrittenDateResponseDto);

        CommentContentRequest commentContentRequest =
                CommentContentRequest.builder()
                        .userId("test")
                        .content("testContent")
                        .build();

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/project/api/projects/1/task/1/comment/1")
                .content(objectMapper.writeValueAsString(commentContentRequest))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.writtenDate").exists());
    }

    @Test
    @DisplayName("comment 삭제")
    void deleteComment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/project/api/projects/1/task/1/comment/1")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }

}