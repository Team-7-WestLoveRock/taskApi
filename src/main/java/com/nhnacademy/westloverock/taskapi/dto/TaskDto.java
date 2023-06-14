package com.nhnacademy.westloverock.taskapi.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime expirationDate;
    private String content;
    private String priority;
    private int projectId;
    private int milestoneId;
    private LocalDateTime createdAt;
}