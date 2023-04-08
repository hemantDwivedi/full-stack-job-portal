package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.JobCategoryDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.JobCategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employer")
public class JobCategoryController {

    @Autowired
    private JobCategoryService jobCategoryService;

    // create category
    @PostMapping("/{employerId}/job-categories")
    public ResponseEntity<JobCategoryDto> create(@Valid @RequestBody JobCategoryDto jobCategoryDto,
                                                 @PathVariable Integer employerId){
        return new ResponseEntity<>(this.jobCategoryService.create(jobCategoryDto, employerId), HttpStatus.CREATED);
    }

    // update category
    @PutMapping("/{employerId}/job-categories/{id}")
    public ResponseEntity<JobCategoryDto> update(@Valid @RequestBody JobCategoryDto jobCategoryDto,
                                                 @PathVariable Integer employerId,
                                                 @PathVariable Integer id){
        return new ResponseEntity<>(this.jobCategoryService.update(jobCategoryDto,employerId, id), HttpStatus.OK);
    }

    // get all category
    @GetMapping
    public ResponseEntity<List<JobCategoryDto>> getAll(){
        return new ResponseEntity<>(this.jobCategoryService.getAll(), HttpStatus.FOUND);
    }
    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryDto> getById(@PathVariable Integer id){
        return new ResponseEntity<>(this.jobCategoryService.getById(id), HttpStatus.FOUND);
    }

    // delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.jobCategoryService.delete(id);
        return new ResponseEntity<>(new APIResponse("Job category deteled ID: " + id), HttpStatus.OK);
    }
}
