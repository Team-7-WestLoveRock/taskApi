package com.nhnacademy.westloverock.taskapi.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "project_id", nullable = false)
    private Integer projectId;

    @Column(name = "name", nullable = false, unique = true, length = 45)
    private String name;

    @Column(name = "color", nullable = false, length = 45)
    private String color;

    // getters and setters
}