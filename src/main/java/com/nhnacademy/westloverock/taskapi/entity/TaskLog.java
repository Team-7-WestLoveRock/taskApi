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
public class TaskLog {

    @Id
    @Column(name = "update_date", nullable = false)
    private LocalDate updateDate;

    @Id
    @Column(name = "Tasks_id", nullable = false)
    private Integer taskId;

}


