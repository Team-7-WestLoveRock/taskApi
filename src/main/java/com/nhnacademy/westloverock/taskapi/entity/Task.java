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
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "register_user_id", nullable = false, unique = true, length = 45)
    private String registerUserId;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "content")
    private String content;

    @Column(name = "priority", length = 10)
    private String priority;

    @ManyToOne
    @JoinColumn(name = "milestone_id")
    private Milestone milestone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}