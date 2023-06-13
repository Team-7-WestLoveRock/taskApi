package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.*;
import com.nhnacademy.westloverock.taskapi.service.MilestoneService;
import com.nhnacademy.westloverock.taskapi.service.ProjectService;
import com.nhnacademy.westloverock.taskapi.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectViewController {

    private final ProjectService projectService;
    private final TagService tagService;
    private final MilestoneService milestoneService;

    @GetMapping
    public String findAllProjects(Model model) {
        List<ProjectDto> projectList = projectService.findAllProjects();
        Map<Integer, List<TagDto>> projectTags = new HashMap<>();
        Map<Integer, List<MilestoneResponseDto>> projectMilestones = new HashMap<>();

        for (ProjectDto project : projectList) {
            List<TagDto> tagList = tagService.findByProjectId(project.getId());
            List<MilestoneResponseDto> milestoneList = milestoneService.findByProjectId(project.getId());

            if(tagList == null) {
                tagList = new ArrayList<>();
            }

            projectTags.put(project.getId(), tagList);
            projectMilestones.put(project.getId(), milestoneList);
        }

        model.addAttribute("projectList", projectList);
        model.addAttribute("projectTags", projectTags);
        model.addAttribute("projectMilestones", projectMilestones);

        return "project_list";
    }


    @GetMapping("/{id}")
    public String findProjectById(@PathVariable String id, Model model) {
        try {
            int projectId = Integer.parseInt(id);
            ProjectDto projectDto = projectService.findProjectById(projectId);
            List<TagDto> tagList = tagService.findByProjectId(projectId);
            List<MilestoneResponseDto> milestoneList = milestoneService.findByProjectId(projectId);

            model.addAttribute("project", projectDto);
            model.addAttribute("tags", tagList);
            model.addAttribute("milestones", milestoneList);
            model.addAttribute("newTag", new TagDto());

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

    @PostMapping("/{id}")
    public String updateProject(@PathVariable String id, @ModelAttribute("project") ProjectUpdateRequest projectUpdateRequest) {
        try {
            int projectId = Integer.parseInt(id);
            projectService.updateProject(projectId, projectUpdateRequest);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @PostMapping("/{id}/delete")
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

    @GetMapping("/{projectId}/tag/{tagId}/edit")
    public String editTagForm(@PathVariable String projectId, @PathVariable String tagId, Model model) {
        try {
            int projectIdInt = Integer.parseInt(projectId);
            int tagIdInt = Integer.parseInt(tagId);
            TagDto tagDto = tagService.findTagById(tagIdInt);
            model.addAttribute("tag", tagDto);
            model.addAttribute("projectId", projectIdInt);
            return "tag_edit_form";
        } catch (NumberFormatException e) {
            return "error";
        }
    }
    @PostMapping("/{projectId}/tag/{tagId}")
    public String updateTag(@PathVariable String projectId, @PathVariable String tagId, @ModelAttribute("tag") TagUpdateRequest tagUpdateRequest) {
        try {
            int tagIdInt = Integer.parseInt(tagId);
            tagService.updateTag(tagIdInt, tagUpdateRequest);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @PostMapping("/{projectId}/tag/{tagId}/delete")
    public String deleteTag(@PathVariable String projectId, @PathVariable String tagId) {
        try {
            int tagIdInt = Integer.parseInt(tagId);
            tagService.deleteTag(tagIdInt);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @GetMapping("/{id}/milestone/new")
    public String newMilestoneForm(@PathVariable String id, Model model) {
        try {
            int projectId = Integer.parseInt(id);
            ProjectDto projectDto = projectService.findProjectById(projectId);
            model.addAttribute("milestone", new CreateMilestoneRequest());
            model.addAttribute("projectId", projectId);
            model.addAttribute("project", projectDto);
            return "milestone_form";
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @PostMapping("/{projectId}/milestone")
    public String createMilestone(@PathVariable("projectId") String projectId,
                                  @ModelAttribute("milestone") @Valid CreateMilestoneRequest createMilestoneRequest,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }
        try {
            int projectIdInt = Integer.parseInt(projectId);
            milestoneService.createMilestone(projectIdInt, createMilestoneRequest);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        } catch (Exception e) {
            return "error";
        }
    }

}
