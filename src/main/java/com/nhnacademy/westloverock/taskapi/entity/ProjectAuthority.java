package com.nhnacademy.westloverock.taskapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Project_Authorities")
public class ProjectAuthority {

    @EmbeddedId
    private Pk id;

    @MapsId("projectId")
    @JoinColumn(name = "project_id", nullable = false)
    @ManyToOne
    private Project project;

    @Column(nullable = false, length = 45)
    private String authority;

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        private Integer projectId;
        @Column(name = "user_id")
        private String userId;
    }
}

