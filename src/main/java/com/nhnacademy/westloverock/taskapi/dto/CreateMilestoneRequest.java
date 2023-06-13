package com.nhnacademy.westloverock.taskapi.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
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
