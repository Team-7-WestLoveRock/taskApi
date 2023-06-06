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
@Table(name = "Tasks_Tags")
public class TaskTag {

    @EmbeddedId
    private Pk id;

    @MapsId("taskId")
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @MapsId("tagId")
    @ManyToOne
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @Embeddable
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        private Integer taskId;
        private Integer tagId;
    }
}
