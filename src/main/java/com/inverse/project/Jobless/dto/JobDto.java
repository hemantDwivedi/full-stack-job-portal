package com.inverse.project.Jobless.dto;

import jakarta.persistence.ElementCollection;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JobDto {

    private String name;
    private String about;
    private String location;
    private String startDate;
    private String applyBy;
    private List<String> skillList;
    private String numberOfOpening;
    private String salary;
    private String companyName;
    private String companyAbout;
    private String companyWebsite;

    private List<ApplicationDetailsDto> applicationDetails;
}
