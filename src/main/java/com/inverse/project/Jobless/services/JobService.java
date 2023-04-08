package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.JobDto;

import java.util.List;

public interface JobService {
    JobDto create(JobDto jobDto,Integer jobCategoryId);
    JobDto update(JobDto jobDto, Integer jobCategoryId, Integer id);
    JobDto getById(Integer id);
    List<JobDto> getAll();
    void delete(Integer id);
}
