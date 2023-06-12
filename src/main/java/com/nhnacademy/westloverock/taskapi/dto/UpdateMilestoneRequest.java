package com.nhnacademy.westloverock.taskapi.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class UpdateMilestoneRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
}
