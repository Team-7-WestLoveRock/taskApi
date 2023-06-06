package com.nhnacademy.westloverock.taskapi.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectDto implements Serializable{

    private String name;
    private String description;

}