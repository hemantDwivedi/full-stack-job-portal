package com.inverse.project.Jobless.services.impl;

import com.inverse.project.Jobless.config.ModelMap;
import com.inverse.project.Jobless.dto.ApplicationDetailsDto;
import com.inverse.project.Jobless.exceptions.ResourceNotFoundException;
import com.inverse.project.Jobless.models.Applicant;
import com.inverse.project.Jobless.models.ApplicationDetails;
import com.inverse.project.Jobless.repositories.ApplicantRepository;
import com.inverse.project.Jobless.repositories.ApplicationDetailRepository;
import com.inverse.project.Jobless.services.AppDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppDetailServiceImpl implements AppDetailService {
    @Autowired
    private ApplicationDetailRepository detailRepository;
    @Autowired
    private ApplicantRepository applicantRepository;
    @Autowired
    private ModelMap modelMap;

    // create application details
    @Override
    public ApplicationDetailsDto create(ApplicationDetailsDto detailsDto, Integer applicantId) {
        ApplicationDetails application = this.modelMap.modelMapper().map(detailsDto, ApplicationDetails.class);
        Applicant applicant = this.applicantRepository.findById(applicantId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException("Applicant Id not found: " + applicantId)
                        );
        application.setApplicant(applicant);
        this.detailRepository.save(application);
        return this.modelMap.modelMapper().map(applicant, ApplicationDetailsDto.class);
    }

    // udpate application details
    @Override
    public ApplicationDetailsDto update(ApplicationDetailsDto detailsDto, Integer applicantId, Integer id){
        ApplicationDetails application = this.detailRepository.findById(id)
                .orElseThrow(
                        ()  -> new ResourceNotFoundException("Application Details not found ID: " + id)
                );
        if (!application.getApplicant().getId().equals(applicantId)) {
            throw new ResourceNotFoundException("Applicant Id not found: " + applicantId);
        }
        application.setStatus(detailsDto.getStatus());
        this.detailRepository.save(application);
        return this.modelMap.modelMapper().map(application, ApplicationDetailsDto.class);
    }

    // delete specific application details
    @Override
    public void delete(Integer applicantId, Integer id) {
        ApplicationDetails applicationDetails = this.detailRepository.findById(id)
                .orElseThrow(
                        ()  -> new ResourceNotFoundException("Application Details not found ID: " + id)
                );

        if (!applicationDetails.getApplicant().getId().equals(applicantId)) {
            throw new ResourceNotFoundException("Applicant Id not found: " + applicantId);
        }
        this.detailRepository.delete(applicationDetails);
    }
}
