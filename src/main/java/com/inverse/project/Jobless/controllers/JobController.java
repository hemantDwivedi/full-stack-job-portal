package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.JobDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.JobService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class JobController {


    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    // create job
    @PostMapping("/{categoryId}/jobs")
    public ResponseEntity<JobDto> create(@Valid  @RequestBody JobDto jobDto,
                                         @PathVariable Integer categoryId){
        return new ResponseEntity<>(this.jobService.create(jobDto,categoryId), HttpStatus.CREATED);
    }

    // update job
    @PutMapping("/{categoryId}/jobs/{id}")
    public ResponseEntity<JobDto> update(@Valid @RequestBody JobDto jobDto,
                                         @PathVariable Integer categoryId,
                                         @PathVariable Integer id){
        return new ResponseEntity<>(this.jobService.update(jobDto, categoryId, id), HttpStatus.OK);
    }

    // delete job
    @DeleteMapping("/jobs/{id}")
    @PreAuthorize("hasAuthority('ROLE_EMPOLYER')")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.jobService.delete(id);
        return new ResponseEntity<>(new APIResponse("Job deleted ID: " + id), HttpStatus.OK);
    }
}
