package com.inverse.project.Jobless.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDto {
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "about should not be blank")
    private String about;
    @NotBlank(message = "location should not be blank")
    private String location;
    @NotBlank(message = "start date should not be blank")
    private String startDate;
    @NotBlank(message = "apply by should not be blank")
    private String applyBy;
    @NotEmpty(message = "skills should not be blank")
    private List<String> skillList;
    @NotBlank(message = "opening should not be blank")
    @Size(min = 1)
    private String numberOfOpening;
    @NotBlank(message = "salary should not be blank")
    private String salary;
    @NotBlank(message = "company name should not be blank")
    private String companyName;
    @NotBlank(message = "company about should not be blank")
    private String companyAbout;
    private String companyWebsite;
    private List<ApplicationDetailsDto> applicationDetails;
}
