package com.inverse.project.Jobless.dto;

import com.inverse.project.Jobless.models.Project;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class ResumeDto {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String gender;
    private List<String> language;
    private List<String> skills;
    private List<String> links;

    private Set<Project> projects;
}
