package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.ResumeDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applicant")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    // create resume
    @PostMapping("/{applicantId}/resume")
    public ResponseEntity<ResumeDto> create(@RequestBody ResumeDto resumeDto, @PathVariable Integer applicantId){
        return new ResponseEntity<>(this.resumeService.create(resumeDto, applicantId), HttpStatus.CREATED);
    }
    // fetch a specific resume
    @GetMapping("/{applicantId}/resume/{id}")
    public ResponseEntity<ResumeDto> getById(@PathVariable Integer id){
        return new ResponseEntity<>(this.resumeService.getById(id), HttpStatus.FOUND);
    }
    // update resume
    @PutMapping("/{applicantId}/resume/{id}")
    public ResponseEntity<ResumeDto> update(@PathVariable Integer id,
                                            @PathVariable Integer applicantId,
                                            @RequestBody ResumeDto resumeDto){
        return new ResponseEntity<>(this.resumeService.update(resumeDto, applicantId, id), HttpStatus.OK);
    }
    // delete a resume
    @DeleteMapping("/{applicantId}/resume/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer applicantId,
                                              @PathVariable Integer id){
        this.resumeService.delete(applicantId,id);
        return new ResponseEntity<>(new APIResponse("Resume deleted ID: " + id), HttpStatus.OK);
    }
}
