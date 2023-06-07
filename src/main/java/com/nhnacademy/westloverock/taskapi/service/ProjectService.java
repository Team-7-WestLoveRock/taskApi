package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.dto.ProjectUpdateRequest;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(ProjectDto projectDto) {
        if (StringUtils.isEmpty(projectDto.getName()) || StringUtils.isEmpty(projectDto.getDescription())) {
            throw new IllegalArgumentException("프로젝트는 이름과 설명이 필요합니다.");
        }

        Project project = new Project();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setCreateAt(LocalDateTime.now());
        project.setState("진행");

        return projectRepository.save(project);
    }


    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("프로젝트 id : " + id + "번을 찾을 수 없습니다."));
    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }

    public Project updateProject(int id, ProjectUpdateRequest newProjectData) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(newProjectData.getName());
                    project.setDescription(newProjectData.getDescription());
                    project.setState(newProjectData.getState());
                    project.setCreateAt(newProjectData.getCreateAt());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new IllegalArgumentException("프로젝트 id : " + id + "번을 찾을 수 없습니다."));
    }
}
