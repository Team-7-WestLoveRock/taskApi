package com.nhnacademy.westloverock.taskapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CommentRegisterDto {
    private String userId;
    private Integer taskId;
    private String content;
    private LocalDateTime writtenDate;
}
