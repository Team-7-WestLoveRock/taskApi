package com.nhnacademy.westloverock.taskapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CommentUpdateDto {
    private Integer commentId;
    private String content;
}
