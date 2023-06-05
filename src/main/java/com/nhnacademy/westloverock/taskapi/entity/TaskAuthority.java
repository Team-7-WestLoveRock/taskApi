package com.nhnacademy.westloverock.taskapi.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Task_Authorities")
public class TaskAuthority {

    @Id
    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @Column(name = "user_id", nullable = false, length = 45)
    private String userId;

    @Column(name = "authority", nullable = false, length = 45)
    private String authority;

}