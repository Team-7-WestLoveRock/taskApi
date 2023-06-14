package com.nhnacademy.westloverock.taskapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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
