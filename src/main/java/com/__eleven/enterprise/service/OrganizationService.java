package com.__eleven.enterprise.service;

import com.__eleven.enterprise.entity.Organization;
import com.__eleven.enterprise.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public Organization createOrganization (Organization organization) {
        return organizationRepository.save(organization);
    }
}
