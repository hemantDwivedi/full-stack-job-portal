package com.inverse.project.Jobless.controllers;


import com.inverse.project.Jobless.dto.ProjectDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.ProjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects")
@AllArgsConstructor
@PreAuthorize("hasRole('APPLICANT')")
public class ProjectController {
    private final ProjectService projectService;
    // create project
    @PostMapping
    public ResponseEntity<ProjectDto> create(@Valid @RequestBody ProjectDto projectDto, @RequestParam int resume_id){
        return new ResponseEntity<>(this.projectService.create(projectDto, resume_id), HttpStatus.CREATED);
    }

    // update project information
    @PutMapping("/projects/{id}")
    public ResponseEntity<ProjectDto> update(@Valid @RequestBody ProjectDto projectDto,
                                             @PathVariable Integer id){
        return ResponseEntity.ok(projectService.update(projectDto, id));
    }

    // delete a specific project
    @DeleteMapping("/projects/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.projectService.delete(id);
        return new ResponseEntity<>(new APIResponse("Project deleted ID: " + id), HttpStatus.OK);
    }
}
