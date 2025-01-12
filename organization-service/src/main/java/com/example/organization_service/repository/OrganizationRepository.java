package com.example.organization_service.repository;

import com.example.organization_service.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {


    Organization findOrganizationByOrganizationCode(String organizationCode);

    Organization findByOrganizationCode(String organizationCode);
}
