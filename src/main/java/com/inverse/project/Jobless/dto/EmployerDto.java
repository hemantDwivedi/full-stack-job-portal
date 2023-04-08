package com.inverse.project.Jobless.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployerDto {
    private Integer id;
    private String name;
    private String contact;
    private String email;
    private String password;
    private String role;

    private List<JobCategoryDto> categories;
}
