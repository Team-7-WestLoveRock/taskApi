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
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NoSuchElementException("아이디에 해당하는 프로젝트가 없습니다."));
        Task task = Task.builder()
                .title(taskDto.getTitle())
                .registerUserId(taskDto.getRegisterUserId())
                .expirationDate(taskDto.getExpirationDate())
                .content(taskDto.getContent())
                .priority(taskDto.getPriority())
                .createdAt(LocalDateTime.now())
                .project(project)
                .build();

        taskRepository.save(task);
    }

    @Transactional
    public void updateTask(Integer projectId, Integer taskId, TaskDto taskDto) {
        Task task = taskRepository
                .findByProjectIdAndId(projectId, taskId)
                .orElseThrow(() ->
                        new NoSuchElementException("해당하는 프로젝트 ID와 Task ID로 일치하는 Task가 없습니다."));

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

    public TaskDto findTaskByProjectIdAndTaskId(int projectId, int taskId) {
        Task task = taskRepository.findByProjectIdAndId(projectId, taskId)
                .orElseThrow(() -> new NoSuchElementException("이 프로젝트에 대한 작업을 찾을 수 없습니다."));
        return task.toDto();
    }

}
