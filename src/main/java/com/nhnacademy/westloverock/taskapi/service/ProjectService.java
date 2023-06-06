package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
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
            throw new IllegalArgumentException("프로젝");
        }

        Project project = new Project();
        project.setName(projectDto.getName());
        project.setDescription(projectDto.getDescription());
        project.setCreateAt(LocalDateTime.now());
        project.setState("보관");

        return projectRepository.save(project);
    }


    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Project with id " + id + " not found."));
    }

    public void deleteProject(int id) {
        projectRepository.deleteById(id);
    }

    public Project updateProject(int id, Project newProjectData) {
        return projectRepository.findById(id)
                .map(project -> {
                    project.setName(newProjectData.getName());
                    project.setDescription(newProjectData.getDescription());
                    project.setState(newProjectData.getState());
                    project.setCreateAt(newProjectData.getCreateAt());
                    return projectRepository.save(project);
                })
                .orElseThrow(() -> new IllegalArgumentException("Project with id " + id + " not found."));
    }
}
