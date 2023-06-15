package com.nhnacademy.westloverock.taskapi.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class CommentResponseDto {
    private int id;
    private String userId;

    private String content;
    private LocalDateTime writtenDate;
}
