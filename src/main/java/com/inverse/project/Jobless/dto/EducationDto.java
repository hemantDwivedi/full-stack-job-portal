package com.inverse.project.Jobless.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EducationDto {
    private Integer id;
    private String name;
    private String degree;
    private String startDate;
    private String endDate;
    private String marks;
}
