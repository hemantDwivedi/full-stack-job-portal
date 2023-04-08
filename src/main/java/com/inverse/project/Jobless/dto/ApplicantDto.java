package com.inverse.project.Jobless.dto;

import com.inverse.project.Jobless.models.ApplicationDetails;
import com.inverse.project.Jobless.models.Resume;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicantDto {
    private int id;
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "Email should not be blank")
    @Email
    private String email;
    @NotBlank(message = "Password should not be blank")
    @Size(min = 8, max = 16)
    private String password;
    // private String role;

    private Resume resume;

    private ApplicationDetails applicationDetails;
}
