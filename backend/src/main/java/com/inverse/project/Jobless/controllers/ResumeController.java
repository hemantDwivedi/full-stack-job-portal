package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.ResumeDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.ResumeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
@AllArgsConstructor
public class ResumeController {
    private final ResumeService resumeService;

    // create resume
    @PostMapping
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<ResumeDto> create(@Valid @RequestBody ResumeDto resumeDto){
        return new ResponseEntity<>(this.resumeService.create(resumeDto), HttpStatus.CREATED);
    }
    // fetch a specific resume
    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getById(@PathVariable Integer id){
        return new ResponseEntity<>(this.resumeService.getById(id), HttpStatus.FOUND);
    }
    // update resume
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<ResumeDto> update(@Valid @PathVariable Integer id,
                                            @RequestBody ResumeDto resumeDto){
        return new ResponseEntity<>(this.resumeService.update(resumeDto, id), HttpStatus.OK);
    }
    // delete a resume
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.resumeService.delete(id);
        return new ResponseEntity<>(new APIResponse("Resume deleted ID: " + id), HttpStatus.OK);
    }
}
