package com.maliroso.tms.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDto {

    @NotBlank(message = "title cannot be empty")
    @Size(max = 50, message = "title cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Enter valid characters")
    private String title;

    @NotBlank(message = "description cannot be empty")
    @Size(max = 200, message = "description cannot exceed 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Enter valid characters")
    private String description;

    private String status;
}