package com.nhnacademy.westloverock.taskapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProjectDto{
    private Integer id;
    private String name;
    private String description;
    private String state;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate createAt;

    public ProjectDto toDto() {
        ProjectDto dto = new ProjectDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setDescription(this.description);
        dto.setState(this.state);
        dto.setCreateAt(this.createAt);
        return dto;
    }
}