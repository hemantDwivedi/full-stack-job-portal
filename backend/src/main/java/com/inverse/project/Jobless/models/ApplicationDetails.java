package com.inverse.project.Jobless.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "application_details")
@NoArgsConstructor
@Getter
@Setter
public class ApplicationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String status;

//    @OneToOne
//    @JsonIgnore
//    private Applicant applicant;

    @ManyToOne
    @JsonIgnore
    private Job job;
}
