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
@ToString
@Table(name = "Task_Authorities")
public class TaskAuthority {

    @EmbeddedId
    private Pk id;

    @MapsId("taskId")
    @JoinColumn(name = "task_id", nullable = false)
    @ManyToOne
    private Task task;

    @Column(nullable = false, length = 45)
    private String authority;

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        private Integer taskId;
        @Column(name = "user_id")
        private String userId;
    }
}
