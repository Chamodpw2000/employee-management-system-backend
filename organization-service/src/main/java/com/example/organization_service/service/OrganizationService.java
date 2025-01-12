package com.example.organization_service.service;

import com.example.organization_service.dto.OrganizationDto;

import java.util.List;


public interface OrganizationService {

    OrganizationDto saveOrganization(OrganizationDto organizationDto);
    OrganizationDto getOrganizationByCode(String organizationCode);
    List<OrganizationDto> getAllOrganizations();
    String deleteOrganization(long organizationId);
    String updateOrganization(OrganizationDto organizationDto);


}
