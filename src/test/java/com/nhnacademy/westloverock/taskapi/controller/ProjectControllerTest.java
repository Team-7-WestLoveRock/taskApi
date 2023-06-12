package com.nhnacademy.westloverock.taskapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.dto.ProjectUpdateRequest;
import com.nhnacademy.westloverock.taskapi.service.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("dev")
@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @MockBean
    private ProjectService projectService;

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void testCreateProject() throws Exception {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project");
        projectDto.setDescription("This is a test project.");

        given(projectService.createProject(any(ProjectDto.class))).willReturn(projectDto);

        mockMvc.perform(post("/project/api/projects")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(projectDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(projectDto)));
    }

    @Test
    void testFindAllProjects() throws Exception {
        ProjectDto project1 = new ProjectDto();
        project1.setName("Test Project 1");
        project1.setDescription("This is a test project.");

        ProjectDto project2 = new ProjectDto();
        project2.setName("Test Project 2");
        project2.setDescription("This is another test project.");

        given(projectService.findAllProjects()).willReturn(Arrays.asList(project1, project2));

        mockMvc.perform(get("/project/api/projects")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Arrays.asList(project1, project2))));
    }

    @Test
    void testFindProjectById() throws Exception {
        int id = 1;
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project");
        projectDto.setDescription("This is a test project.");

        given(projectService.findProjectById(id)).willReturn(projectDto);

        mockMvc.perform(get("/project/api/projects/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(projectDto)));
    }

    @Test
    void testDeleteProject() throws Exception {
        int id = 1;

        mockMvc.perform(delete("/project/api/projects/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(projectService, times(1)).deleteProject(id);
    }

    @Test
    void testUpdateProject() throws Exception {
        int id = 1;
        ProjectUpdateRequest newProjectData = new ProjectUpdateRequest();
        newProjectData.setName("Updated Project");
        newProjectData.setDescription("This is an updated project.");

        mockMvc.perform(put("/project/api/projects/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newProjectData)))
                .andExpect(status().isOk());

        verify(projectService, times(1)).updateProject(eq(id), any(ProjectUpdateRequest.class));
    }
}
