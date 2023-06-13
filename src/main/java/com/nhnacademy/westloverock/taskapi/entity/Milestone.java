package com.nhnacademy.westloverock.taskapi.entity;

import com.nhnacademy.westloverock.taskapi.dto.UpdateMilestoneRequest;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "Milestones")
@Generated
@EqualsAndHashCode
public class Milestone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;


    public void modifyMilestone(UpdateMilestoneRequest updateMilestoneRequest) {
        this.name = updateMilestoneRequest.getName();
        this.startDate = updateMilestoneRequest.getStartDate();
        this.endDate = updateMilestoneRequest.getEndDate();
    }
}