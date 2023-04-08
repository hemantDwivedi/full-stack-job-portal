package com.inverse.project.Jobless.dto;

import com.inverse.project.Jobless.models.Job;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EmployerDto {
    private Integer id;
    private String name;
    private String contact;
    private String email;
    private String password;
    private String role;
}
