package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto) {
        Project project = projectService.createProject(projectDto);
        return ResponseEntity.ok(project);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> getProjectById(@PathVariable int id) {
        return ResponseEntity.ok(projectService.getProjectById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable int id) {
        projectService.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable int id, @RequestBody Project newProjectData) {
        Project updatedProject = projectService.updateProject(id, newProjectData);
        return ResponseEntity.ok(updatedProject);
    }
}

