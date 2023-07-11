package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.JobDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.JobService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@AllArgsConstructor
public class JobController {
    private final JobService jobService;
    // create job
    @PostMapping("/{categoryId}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<JobDto> create(@Valid  @RequestBody JobDto jobDto,
                                         @PathVariable Integer categoryId){
        return new ResponseEntity<>(this.jobService.create(jobDto,categoryId), HttpStatus.CREATED);
    }

    // update job
    @PutMapping("/{id}/job-categories/{categoryId}")
    @PreAuthorize("hasRole('EMPLOYER')")
    public ResponseEntity<JobDto> update(@Valid @RequestBody JobDto jobDto,
                                         @PathVariable Integer categoryId,
                                         @PathVariable Integer id){
        return ResponseEntity.ok(jobService.update(jobDto, categoryId, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobDto> getById(@PathVariable Integer id){
        return ResponseEntity.ok(jobService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<JobDto>> getAll(){
        return ResponseEntity.ok(jobService.getAll());
    }

    @GetMapping("/search/job-name")
    public ResponseEntity<List<JobDto>> searchJobByName(@RequestParam("name") String name){
        return ResponseEntity.ok(jobService.searchByName(name));
    }

    @GetMapping("/search/job-location")
    public ResponseEntity<List<JobDto>> searchJobByLocation(@RequestParam("location") String location){
        return ResponseEntity.ok(jobService.searchByLocation(location));
    }

    @GetMapping("/search/company-name")
    public ResponseEntity<List<JobDto>> searchJobByCompanyName(@RequestParam("company") String companyName){
        return ResponseEntity.ok(jobService.searchByComanyName(companyName));
    }

    // delete job
    @DeleteMapping("/jobs/{id}")
    @PreAuthorize("hasAuthority('EMPOLYER')")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.jobService.delete(id);
        return ResponseEntity.ok(new APIResponse("Job deleted with id : " + id));
    }
}
