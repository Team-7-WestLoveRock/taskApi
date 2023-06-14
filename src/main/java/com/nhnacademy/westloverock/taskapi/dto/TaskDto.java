package com.nhnacademy.westloverock.taskapi.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskDto {
    private int id;
    private String title;
    private String registerUserId;
    private LocalDateTime expirationDate;
    private String content;
    private String priority;
    private int projectId;
    private int milestoneId;
    private LocalDateTime createdAt;
}