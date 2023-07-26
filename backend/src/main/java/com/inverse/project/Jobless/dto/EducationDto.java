package com.inverse.project.Jobless.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EducationDto {
    // private Integer id;
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "degree should not be blank")
    private String degree;
    @NotBlank(message = "start date should not be blank")
    private String startDate;
    @NotBlank(message = "end date should not be blank")
    private String endDate;
    @NotBlank(message = "marks should not be blank")
    private String marks;
}
