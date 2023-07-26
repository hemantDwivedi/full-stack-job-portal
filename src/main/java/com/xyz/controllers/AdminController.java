package com.xyz.controllers;

import com.xyz.dto.AdminDto;
import com.inverse.project.Jobless.exceptions.APIResponse;
import com.xyz.services.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // create admin
    @PostMapping
    public ResponseEntity<AdminDto> create(@RequestBody AdminDto adminDto){
        return new ResponseEntity<>(this.adminService.create(adminDto), HttpStatus.CREATED);
    }

    // update admin
    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> udpate(@RequestBody AdminDto adminDto,
                                           @PathVariable Integer id){
        return new ResponseEntity<>(this.adminService.update(adminDto, id), HttpStatus.OK);
    }

    // get by id
    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getByID(@PathVariable Integer id){
        return new ResponseEntity<>(this.adminService.getById(id), HttpStatus.OK);
    }

    // delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> delete(@PathVariable Integer id){
        this.adminService.delete(id);
        return new ResponseEntity<>(new APIResponse("admin deleted ID: " + id), HttpStatus.OK);
    }
}
