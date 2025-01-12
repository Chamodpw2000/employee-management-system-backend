package com.example.organization_service.mapper;

import com.example.organization_service.dto.OrganizationDto;
import com.example.organization_service.entity.Organization;

public class OrganizationMapper {



    public static OrganizationDto mapToOrganizationDto(Organization organization) {

        OrganizationDto organizationDto = new OrganizationDto(
                organization.getId(),
                organization.getCreatedDate(),

                organization.getOrganizationCode(),


                organization.getOrganizationDescription(),


                organization.getOrganizationName()


                );

        return organizationDto;
    }

    public static Organization mapToOrganization(OrganizationDto organizationDto) {
        Organization organization = new Organization(
                organizationDto.getId(),
                organizationDto.getCreatedDate(),
                organizationDto.getOrganizationCode(),

                organizationDto.getOrganizationDescription(),
                organizationDto.getOrganizationName()

                );

        return organization;
    }



}
