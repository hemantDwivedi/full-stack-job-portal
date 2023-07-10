package com.inverse.project.Jobless.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String about;
    private String location;
    private String startDate;
    private String applyBy;
    @ElementCollection
    private List<String> skillList;
    private String numberOfOpening;
    private String salary;
    private String companyName;
    private String companyAbout;
    private String companyWebsite;

    @ManyToOne
    @JoinColumn(name = "job_category_id")
    private JobCategory jobCategory;

    @OneToMany(mappedBy = "job")
    @JsonIgnore
    private List<ApplicationDetails> applicationDetails;
}
