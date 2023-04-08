package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.config.ModelMap;
import com.inverse.project.Jobless.dto.JobCategoryDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Employer;
import com.inverse.project.Jobless.models.JobCategory;
import com.inverse.project.Jobless.repositories.EmployerRepository;
import com.inverse.project.Jobless.repositories.JobCategoryRepository;
import com.inverse.project.Jobless.services.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobCategoryServiceImpl implements JobCategoryService {

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private ModelMap modelMap;

    @Override
    public JobCategoryDto create(JobCategoryDto jobCategoryDto, Integer employerId) {
        JobCategory jobCategory = this.modelMap.modelMapper().map(jobCategoryDto, JobCategory.class);
        Employer employer = this.employerRepository.findById(employerId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer not found ID: " + employerId)
                );
        jobCategory.setEmployer(employer);
        this.jobCategoryRepository.save(jobCategory);
        return this.modelMap.modelMapper().map(jobCategory, JobCategoryDto.class);
    }

    @Override
    public JobCategoryDto update(JobCategoryDto jobCategoryDto,
                                 Integer employerId,
                                 Integer id) {

        JobCategory jobCategory = this.jobCategoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job Category not found ID: " + id)
                );
        Employer employer = this.employerRepository.findById(employerId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employer not found ID: " + employerId)
                );
        jobCategory.setName(jobCategoryDto.getName());
        jobCategory.setEmployer(employer);
        this.jobCategoryRepository.save(jobCategory);
        return this.modelMap.modelMapper().map(jobCategory, JobCategoryDto.class);
    }

    @Override
    public JobCategoryDto getById(Integer id) {
        JobCategory jobCategory = this.jobCategoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job Category not found ID: " + id)
                );
        return this.modelMap.modelMapper().map(jobCategory, JobCategoryDto.class);
    }

    @Override
    public List<JobCategoryDto> getAll() {
        List<JobCategory> jobCategories = this.jobCategoryRepository.findAll();
        return jobCategories
                .stream()
                .map(
                        jobCategory -> this.modelMap.modelMapper().map(jobCategory, JobCategoryDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        JobCategory jobCategory = this.jobCategoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job Category not found ID: " + id)
                );
        this.jobCategoryRepository.delete(jobCategory);
    }
}