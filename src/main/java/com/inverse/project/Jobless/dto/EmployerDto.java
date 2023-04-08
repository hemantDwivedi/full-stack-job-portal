package com.inverse.project.Jobless.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EmployerDto {
    private Integer id;
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "contact should not be blank")
    @Size(min = 10, max = 10)
    private String contact;
    @NotBlank(message = "email should not be blank")
    @Email
    private String email;
    @NotBlank(message = "password should not be blank")
    @Size(min = 8, max = 16)
    private String password;
    private String role;

    private List<JobCategoryDto> categories;
}
