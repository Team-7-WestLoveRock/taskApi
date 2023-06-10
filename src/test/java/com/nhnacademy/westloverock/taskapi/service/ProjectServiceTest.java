package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.dto.ProjectUpdateRequest;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    private Project project;
    private ProjectDto projectDto;
    private ProjectUpdateRequest projectUpdateRequest;

    @BeforeEach
    public void setup() {
        project = new Project();
        project.setId(1);
        project.setName("Test Project");
        project.setDescription("This is a test project.");
        project.setCreateAt(LocalDateTime.now());
        project.setState("진행");

        projectDto = new ProjectDto();
        projectDto.setId(1);
        projectDto.setName("Test Project");
        projectDto.setDescription("This is a test project.");

        projectUpdateRequest = new ProjectUpdateRequest();
        projectUpdateRequest.setName("Updated Test Project");
        projectUpdateRequest.setDescription("This is an updated test project.");
        projectUpdateRequest.setState("완료");
    }

    @Test
    void createProjectTest() {
        // given
        ProjectDto projectDto = new ProjectDto();
        projectDto.setName("Test Project");
        projectDto.setDescription("This is a test project.");

        Project testProject = new Project();
        testProject.setId(1);
        testProject.setName("Test Project");
        testProject.setDescription("This is a test project.");
        testProject.setCreateAt(LocalDateTime.now());
        testProject.setState("진행");

        given(projectRepository.save(any(Project.class))).willReturn(testProject);
        // when
        ProjectDto createdProject = projectService.createProject(projectDto);

        // then
        assertEquals(createdProject.getName(), projectDto.getName());
        assertEquals(createdProject.getDescription(), projectDto.getDescription());
    }


    @Test
    void findAllProjectsTest() {
        given(projectRepository.findAll()).willReturn(Arrays.asList(project));

        List<ProjectDto> projectDtos = projectService.findAllProjects();
        assertEquals(1, projectDtos.size());
        assertEquals(projectDto.getName(), projectDtos.get(0).getName());
    }

    @Test
    void findProjectByIdTest() {
        given(projectRepository.findProjectById(1)).willReturn(Optional.of(project));

        ProjectDto foundProjectDto = projectService.findProjectById(1);
        assertEquals(foundProjectDto.getName(), projectDto.getName());
    }

    @Test
    void updateProjectTest() {
        given(projectRepository.findById(1)).willReturn(Optional.of(project));

        projectService.updateProject(1, projectUpdateRequest);
        verify(projectRepository).save(project);
    }

    @Test
    void deleteProjectTest() {
        projectService.deleteProject(1);
        verify(projectRepository).deleteById(1);
    }
}
