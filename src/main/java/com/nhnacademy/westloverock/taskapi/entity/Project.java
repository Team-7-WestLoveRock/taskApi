package com.nhnacademy.westloverock.taskapi.entity;
import lombok.*;
import org.springframework.scheduling.config.Task;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "state", nullable = false, length = 2)
    private String state;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createAt;


}