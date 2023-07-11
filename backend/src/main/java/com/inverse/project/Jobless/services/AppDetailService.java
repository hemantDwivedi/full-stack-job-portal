package com.inverse.project.Jobless.services;

import com.inverse.project.Jobless.dto.ApplicationDetailsDto;

public interface AppDetailService {
    ApplicationDetailsDto create(ApplicationDetailsDto detailsDto, Integer jodId);
    ApplicationDetailsDto update(ApplicationDetailsDto detailsDto, Integer jodId, Integer id);
    void delete(Integer id);
}
