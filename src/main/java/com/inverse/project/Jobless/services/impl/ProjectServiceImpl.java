package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.util.ValueMapper;
import com.inverse.project.Jobless.dto.ProjectDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Project;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.ProjectRepository;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.ProjectService;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ValueMapper valueMapper;
    private final ResumeRepository resumeRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository, ValueMapper valueMapper, ResumeRepository resumeRepository) {
        this.projectRepository = projectRepository;
        this.valueMapper = valueMapper;
        this.resumeRepository = resumeRepository;
    }

    @Override
    public ProjectDto create(ProjectDto projectDto, int resume_id) {
        Project project = this.valueMapper.modelMapper().map(projectDto, Project.class);
        Resume resume = this.resumeRepository.findById(resume_id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found ID: " + resume_id)
                );
        project.setResume(resume);
        this.projectRepository.save(project);
        return this.valueMapper.modelMapper().map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto,Integer resumeId, Integer id) {
        Project project = this.projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project not found ID: " + id)
                );
        if (!project.getResume().getId().equals(resumeId)) {
            throw new ResourceNotFoundException("Resume Id not found: " + resumeId);
        }
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setLink(projectDto.getLink());
        this.projectRepository.save(project);
        return this.valueMapper.modelMapper().map(project, ProjectDto.class);
    }

    @Override
    public void delete(Integer resumeId, Integer id) {
        Project project = this.projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project not found ID: " + id)
                );
        if (!project.getResume().getId().equals(resumeId)) {
            throw new ResourceNotFoundException("Resume Id not found: " + resumeId);
        }
        this.projectRepository.delete(project);
    }
}
