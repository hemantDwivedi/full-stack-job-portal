package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.JobDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class JobController {

    @Autowired
    private JobService jobService;

    // create job
    @PostMapping("/{categoryId}/jobs")
    public ResponseEntity<JobDto> create(@RequestBody JobDto jobDto,
                                         @PathVariable Integer categoryId){
        return new ResponseEntity<>(this.jobService.create(jobDto,categoryId), HttpStatus.CREATED);
    }

    // update job
    @PutMapping("/{categoryId}/jobs/{id}")
    public ResponseEntity<JobDto> update(@RequestBody JobDto jobDto,
                                         @PathVariable Integer categoryId,
                                         @PathVariable Integer id){
        return new ResponseEntity<>(this.jobService.update(jobDto, categoryId, id), HttpStatus.OK);
    }

    // delete job
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.jobService.delete(id);
        return new ResponseEntity<>(new APIResponse("Job deleted ID: " + id), HttpStatus.OK);
    }
}
