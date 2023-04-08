package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.JobCategoryDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-categories")
public class JobCategoryController {

    @Autowired
    private JobCategoryService jobCategoryService;

    // create category
    @PostMapping
    public ResponseEntity<JobCategoryDto> create(@RequestBody JobCategoryDto jobCategoryDto){
        return new ResponseEntity<>(this.jobCategoryService.create(jobCategoryDto), HttpStatus.CREATED);
    }

    // update category
    @PutMapping("/{id}")
    public ResponseEntity<JobCategoryDto> update(@RequestBody JobCategoryDto jobCategoryDto, @PathVariable Integer id){
        return new ResponseEntity<>(this.jobCategoryService.update(jobCategoryDto, id), HttpStatus.OK);
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
