package com.nhnacademy.westloverock.taskapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Task_Logs")
@IdClass(TaskLog.TaskLogId.class)
public class TaskLog {

    @Id
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Id
    @ManyToOne
    @JoinColumn(name = "Tasks_id", nullable = false)
    private Task task;

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class TaskLogId implements Serializable {

        private LocalDate updateDate;
        private Integer task;
    }
}



