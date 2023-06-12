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

    public TagUpdateRequest toDto() {
        TagUpdateRequest dto = new TagUpdateRequest();
        dto.setName(this.name);
        dto.setColor(this.color);
        return dto;
    }
}
