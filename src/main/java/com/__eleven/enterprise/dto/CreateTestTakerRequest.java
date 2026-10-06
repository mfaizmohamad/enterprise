package com.__eleven.enterprise.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTestTakerRequest {
    private String scheduleName;

    @NotBlank(message = "Username is required")
    @Size(max=20, message = "Maximum character for username is 20")
    private String username;

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @NotBlank(message = "Organization Id is required")
    private String organizationId;
}
