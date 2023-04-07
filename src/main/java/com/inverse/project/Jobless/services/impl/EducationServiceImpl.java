package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.config.ModelMap;
import com.inverse.project.Jobless.dto.EducationDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Education;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.EducationRepository;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationServiceImpl implements EducationService {
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ModelMap modelMap;
    @Autowired
    private ResumeRepository resumeRepository;

    // create education
    @Override
    public EducationDto create(EducationDto educationDto, Integer resumeId) {
        Education education = this.modelMap.modelMapper().map(educationDto, Education.class);
        Resume resume = this.resumeRepository.findById(resumeId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found ID: " + resumeId)
                );
        education.setResume(resume);
        this.educationRepository.save(education);
        return this.modelMap.modelMapper().map(education, EducationDto.class);
    }

    // update education
    @Override
    public EducationDto update(EducationDto educationDto,Integer resumeId, Integer id) {
        Education education = this.educationRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Education not found ID: " + id)
                );
        if (!education.getResume().getId().equals(resumeId)) {
            throw new ResourceNotFoundException("Resume Id not found: " + resumeId);
        }
        education.setName(educationDto.getName());
        education.setDegree(educationDto.getDegree());
        education.setStartDate(educationDto.getStartDate());
        education.setEndDate(educationDto.getEndDate());
        education.setMarks(educationDto.getMarks());
        this.educationRepository.save(education);
        return this.modelMap.modelMapper().map(education, EducationDto.class);
    }

    // delete education by ID.
    @Override
    public void delete(Integer resumeId, Integer id) {
        Education education = this.educationRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Education not found ID: " + id)
                );
        if (!education.getResume().getId().equals(resumeId)) {
            throw new ResourceNotFoundException("Resume Id not found: " + resumeId);
        }
        this.educationRepository.delete(education);
    }
}
