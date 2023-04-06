package com.inverse.project.Jobless.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
}
