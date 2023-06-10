package com.nhnacademy.westloverock.taskapi.dto;

import com.nhnacademy.westloverock.taskapi.entity.Project;
import com.nhnacademy.westloverock.taskapi.entity.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    private int id;
    private String name;
    private String color;
    private Project project;

    public TagDto toDto() {
        TagDto tag = new TagDto();
        tag.setId(this.id);
        tag.setName(this.name);
        tag.setColor(this.color);
        tag.setProject(this.project);
        return tag;
    }
}
