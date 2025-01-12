package com.example.organization_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@Table(name="organizations")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public Organization() {

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

    public long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Column(nullable = false)
    private String organizationName;


    private String organizationDescription;

    @Column(nullable = false, unique = true)
    private String organizationCode;


    @CreationTimestamp
    private LocalDateTime createdDate;


    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
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


    public Organization(long id, LocalDateTime createdDate, String organizationCode, String organizationDescription, String organizationName) {
        this.id = id;
        this.createdDate = createdDate;
        this.organizationCode = organizationCode;
        this.organizationDescription = organizationDescription;
        this.organizationName = organizationName;
    }

//    public Organization( String organizationCode, String organizationDescription, String organizationName) {
//
//        this.organizationCode = organizationCode;
//        this.organizationDescription = organizationDescription;
//        this.organizationName = organizationName;
//    }


}
