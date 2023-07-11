package com.inverse.project.Jobless.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class JobCategoryDto {
    // private Integer id;
    @NotBlank(message = "category name should not be blank")
    private String name;

    private Set<JobDto> jobs;
}
