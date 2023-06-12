package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import com.nhnacademy.westloverock.taskapi.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class TagControllerTest {

    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(tagController).build();
    }

    @Test
    public void createTagTest() throws Exception {
        given(tagService.createTag(anyInt(), any(TagDto.class))).willReturn(new TagDto());

        mockMvc.perform(post("/project/api/tags/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void findAllTagsTest() throws Exception {
        List<TagDto> tagDtoList = Arrays.asList(new TagDto(), new TagDto());
        given(tagService.findAllTags()).willReturn(tagDtoList);

        mockMvc.perform(get("/project/api/tags")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findTagByIdTest() throws Exception {
        given(tagService.findTagById(anyInt())).willReturn(new TagDto());

        mockMvc.perform(get("/project/api/tags/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateTagTest() throws Exception {
        mockMvc.perform(put("/project/api/tags/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTagTest() throws Exception {
        mockMvc.perform(delete("/project/api/tags/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
