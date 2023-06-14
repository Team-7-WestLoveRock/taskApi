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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}