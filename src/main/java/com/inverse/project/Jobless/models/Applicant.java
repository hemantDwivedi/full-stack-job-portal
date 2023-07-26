package com.inverse.project.Jobless.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "applicant")
@Getter
@Setter
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String role;

    @OneToOne(mappedBy = "applicant")
    @JsonIgnore
    private Resume resume;

    @OneToOne(mappedBy = "applicant")
    @JsonIgnore
    private ApplicationDetails applicationDetails;
}