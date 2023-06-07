package com.nhnacademy.westloverock.taskapi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectResponseDto {
    private Integer id;
    private String name;
    private String description;
    private String state;
}