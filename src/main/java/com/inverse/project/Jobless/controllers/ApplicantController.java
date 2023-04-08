package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.ApplicantDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.ApplicantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicants")
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    // create applicant
    @PostMapping
    public ResponseEntity<ApplicantDto> createApplicant(@Valid @RequestBody ApplicantDto applicantDto){
        return new ResponseEntity<>(this.applicantService.createApplicant(applicantDto), HttpStatus.CREATED);
    }
    // fetch all applicants
    @GetMapping
    public ResponseEntity<List<ApplicantDto>> getAllApplicant(){
        return new ResponseEntity<>(this.applicantService.getAllApplicants(), HttpStatus.FOUND);
    }
    // fetch a specific applicant
    @GetMapping("/{id}")
    public ResponseEntity<ApplicantDto> getById(@PathVariable Integer id){
        return new ResponseEntity<>(this.applicantService.getById(id), HttpStatus.FOUND);
    }
    // update applicant
    @PutMapping("/{id}")
    public ResponseEntity<ApplicantDto> updateApplicant(@Valid @RequestBody ApplicantDto applicantDto, @PathVariable Integer id){
        return new ResponseEntity<>(this.applicantService.updateApplicant(applicantDto, id), HttpStatus.OK);
    }
    // delete a specific applicant
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteApplicant(@PathVariable Integer id){
        this.applicantService.deleteApplicant(id);
        return new ResponseEntity<>(new APIResponse("Applicant deleted ID: " + id), HttpStatus.OK);
    }
}
