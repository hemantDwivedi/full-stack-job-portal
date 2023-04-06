package com.inverse.project.Jobless.dto;

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
}
