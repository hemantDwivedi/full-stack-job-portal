package com.inverse.project.Jobless.dto;

import com.inverse.project.Jobless.models.ApplicationDetails;
import com.inverse.project.Jobless.models.Resume;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    private Resume resume;

    private ApplicationDetails applicationDetails;
}
