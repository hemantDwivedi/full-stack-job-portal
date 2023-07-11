package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.EducationDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.EducationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/educations")
@AllArgsConstructor
@PreAuthorize("hasRole('APPLICANT')")
public class EducationController {
    private final EducationService educationService;
    // create education
    @PostMapping
    public ResponseEntity<EducationDto> create(@Valid @RequestBody EducationDto educationDto,
                                               @RequestParam Integer resumeId){
        return new ResponseEntity<>(this.educationService.create(educationDto, resumeId), HttpStatus.CREATED);
    }
    // update education
    @PutMapping("/educations/{id}")
    public ResponseEntity<EducationDto> update(@Valid @RequestBody EducationDto educationDto,
                                               @PathVariable Integer id){
        return ResponseEntity.ok(educationService.update(educationDto, id));
    }
    // delete education
    @DeleteMapping("/{resumeId}/educations/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer resumeId,
                                              @PathVariable Integer id){
        this.educationService.delete(id);
        return ResponseEntity.ok(new APIResponse("Education detail deleted id : " + id));
    }
}
