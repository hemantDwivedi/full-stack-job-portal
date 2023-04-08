package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.config.ModelMap;
import com.inverse.project.Jobless.dto.EmployerDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Employer;
import com.inverse.project.Jobless.repositories.EmployerRepository;
import com.inverse.project.Jobless.services.EmployerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployerServiceImpl implements EmployerService {
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private ModelMap modelMap;

    // create employer
    @Override
    public EmployerDto create(EmployerDto employerDto) {
        Employer employer = this.modelMap.modelMapper().map(employerDto, Employer.class);
        employer.setRole("EMPLOYER");
        this.employerRepository.save(employer);
        return this.modelMap.modelMapper().map(employer, EmployerDto.class);
    }

    @Override
    public EmployerDto update(EmployerDto employerDto, Integer id) {
        Employer employer = this.employerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer not found ID: " + id)
                );
        employer.setName(employerDto.getName());
        employer.setEmail(employerDto.getEmail());
        employer.setPassword(employerDto.getPassword());
        employer.setContact(employerDto.getContact());
        this.employerRepository.save(employer);
        return this.modelMap.modelMapper().map(employer, EmployerDto.class);
    }

    @Override
    public EmployerDto getById(Integer id) {
        Employer employer = this.employerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer not found ID: " + id)
                );
        return this.modelMap.modelMapper().map(employer, EmployerDto.class);
    }

    // fetch all employer
    @Override
    public List<EmployerDto> getAll() {
        List<Employer> employerList = this.employerRepository.findAll();
        return employerList
                .stream()
                .map(
                        employer -> this.modelMap.modelMapper().map(employer, EmployerDto.class)
                ).collect(Collectors.toList());
    }

    // delete a employer
    @Override
    public void delete(Integer id) {
        Employer employer = this.employerRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer not found ID: " + id)
                );
        this.employerRepository.delete(employer);
    }
}
