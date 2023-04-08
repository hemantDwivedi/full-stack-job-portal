package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ApplicationDetailsDto;

public interface AppDetailService {
    ApplicationDetailsDto create(ApplicationDetailsDto detailsDto, Integer applicantId, Integer jodId);
    ApplicationDetailsDto update(ApplicationDetailsDto detailsDto, Integer applicantId, Integer jodId, Integer id);
    void delete(Integer applicantId, Integer id);
}
