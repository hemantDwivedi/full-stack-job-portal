package com.inverse.project.Jobless.controllers;


import com.inverse.project.Jobless.dto.ProjectDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resume")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    // create project
    @PostMapping("/{resume_id}/projects")
    public ResponseEntity<ProjectDto> create(@Valid @RequestBody ProjectDto projectDto, @PathVariable int resume_id){
        return new ResponseEntity<>(this.projectService.create(projectDto, resume_id), HttpStatus.CREATED);
    }

    // update project information
    @PutMapping("/{resumeId}/projects/{id}")
    public ResponseEntity<ProjectDto> update(@Valid @RequestBody ProjectDto projectDto,
                                             @PathVariable Integer resumeId,
                                             @PathVariable Integer id){
        return new ResponseEntity<>(this.projectService.update(projectDto,resumeId, id), HttpStatus.OK);
    }

    // delete a specific project
    @DeleteMapping("/{resumeId}/projects/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer resumeId,@PathVariable Integer id){
        this.projectService.delete(resumeId,id);
        return new ResponseEntity<>(new APIResponse("Project deleted ID: " + id), HttpStatus.OK);
    }
}
