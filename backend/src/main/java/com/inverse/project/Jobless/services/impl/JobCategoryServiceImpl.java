package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.dto.JobCategoryDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.JobCategory;
import com.inverse.project.Jobless.repositories.JobCategoryRepository;
import com.inverse.project.Jobless.services.JobCategoryService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JobCategoryServiceImpl implements JobCategoryService {
    private final JobCategoryRepository jobCategoryRepository;
    private ModelMapper modelMapper;
    @Override
    public JobCategoryDto create(JobCategoryDto jobCategoryDto) {
        JobCategory jobCategory = modelMapper.map(jobCategoryDto, JobCategory.class);
        this.jobCategoryRepository.save(jobCategory);
        return modelMapper.map(jobCategory, JobCategoryDto.class);
    }

    @Override
    public JobCategoryDto update(JobCategoryDto jobCategoryDto, Integer id) {
        JobCategory jobCategory = this.jobCategoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job Category not found ID: " + id)
                );
        jobCategory.setName(jobCategoryDto.getName());
        this.jobCategoryRepository.save(jobCategory);
        return modelMapper.map(jobCategory, JobCategoryDto.class);
    }

    @Override
    public JobCategoryDto getById(Integer id) {
        JobCategory jobCategory = this.jobCategoryRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Job Category not found ID: " + id)
                );
        return modelMapper.map(jobCategory, JobCategoryDto.class);
    }

    @Override
    public List<JobCategoryDto> getAll() {
        List<JobCategory> jobCategories = this.jobCategoryRepository.findAll();
        return jobCategories
                .stream()
                .map(
                        jobCategory -> modelMapper.map(jobCategory, JobCategoryDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public List<JobCategoryDto> searchByName(String name) {
        List<JobCategory> jobCategories = jobCategoryRepository.findByName(name);
        if (jobCategories.get(0).getId() == null){
            throw new ResourceNotFoundException("Job category not found with name : " + name);
        }
        return jobCategories
                .stream()
                .map(
                        jobCategory -> modelMapper.map(jobCategory, JobCategoryDto.class)
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