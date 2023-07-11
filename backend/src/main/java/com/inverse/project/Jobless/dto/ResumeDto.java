package com.inverse.project.Jobless.dto;

import com.inverse.project.Jobless.models.Education;
import com.inverse.project.Jobless.models.Project;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ResumeDto {
    // private int id;
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "email should not be blank")
    @Email
    private String email;
    @NotBlank(message = "phone should not be blank")
    @Size(min = 10, max = 10)
    private String phone;
    @NotBlank(message = "address should not be blank")
    private String address;
    @NotBlank(message = "gender should not be blank")
    private String gender;
    @NotEmpty(message = "language should not be blank")
    private List<String> language;
    @NotEmpty(message = "skills should not be blank")
    private List<String> skills;
    @NotEmpty(message = "links should not be blank")
    private List<String> links;
    private Set<EducationDto> educations;
    private Set<ProjectDto> projects;
}
