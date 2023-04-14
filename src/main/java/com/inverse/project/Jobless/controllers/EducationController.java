package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.EducationDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.EducationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
public class EducationController {

    private final EducationService educationService;

    public EducationController(EducationService educationService) {
        this.educationService = educationService;
    }

    // create education
    @PostMapping("/{resumeId}/educations")
    public ResponseEntity<EducationDto> create(@Valid @RequestBody EducationDto educationDto,
                                               @PathVariable Integer resumeId){
        return new ResponseEntity<>(this.educationService.create(educationDto, resumeId), HttpStatus.CREATED);
    }
    // update education
    @PutMapping("/{resumeId}/educations/{id}")
    public ResponseEntity<EducationDto> update(@Valid @RequestBody EducationDto educationDto,
                                               @PathVariable Integer resumeId,
                                               @PathVariable Integer id){
        return new ResponseEntity<>(this.educationService.update(educationDto,resumeId, id), HttpStatus.OK);
    }
    // delete education
    @DeleteMapping("/{resumeId}/educations/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer resumeId,
                                              @PathVariable Integer id){
        this.educationService.delete(resumeId,id);
        return new ResponseEntity<>(new APIResponse("Education deleted ID: " + id), HttpStatus.OK);
    }
}
