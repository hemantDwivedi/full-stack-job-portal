package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.JobCategoryDto;

import java.util.List;

public interface JobCategoryService {
    JobCategoryDto create(JobCategoryDto jobCategoryDto, Integer employerId);
    JobCategoryDto update(JobCategoryDto jobCategoryDto,Integer employerId,  Integer id);
    JobCategoryDto getById(Integer id);
    List<JobCategoryDto> getAll();
    void delete(Integer id);
}