package com.nhnacademy.westloverock.taskapi.controller;
import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.dto.ProjectUpdateRequest;
import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import com.nhnacademy.westloverock.taskapi.dto.TagUpdateRequest;
import com.nhnacademy.westloverock.taskapi.service.ProjectService;
import com.nhnacademy.westloverock.taskapi.service.TagService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
@ActiveProfiles("dev")
@WebMvcTest(ProjectViewController.class)
class ProjectViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProjectService projectService;

    @MockBean
    private TagService tagService;

    @Test
    void testFindAllProjects() throws Exception {
        ProjectDto projectDto1 = new ProjectDto();
        projectDto1.setId(1);
        ProjectDto projectDto2 = new ProjectDto();
        projectDto2.setId(2);

        given(projectService.findAllProjects()).willReturn(Arrays.asList(projectDto1, projectDto2));
        given(tagService.findByProjectId(1)).willReturn(new ArrayList<>());
        given(tagService.findByProjectId(2)).willReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/project"))
                .andExpect(status().isOk())
                .andExpect(view().name("project_list"))
                .andExpect(model().attributeExists("projectList"))
                .andExpect(model().attributeExists("projectTags"));
    }


    @Test
    void testFindProjectById() throws Exception {
        given(projectService.findProjectById(1)).willReturn(new ProjectDto());
        given(tagService.findByProjectId(1)).willReturn(Arrays.asList(new TagDto(), new TagDto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/project/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("project_detail"))
                .andExpect(model().attributeExists("project", "tags", "newTag"));
    }

    @Test
    void testNewProjectForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/project/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("project_form"))
                .andExpect(model().attributeExists("project"));
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

    @Test
    void testCreateTag() throws Exception {
        TagDto tagDto = new TagDto();
        tagDto.setProjectId(1);
        tagDto.setName("Test Tag");

        given(tagService.createTag(anyInt(), any(TagDto.class))).willReturn(tagDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/project/1/tag")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Test Tag"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));
    }

    @Test
    void testNewTagForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/project/1/tag/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag_form"))
                .andExpect(model().attributeExists("tag", "projectId"));
    }

    @Test
    void testEditProjectForm() throws Exception {
        given(projectService.findProjectById(1)).willReturn(new ProjectDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/project/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("project_edit_form"))
                .andExpect(model().attributeExists("project"));
    }

    @Test
    void testUpdateProject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/project/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Updated Project")
                        .param("description", "This is an updated test project."))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(projectService).updateProject(anyInt(), any(ProjectUpdateRequest.class));
    }

    @Test
    void testDeleteProject() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/project/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project"));

        verify(projectService).deleteProject(anyInt());
    }

    @Test
    void testEditTagForm() throws Exception {
        given(tagService.findTagById(1)).willReturn(new TagDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/project/1/tag/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("tag_edit_form"))
                .andExpect(model().attributeExists("tag", "projectId"));
    }

    @Test
    void testUpdateTag() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/project/1/tag/1")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("name", "Updated Tag"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(tagService).updateTag(anyInt(), any(TagUpdateRequest.class));
    }

    @Test
    void testDeleteTag() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/project/1/tag/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/project/1"));

        verify(tagService).deleteTag(anyInt());
    }

}
