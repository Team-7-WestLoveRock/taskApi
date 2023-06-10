package com.nhnacademy.westloverock.taskapi.controller;
import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.service.ProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;

import java.util.Arrays;

@WebMvcTest(ProjectViewController.class)
class ProjectViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @Test
    void testFindAllProjects() throws Exception {
        given(projectService.findAllProjects()).willReturn(Arrays.asList(new ProjectDto(), new ProjectDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/project"))
                .andExpect(status().isOk())
                .andExpect(view().name("project_list"));
    }

    @Test
    void testFindProjectById() throws Exception {
        given(projectService.findProjectById(1)).willReturn(new ProjectDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/project/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("project_detail"));
    }

    @Test
    void testNewProjectForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/project/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("project_form"));
    }

    @Test
    void testCreateProject() throws Exception {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project");
        projectDto.setDescription("This is a test project.");

        given(projectService.createProject(any(ProjectDto.class))).willReturn(projectDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/project")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Test Project")
                        .param("description", "This is a test project."))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project"));
    }

}
