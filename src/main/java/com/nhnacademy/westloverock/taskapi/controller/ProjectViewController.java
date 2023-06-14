package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.*;
import com.nhnacademy.westloverock.taskapi.service.MilestoneService;
import com.nhnacademy.westloverock.taskapi.service.ProjectService;
import com.nhnacademy.westloverock.taskapi.service.TagService;
import com.nhnacademy.westloverock.taskapi.service.TaskService;
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
    private final TaskService taskService;
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
            List<TaskDto> taskList = taskService.findByProjectId(projectId);

            model.addAttribute("project", projectDto);
            model.addAttribute("tags", tagList);
            model.addAttribute("milestones", milestoneList);
            model.addAttribute("tasks", taskList);
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
    public String createMileStone(@PathVariable String projectId, @ModelAttribute("milestone") CreateMilestoneRequest createMilestoneRequest) {
        try {
            int projectIdInt = Integer.parseInt(projectId);
            createMilestoneRequest.setProjectId(projectIdInt);
            milestoneService.createMilestone(projectIdInt, createMilestoneRequest);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        }
    }
    @GetMapping("/{projectId}/milestone/{milestoneId}/edit")
    public String editMilestoneForm(@PathVariable Integer projectId, @PathVariable Integer milestoneId, Model model) {
        MilestoneResponseDto milestone = milestoneService.findById(milestoneId);
        model.addAttribute("milestone", milestone);
        model.addAttribute("projectId", projectId);
        return "milestone_edit_form";
    }

    @PostMapping("/{projectId}/milestone/{milestoneId}")
    public String updateMilestone(@PathVariable String projectId, @PathVariable String milestoneId, @ModelAttribute("milestone") UpdateMilestoneRequest updateMilestoneRequest) {

        try {
            int projectIdInt = Integer.parseInt(projectId);
            int milestoneIdInt = Integer.parseInt(milestoneId);
            try {
                milestoneService.updateMilestone(projectIdInt, milestoneIdInt, updateMilestoneRequest);
                return "redirect:/project/" + projectId;
            } catch (Exception e) {
                return "error";
            }
        } catch (NumberFormatException e) {
            return "error";
        }
    }


    @PostMapping("/{projectId}/milestone/{milestoneId}/delete")
    public String deleteMilestone(@PathVariable String projectId, @PathVariable String milestoneId) {
        try {
            int milestoneIdInt = Integer.parseInt(milestoneId);
            milestoneService.deleteMilestone(milestoneIdInt);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @GetMapping("/{projectId}/task/{taskId}")
    public String findTaskById(@PathVariable String projectId, @PathVariable String taskId, Model model) {
        try {
            int projectIdInt = Integer.parseInt(projectId);
            int taskIdInt = Integer.parseInt(taskId);

            TaskDto taskDto = taskService.findTaskByProjectIdAndTaskId(projectIdInt, taskIdInt);

            model.addAttribute("task", taskDto);

            return "task_detail";
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @GetMapping("/{projectId}/task/new")
    public String newTaskForm(@PathVariable String projectId, Model model) {
        try {
            int projectIdInt = Integer.parseInt(projectId);
            model.addAttribute("task", new TaskDto());
            model.addAttribute("projectId", projectIdInt);
            return "task_form";
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @PostMapping("/{projectId}/task")
    public String createTask(@PathVariable String projectId, @ModelAttribute("task") TaskDto taskDto) {
        try {
            int projectIdInt = Integer.parseInt(projectId);
            taskDto.setProjectId(projectIdInt);
            taskService.createTask(projectIdInt, taskDto);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @GetMapping("/{projectId}/task/{taskId}/edit")
    public String editTaskForm(@PathVariable String projectId, @PathVariable String taskId, Model model) {
        try {
            int projectIdInt = Integer.parseInt(projectId);
            int taskIdInt = Integer.parseInt(taskId);
            TaskDto taskDto = taskService.findTaskByProjectIdAndTaskId(projectIdInt, taskIdInt);
            model.addAttribute("task", taskDto);
            model.addAttribute("projectId", projectIdInt);
            return "task_edit_form";
        } catch (NumberFormatException e) {
            return "error";
        }
    }

    @PostMapping("/{projectId}/task/{taskId}")
    public String updateTask(@PathVariable int projectId, @PathVariable int taskId, @ModelAttribute("task") TaskDto taskDto) {
        taskService.updateTask(projectId, taskId, taskDto);
        return "redirect:/project/" + projectId;
    }

    @PostMapping("/{projectId}/task/{taskId}/delete")
    public String deleteTask(@PathVariable String projectId, @PathVariable String taskId) {
        try {
            int taskIdInt = Integer.parseInt(taskId);
            taskService.deleteTask(taskIdInt);
            return "redirect:/project/" + projectId;
        } catch (NumberFormatException e) {
            return "error";
        }
    }

}
