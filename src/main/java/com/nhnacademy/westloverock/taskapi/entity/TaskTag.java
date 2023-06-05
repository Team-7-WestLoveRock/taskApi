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
@Table(name = "Tasks_Tags")
public class TaskTag {

    @Id
    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    @Id
    @Column(name = "tag_id", nullable = false)
    private Integer tagId;


}
