package com.nhnacademy.westloverock.taskapi.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

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

    @Column(name = "milestone_id")
    private Integer milestoneId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}