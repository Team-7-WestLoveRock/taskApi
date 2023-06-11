package com.nhnacademy.westloverock.taskapi.dto;

import com.nhnacademy.westloverock.taskapi.entity.Project;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TagUpdateRequest {
    private String name;
    private String color;
    private Project project;

    public TagUpdateRequest toDto() {
        TagUpdateRequest dto = new TagUpdateRequest();
        dto.setName(this.name);
        dto.setColor(this.color);
        dto.setProject(this.project);
        return dto;
    }
}
