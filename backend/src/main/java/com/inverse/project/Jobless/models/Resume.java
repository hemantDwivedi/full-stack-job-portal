package com.inverse.project.Jobless.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "resume")
@Getter
@Setter
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String gender;
    @ElementCollection
    private List<String> language;
    @ElementCollection
    private List<String> skills;
    @ElementCollection
    private List<String> links;

    @OneToMany(mappedBy = "resume")
    @JsonIgnore
    private Set<Education> educations;

    @OneToMany(mappedBy = "resume")
    @JsonIgnore
    private Set<Project> projects;
}
