package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.dto.EducationDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Education;
import com.inverse.project.Jobless.models.Resume;
import com.inverse.project.Jobless.repositories.EducationRepository;
import com.inverse.project.Jobless.repositories.ResumeRepository;
import com.inverse.project.Jobless.services.EducationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EducationServiceImpl implements EducationService {
    private final EducationRepository educationRepository;
    private final ModelMapper modelMapper;
    private final ResumeRepository resumeRepository;
    // create education
    @Override
    public EducationDto create(EducationDto educationDto, Integer resumeId) {
        Education education = modelMapper.map(educationDto, Education.class);
        Resume resume = this.resumeRepository.findById(resumeId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Resume not found ID: " + resumeId)
                );
        education.setResume(resume);
        this.educationRepository.save(education);
        return modelMapper.map(education, EducationDto.class);
    }

    // update education
    @Override
    public EducationDto update(EducationDto educationDto, Integer id) {
        Education education = this.educationRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Education not found ID: " + id)
                );
        education.setName(educationDto.getName());
        education.setDegree(educationDto.getDegree());
        education.setStartDate(educationDto.getStartDate());
        education.setEndDate(educationDto.getEndDate());
        education.setMarks(educationDto.getMarks());
        this.educationRepository.save(education);
        return modelMapper.map(education, EducationDto.class);
    }

    // delete education by ID.
    @Override
    public void delete(Integer id) {
        Education education = this.educationRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Education not found ID: " + id)
                );
        this.educationRepository.delete(education);
    }
}
