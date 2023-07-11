package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.JobCategoryDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.JobCategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-categories")
@AllArgsConstructor
public class JobCategoryController {
    private final JobCategoryService jobCategoryService;
    // create category
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JobCategoryDto> create(@Valid @RequestBody JobCategoryDto jobCategoryDto){
        return new ResponseEntity<>(this.jobCategoryService.create(jobCategoryDto), HttpStatus.CREATED);
    }

    // update category
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<JobCategoryDto> update(@Valid @RequestBody JobCategoryDto jobCategoryDto,
                                                 @PathVariable Integer id){
        return ResponseEntity.ok(jobCategoryService.update(jobCategoryDto, id));
    }

    // get all category
    @GetMapping
    public ResponseEntity<List<JobCategoryDto>> getAll(){
        return ResponseEntity.ok(jobCategoryService.getAll());
    }
    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<JobCategoryDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(jobCategoryService.getById(id));
    }

    // delete category
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.jobCategoryService.delete(id);
        return ResponseEntity.ok(new APIResponse("Job category deteled ID: " + id));
    }
}
