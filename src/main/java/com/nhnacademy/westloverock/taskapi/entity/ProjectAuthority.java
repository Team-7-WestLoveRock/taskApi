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
public class ProjectAuthority {

    @Id
    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Id
    @Column(name = "user_id", nullable = false, length = 45)
    private String userId;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

}
