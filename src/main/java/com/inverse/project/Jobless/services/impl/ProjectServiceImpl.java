package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.config.ModelMap;
import com.inverse.project.Jobless.dto.ProjectDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Project;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.ProjectRepository;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ModelMap modelMap;
    @Autowired
    private ResumeRepository resumeRepository;

    @Override
    public ProjectDto create(ProjectDto projectDto, int resume_id) {
        Project project = this.modelMap.modelMapper().map(projectDto, Project.class);
        Resume resume = this.resumeRepository.findById(resume_id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found ID: " + resume_id)
                );
        project.setResume(resume);
        this.projectRepository.save(project);
        return this.modelMap.modelMapper().map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto, Integer id) {
        Project project = this.projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project not found ID: " + id)
                );
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setLink(projectDto.getLink());
        this.projectRepository.save(project);
        return this.modelMap.modelMapper().map(project, ProjectDto.class);
    }

    @Override
    public void delete(Integer id) {
        Project project = this.projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project not found ID: " + id)
                );
        this.projectRepository.delete(project);
    }
}
