package com.__eleven.enterprise.dto;

import lombok.Data;

@Data
public class CreateTestTakerRequest {
    private String scheduleName;
    private String username;
    private String fullName;
    private String organizationId;
}
