package com.nhnacademy.westloverock.taskapi.controller;

import com.nhnacademy.westloverock.taskapi.dto.CreateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.dto.TaskDto;
import com.nhnacademy.westloverock.taskapi.dto.UpdateMilestoneRequest;
import com.nhnacademy.westloverock.taskapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project/api/projects/{projectId}/task")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public HttpEntity<List<TaskDto>> findTasksByProjectId(@PathVariable int projectId) {
        return new ResponseEntity<> (taskService.findByProjectId(projectId), HttpStatus.OK);
    }

    @PostMapping
    public HttpEntity<Void> createTask(@PathVariable int projectId, @RequestBody @Valid TaskDto taskDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("요청 형식이 잘못됨");
        }

        taskService.createTask(projectId, taskDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{taskId}")
    public HttpEntity<Void> updateTask(@PathVariable(value = "projectId") Integer projectId,
                                            @PathVariable(value = "taskId") Integer taskId,
                                            @RequestBody @Valid TaskDto taskDto,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new RuntimeException("요청 형식이 잘못됨");
        }

        taskService.updateTask(projectId, taskId, taskDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public HttpEntity<Void> deleteTask(@PathVariable(value = "taskId") Integer taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
