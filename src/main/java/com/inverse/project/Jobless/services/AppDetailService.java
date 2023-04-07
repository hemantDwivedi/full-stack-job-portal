package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ApplicationDetailsDto;

public interface AppDetailService {
    ApplicationDetailsDto create(ApplicationDetailsDto detailsDto, Integer applicantId);
    ApplicationDetailsDto update(ApplicationDetailsDto detailsDto, Integer applicantId, Integer id);
    void delete(Integer applicantId, Integer id);
}
