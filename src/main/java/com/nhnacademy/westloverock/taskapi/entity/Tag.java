package com.nhnacademy.westloverock.taskapi.entity;

import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "name", nullable = false, unique = true, length = 45)
    private String name;

    @Column(name = "color", nullable = false, length = 45)
    private String color;

    public Tag(String name, String color, Project project) {
        this.name = name;
        this.color = color;
        this.project = project;
    }
    public TagDto toDto() {
        TagDto dto = new TagDto();
        dto.setId(this.id);
        dto.setProject(this.project);
        dto.setName(this.name);
        dto.setColor(this.color);
        return dto;
    }

    public void update(String name, String color, Project project) {
        this.name = name;
        this.color = color;
        this.project = project;
    }
}