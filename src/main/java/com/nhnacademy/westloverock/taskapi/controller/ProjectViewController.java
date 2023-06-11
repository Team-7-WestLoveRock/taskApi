package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.dto.ProjectUpdateRequest;
import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import com.nhnacademy.westloverock.taskapi.service.ProjectService;
import com.nhnacademy.westloverock.taskapi.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectViewController {

    private final ProjectService projectService;
    private final TagService tagService;

    public ProjectViewController(ProjectService projectService, TagService tagService) {
        this.projectService = projectService;
        this.tagService = tagService;
    }

    @GetMapping
    public String findAllProjects(Model model) {
        List<ProjectDto> projectList = projectService.findAllProjects();
        model.addAttribute("projectList", projectList);
        return "project_list";
    }

    @GetMapping("/{id}")
    public String findProjectById(@PathVariable String id, Model model) {
        try {
            int projectId = Integer.parseInt(id);
            ProjectDto projectDto = projectService.findProjectById(projectId);
            List<TagDto> tagList = tagService.findByProjectId(projectId); // assuming you have such a method in your TagService
            model.addAttribute("project", projectDto);
            model.addAttribute("tags", tagList); // it's 'tags', not 'tag'
            model.addAttribute("newTag", new TagDto()); // if you want to add a new tag
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
            ProjectDto projectDto = projectService.findProjectById(projectId);
            model.addAttribute("project", projectDto);
            return "project_edit_form";
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @PutMapping("/{id}")
    public String updateProject(@PathVariable String id, @ModelAttribute("project") ProjectUpdateRequest projectUpdateRequest) {
        try {
            int projectId = Integer.parseInt(id);
            projectService.updateProject(projectId, projectUpdateRequest);
            return "redirect:/project/" + projectId;
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
    @PostMapping("/{projectId}/tag")
    public String createTag(@PathVariable String projectId, @ModelAttribute("tag") TagDto tagDto) {
        try {
            int projectIdInt = Integer.parseInt(projectId);
            tagDto.setProjectId(projectIdInt);
            tagService.createTag(projectIdInt, tagDto);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        }
    }
    @GetMapping("/{id}/tag/new")
    public String newTagForm(@PathVariable String id, Model model) {
        try {
            int projectId = Integer.parseInt(id);
            model.addAttribute("tag", new TagDto());
            model.addAttribute("projectId", projectId);
            return "tag_form";
        } catch (NumberFormatException e) {
            return "error";
        }
    }
}
