package com.nhnacademy.westloverock.taskapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Task_Logs")
public class TaskLog {

    @EmbeddedId
    private Pk id;

    @MapsId("taskId")
    @ManyToOne
    @JoinColumn(name = "Tasks_id", nullable = false)
    private Task task;

    @Embeddable
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class Pk implements Serializable {
        @Column(name = "update_date", nullable = false)
        private LocalDate updateDate;
        private int taskId;
    }
}
