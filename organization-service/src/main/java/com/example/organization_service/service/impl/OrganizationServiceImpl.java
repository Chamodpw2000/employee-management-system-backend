package com.example.organization_service.service.impl;

import com.example.organization_service.dto.OrganizationDto;
import com.example.organization_service.entity.Organization;
import com.example.organization_service.mapper.OrganizationMapper;
import com.example.organization_service.repository.OrganizationRepository;
import com.example.organization_service.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService
{

    private static final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);
    @Autowired
    private OrganizationRepository organizationRepository;



    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        Organization savedOrganization = organizationRepository.save(organization);

        OrganizationDto organizationDto1 = OrganizationMapper.mapToOrganizationDto(savedOrganization);


        return organizationDto1;
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {



        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);

        OrganizationDto organizationDto = OrganizationMapper.mapToOrganizationDto(organization);
        return organizationDto;
    }

    @Override
    public List<OrganizationDto> getAllOrganizations() {

        List<Organization> organizations = organizationRepository.findAll();
        List<OrganizationDto> organizationDtos = new ArrayList<>();
        for(Organization organization: organizations){

            OrganizationDto organizationDto = OrganizationMapper.mapToOrganizationDto(organization);
            organizationDtos.add(organizationDto);
        }
        return organizationDtos;
    }

    @Override
    public String deleteOrganization(long organizationId) {
        if(organizationRepository.existsById(organizationId)){
            organizationRepository.deleteById(organizationId);
            return "Deleted Successfully " + organizationId;

        }else{

            throw new RuntimeException("No Organization for that id");
        }


    }

    @Override
    public String updateOrganization(OrganizationDto organizationDto) {
       Organization organization = OrganizationMapper.mapToOrganization(organizationDto);

        organizationRepository.save(organization);
        return "Employee updated successfully with ID: " + organization.getId();
    }




}
