package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.JobCategoryDto;

import java.util.List;

public interface JobCategoryService {
    JobCategoryDto create(JobCategoryDto jobCategoryDto);
    JobCategoryDto update(JobCategoryDto jobCategoryDto,Integer id);
    JobCategoryDto getById(Integer id);
    List<JobCategoryDto> getAll();
    List<JobCategoryDto> searchByName(String name);
    void delete(Integer id);
}
