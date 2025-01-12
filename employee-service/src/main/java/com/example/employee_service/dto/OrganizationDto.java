package com.example.employee_service.dto;


import java.time.LocalDateTime;

//@NoArgsConstructor
//@AllArgsConstructor
public class OrganizationDto {


    private long id;

    private String organizationName;


    private String organizationDescription;

    public OrganizationDto( String organizationCode, String organizationDescription, String organizationName) {

        this.organizationCode = organizationCode;
        this.organizationDescription = organizationDescription;
        this.organizationName = organizationName;
    }

    public void setId(long id) {
        this.id = id;
    }


    public OrganizationDto(long id, LocalDateTime createdDate, String organizationCode, String organizationDescription, String organizationName) {
        this.id = id;
        this.createdDate = createdDate;
        this.organizationCode = organizationCode;
        this.organizationDescription = organizationDescription;
        this.organizationName = organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    private String organizationCode;

    public OrganizationDto() {
    }

    private LocalDateTime createdDate;
}
