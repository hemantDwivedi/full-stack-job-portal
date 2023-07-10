package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ProjectDto;

public interface ProjectService {
    ProjectDto create(ProjectDto projectDto, int resume_id);
    ProjectDto update(ProjectDto projectDto, Integer id);
    void delete(Integer id);
}
