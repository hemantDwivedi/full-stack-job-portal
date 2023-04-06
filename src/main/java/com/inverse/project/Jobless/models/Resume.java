package com.inverse.project.Jobless.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "resume")
@Getter
@Setter
public class Resume {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
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
}
