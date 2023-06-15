package com.nhnacademy.westloverock.taskapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class CommentUpdateDto {
    private Integer commentId;
    private String content;
}
