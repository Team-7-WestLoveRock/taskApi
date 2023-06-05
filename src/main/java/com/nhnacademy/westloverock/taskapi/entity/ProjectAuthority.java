package com.nhnacademy.westloverock.taskapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Project_Authorities")
@IdClass(ProjectAuthority.ProjectAuthorityId.class)
public class ProjectAuthority {

    @Id
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Id
    @Column(name = "user_id", nullable = false, length = 45)
    private String userId;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class ProjectAuthorityId implements Serializable {
        private Integer project;
        private String userId;
    }

}

