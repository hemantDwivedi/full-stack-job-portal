package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.ApplicationDetailsDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.AppDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application-details")
public class AppDetailsController {

    private final AppDetailService appDetailService;

    public AppDetailsController(AppDetailService appDetailService) {
        this.appDetailService = appDetailService;
    }

    // create application details
    @PostMapping("/jobs/{jodId}")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<ApplicationDetailsDto> create(@RequestBody ApplicationDetailsDto applicationDetailsDto,
                                                        @PathVariable Integer jodId){
        return new ResponseEntity<>(this.appDetailService.create(applicationDetailsDto, jodId), HttpStatus.CREATED);
    }

    // udpate application details
    @PutMapping("/jobs/{jodId}")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<ApplicationDetailsDto> update(@RequestBody ApplicationDetailsDto applicationDetailsDto,
                                                        @PathVariable Integer jodId,
                                                        @PathVariable Integer id){
        return ResponseEntity.ok(appDetailService.update(applicationDetailsDto, jodId, id));
    }

    // delete application details
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.appDetailService.delete(id);
        return ResponseEntity.ok(new APIResponse("Application Details deleted"));
    }
}
