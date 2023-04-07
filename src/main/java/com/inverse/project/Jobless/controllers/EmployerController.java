package com.inverse.project.Jobless.controllers;

import com.inverse.project.Jobless.dto.EmployerDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.inverse.project.Jobless.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employers")
public class EmployerController {
    @Autowired
    private EmployerService employerService;

    // create employer
    @PostMapping
    public ResponseEntity<EmployerDto> create(@RequestBody EmployerDto employerDto){
        return new ResponseEntity<>(this.employerService.create(employerDto), HttpStatus.CREATED);
    }

    // update employer
    @PutMapping("/{id}")
    public ResponseEntity<EmployerDto> update(@RequestBody EmployerDto employerDto, @PathVariable Integer id){
        return new ResponseEntity<>(this.employerService.update(employerDto, id), HttpStatus.OK);
    }

    // fetch a specific employer
    @GetMapping("/{id}")
    public ResponseEntity<EmployerDto> getById(@PathVariable Integer id){
        return new ResponseEntity<>(this.employerService.getById(id), HttpStatus.FOUND);
    }

    // fetch all employer
    @GetMapping
    public ResponseEntity<List<EmployerDto>> getAll(){
        return new ResponseEntity<>(this.employerService.getAll(), HttpStatus.FOUND);
    }

    // delete employer
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.employerService.delete(id);
        return new ResponseEntity<>(new APIResponse("Employer deleted ID: " + id), HttpStatus.OK);
    }
}
