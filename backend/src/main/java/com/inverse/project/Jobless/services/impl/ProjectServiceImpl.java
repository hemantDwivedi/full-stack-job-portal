package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.dto.ProjectDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Project;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.ProjectRepository;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.ProjectService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ResumeRepository resumeRepository;
    private ModelMapper modelMapper;

    @Override
    public ProjectDto create(ProjectDto projectDto, int resume_id) {
        Project project = modelMapper.map(projectDto, Project.class);
        Resume resume = this.resumeRepository.findById(resume_id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found ID: " + resume_id)
                );
        project.setResume(resume);
        this.projectRepository.save(project);
        return modelMapper.map(project, ProjectDto.class);
    }

    @Override
    public ProjectDto update(ProjectDto projectDto,Integer id) {
        Project project = this.projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Project not found ID: " + id)
                );
        project.setTitle(projectDto.getTitle());
        project.setDescription(projectDto.getDescription());
        project.setLink(projectDto.getLink());
        this.projectRepository.save(project);
        return modelMapper.map(project, ProjectDto.class);
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
