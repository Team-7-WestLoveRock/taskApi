package com.nhnacademy.westloverock.taskapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nhnacademy.westloverock.taskapi.dto.MilestoneResponseDto;
import com.nhnacademy.westloverock.taskapi.dto.TagDto;
import com.nhnacademy.westloverock.taskapi.dto.UpdateMilestoneRequest;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "end_date")
    private LocalDate endDate;


    public void modifyMilestone(UpdateMilestoneRequest updateMilestoneRequest) {
        this.name = updateMilestoneRequest.getName();
        this.startDate = updateMilestoneRequest.getStartDate();
        this.endDate = updateMilestoneRequest.getEndDate();
    }
    public MilestoneResponseDto toDto() {
        MilestoneResponseDto dto = new MilestoneResponseDto();
        dto.setId(this.id);
        dto.setName(this.name);
        dto.setStartDate(this.startDate);
        dto.setEndDate(this.endDate);
        dto.setProjectId(this.project.getId());
        return dto;
    }
}