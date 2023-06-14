package com.nhnacademy.westloverock.taskapi.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

    @Column(name = "user_id", nullable = false, length = 45)
    private String userId;

    @Column(name = "content", nullable = false, length = 45)
    private String content;

    @Column(name = "written_date", nullable = false)
    private LocalDateTime writtenDate;

    public void modifyComment(String content) {
        this.content = content;
        this.writtenDate = LocalDateTime.now();
    }


}