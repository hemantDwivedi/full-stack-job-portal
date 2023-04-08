package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.ApplicationDetailsDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.AppDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/applicant")
public class AppDetailsController {
    @Autowired
    private AppDetailService appDetailService;

    // create application details
    @PostMapping("/{applicantId}/jobs/{jodId}/application-details")
    public ResponseEntity<ApplicationDetailsDto> create(@RequestBody ApplicationDetailsDto applicationDetailsDto,
                                                        @PathVariable Integer jodId,
                                                        @PathVariable Integer applicantId){
        return new ResponseEntity<>(this.appDetailService.create(applicationDetailsDto, applicantId, jodId), HttpStatus.CREATED);
    }

    // udpate application details
    @PutMapping("/{applicantId}/jobs/{jodId}/application-details/{id}")
    public ResponseEntity<ApplicationDetailsDto> create(@RequestBody ApplicationDetailsDto applicationDetailsDto,
                                                        @PathVariable Integer applicantId,
                                                        @PathVariable Integer jodId,
                                                        @PathVariable Integer id){
        return new ResponseEntity<>(this.appDetailService.update(applicationDetailsDto, applicantId,jodId, id), HttpStatus.OK);
    }

    // delete application details
    @DeleteMapping("/{applicantId}/application-details/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer applicantId, @PathVariable Integer id){
        this.appDetailService.delete(applicantId,id);
        return new ResponseEntity<>(new APIResponse("Application details deleted ID: " + id), HttpStatus.OK);
    }
}
