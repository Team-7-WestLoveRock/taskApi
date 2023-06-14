package com.nhnacademy.westloverock.taskapi.entity;

import com.nhnacademy.westloverock.taskapi.dto.TaskDto;
import com.nhnacademy.westloverock.taskapi.repository.ProjectRepository;
import com.nhnacademy.westloverock.taskapi.service.TaskService;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "register_user_id", nullable = false, unique = true, length = 45)
    private String registerUserId;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "content")
    private String content;

    @Column(name = "priority", length = 10)
    private String priority;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public void updateTask(TaskDto taskDto) {
        this.title = taskDto.getTitle();
        this.registerUserId = taskDto.getRegisterUserId();
        this.expirationDate = LocalDateTime.now();
        this.content = taskDto.getContent();
        this.priority = taskDto.getPriority();
    }
    public Task(TaskDto taskDto) {
        this.title = taskDto.getTitle();
        this.registerUserId = taskDto.getRegisterUserId();
        this.expirationDate = taskDto.getExpirationDate();
        this.content = taskDto.getContent();
        this.priority = taskDto.getPriority();
    }
    public TaskDto toDto() {
        TaskDto dto = new TaskDto();
        dto.setId(this.id);
        dto.setTitle(this.title);
        dto.setRegisterUserId(this.registerUserId);
        dto.setExpirationDate(this.expirationDate);
        dto.setContent(this.content);
        dto.setPriority(this.priority);
        dto.setProjectId(this.project.getId());
        dto.setCreatedAt(this.createdAt);
        return dto;
    }
}