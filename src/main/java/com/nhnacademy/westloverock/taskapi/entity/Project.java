package com.nhnacademy.westloverock.taskapi.entity;
import com.nhnacademy.westloverock.taskapi.dto.ProjectDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @OneToMany(mappedBy = "project")
    private List<ProjectAuthority> projectAuthorities;

    @OneToMany(mappedBy = "project")
    private List<Tag> tags;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
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