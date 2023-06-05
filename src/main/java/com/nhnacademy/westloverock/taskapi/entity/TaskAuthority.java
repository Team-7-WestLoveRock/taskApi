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
@Table(name = "Task_Authorities")
@IdClass(TaskAuthority.TaskAuthorityId.class)
public class TaskAuthority {

    @Id
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Id
    @Column(name = "user_id", nullable = false, length = 45)
    private String userId;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class TaskAuthorityId implements Serializable {
        private Integer task;
        private String userId;
    }
}