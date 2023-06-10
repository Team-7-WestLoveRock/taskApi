package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.dto.ProjectUpdateRequest;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    public ProjectDto createProject(ProjectDto projectDto) {
        if (StringUtils.isEmpty(projectDto.getName()) || StringUtils.isEmpty(projectDto.getDescription())) {
            throw new IllegalArgumentException("프로젝트는 이름과 설명이 필요합니다.");
        }

        Project project = new Project(projectDto.getName(), projectDto.getDescription(), "진행", LocalDateTime.now());

        Project savedProject = projectRepository.save(project);
        return savedProject.toDto();
    }
    public List<ProjectDto> findAllProjects() {
        return projectRepository.findAll().stream()
                .map(Project::toDto)
                .collect(Collectors.toList());
    }

    public ProjectDto findProjectById(int id) {
        return projectRepository.findProjectById(id)
                .map(Project::toDto)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트 id : " + id + "번을 찾을 수 없습니다."));
    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(int id, ProjectUpdateRequest newProjectData) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트 id : " + id + "번을 찾을 수 없습니다."));
        project.update(newProjectData.getName(), newProjectData.getDescription(), newProjectData.getState(), LocalDateTime.now());

        projectRepository.save(project);
    }

}