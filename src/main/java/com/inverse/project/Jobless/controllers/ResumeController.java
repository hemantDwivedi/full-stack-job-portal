package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.ResumeDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;
    // create resume
    @PostMapping
    public ResponseEntity<ResumeDto> create(@RequestBody ResumeDto resumeDto){
        return new ResponseEntity<>(this.resumeService.create(resumeDto), HttpStatus.CREATED);
    }
    // fetch a specific resume
    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getById(@PathVariable int id){
        return new ResponseEntity<>(this.resumeService.getById(id), HttpStatus.FOUND);
    }
    // update resume
    @PutMapping("/{id}")
    public ResponseEntity<ResumeDto> update(@PathVariable int id, @RequestBody ResumeDto resumeDto){
        return new ResponseEntity<>(this.resumeService.update(resumeDto, id), HttpStatus.OK);
    }
    // delete a resume
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable int id){
        this.resumeService.delete(id);
        return new ResponseEntity<>(new APIResponse("Resume deleted ID: " + id), HttpStatus.OK);
    }
}
