package com.nhnacademy.westloverock.taskapi.service;

import com.nhnacademy.westloverock.taskapi.dto.TaskDto;
import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.entity.Task;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import com.nhnacademy.westloverock.taskapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    @Transactional
    public void createTask(int projectId, TaskDto taskDto) {
        Project project = projectRepository
                .findProjectById(projectId)
                .orElseThrow(() ->
                        new NoSuchElementException("아이디에 해당하는 프로젝트 없음"));
        Task task = Task.builder()
                .project(project)
                .title(taskDto.getTitle())
                .registerUserId(taskDto.getRegisterUserId())
                .expirationDate(taskDto.getExpirationDate())
                .content(taskDto.getContent())
                .priority(taskDto.getPriority())
                .createdAt(LocalDateTime.now())
                .build();

        taskRepository.save(task);
    }


    @Transactional
    public void updateTask(Integer projectId, Integer taskId, TaskDto taskDto) {
        Task task = taskRepository
                .findTaskByProject_IdAndId(projectId, taskId)
                .orElseThrow(() ->
                        new NoSuchElementException("해당하는 프로젝트id와 taskId와 일치하는 task가 없습니다."));

        task.updateTask(taskDto);

    }

    @Transactional
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }

    public List<TaskDto> findByProjectId(int projectId) {
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        return tasks.stream()
                .map(Task::toDto)
                .collect(Collectors.toList());
    }
}
