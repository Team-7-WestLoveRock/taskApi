package com.nhnacademy.westloverock.taskapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@AllArgsConstructor
@Getter
public class CommentUpdateRequest {
    @NotBlank
    private String content;
}
