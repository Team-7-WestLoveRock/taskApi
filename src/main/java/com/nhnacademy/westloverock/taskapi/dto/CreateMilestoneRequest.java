package com.nhnacademy.westloverock.taskapi.dto;

import com.sun.istack.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CreateMilestoneRequest {
    @NotNull
    private String name;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
}
