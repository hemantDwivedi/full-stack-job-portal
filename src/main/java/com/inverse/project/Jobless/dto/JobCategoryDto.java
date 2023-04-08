package com.inverse.project.Jobless.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class JobCategoryDto {
    // private Integer id;
    private String name;

    private Set<JobDto> jobs;
}
