package com.inverse.project.Jobless.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
    // private Integer id;
    @NotBlank(message = "title should not be blank")
    private String title;
    @NotBlank(message = "description should not be blank")
    private String description;
    @NotBlank(message = "project link should not be blank")
    private String link;
}
