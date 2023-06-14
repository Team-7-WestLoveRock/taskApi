package com.nhnacademy.westloverock.taskapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Builder
public class CommentContentRequest {
    @NotBlank
    private String userId;
    @NotBlank
    private String content;
}
