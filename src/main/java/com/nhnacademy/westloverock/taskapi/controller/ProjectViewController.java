package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.dto.ProjectUpdateRequest;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectViewController {

    private final ProjectService projectService;

    public ProjectViewController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String getAllProjects(Model model) {
        List<Project> projects = projectService.getProjects();
        model.addAttribute("projects", projects);
        return "project_list";
    }

    @GetMapping("/{id}")
    public String getProjectById(@PathVariable String id, Model model) {
        try {
            int projectId = Integer.parseInt(id);
            Project project = projectService.getProjectById(projectId);
            model.addAttribute("project", project);
            return "project_detail";
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @GetMapping("/new")
    public String newProjectForm(Model model) {
        model.addAttribute("project", new ProjectDto());
        return "project_form";
    }

    @PostMapping
    public String createProject(@ModelAttribute("project") ProjectDto projectDto) {
        projectService.createProject(projectDto);
        return "redirect:/project";
    }

    @GetMapping("/{id}/edit")
    public String editProjectForm(@PathVariable String id, Model model) {
        try {
            int projectId = Integer.parseInt(id);
            Project project = projectService.getProjectById(projectId);
            model.addAttribute("project", project);
            return "project_form";
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @PutMapping("/{id}")
    public String updateProject(@PathVariable String id, @ModelAttribute("project") ProjectUpdateRequest projectUpdateRequest) {
        try {
            int projectId = Integer.parseInt(id);
            projectService.updateProject(projectId, projectUpdateRequest);
            return "redirect:/project/" + id;
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteProject(@PathVariable String id) {
        try {
            int projectId = Integer.parseInt(id);
            projectService.deleteProject(projectId);
            return "redirect:/project";
        } catch (NumberFormatException e) {
            return "error";
        }
    }
}