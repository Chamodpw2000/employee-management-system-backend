package com.example.organization_service.controller;

import com.example.organization_service.dto.OrganizationDto;
import com.example.organization_service.service.OrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")

public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<OrganizationDto> saveOrganization(@RequestBody OrganizationDto  organizationDto){



        OrganizationDto savedOrganizationDto =  organizationService.saveOrganization(organizationDto);

        return  new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);





    }



    @GetMapping({"{code}"})
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code") String organizationCode){



        OrganizationDto savedOrganizationDto =  organizationService.getOrganizationByCode(organizationCode);

        return ResponseEntity.ok(savedOrganizationDto);








    }

    @GetMapping
    public ResponseEntity<List<OrganizationDto>> getAllOrganizations() {
        List<OrganizationDto> organizationDtos = organizationService.getAllOrganizations();
        return ResponseEntity.ok(organizationDtos);
    }
    @DeleteMapping(path = "deleteorganization/{id}")
    public String deleteDepartment(@PathVariable (value = "id") long organizationtId){
        String deleted = organizationService.deleteOrganization(organizationtId);

        return deleted;


    }



    @PutMapping(path = "updateorganization/{id}")
    public String updateOrganization(@PathVariable (value = "id") String orgId, @RequestBody OrganizationDto employeeDto){
        String edited = organizationService.updateOrganization(employeeDto);
        return edited;
    }

}
