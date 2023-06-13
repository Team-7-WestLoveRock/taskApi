package com.nhnacademy.westloverock.taskapi.dto;

import lombok.*;

import java.time.LocalDate;


@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Generated
public class MilestoneResponseDto {
    private int id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int projectId;


    public MilestoneResponseDto toDto() {
        MilestoneResponseDto milestone = new MilestoneResponseDto();
        milestone.setId(this.id);
        milestone.setName(this.name);
        milestone.setStartDate(this.startDate);
        milestone.setEndDate(this.endDate);
        milestone.setProjectId(this.projectId);
        return milestone;
    }
}
