package com.nhnacademy.westloverock.taskapi.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Generated
public class CreateMilestoneRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

}
