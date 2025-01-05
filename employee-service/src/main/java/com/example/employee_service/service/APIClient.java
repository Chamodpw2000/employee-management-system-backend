package com.example.employee_service.service;

import com.example.employee_service.dto.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url ="http://localhost:8080/" , value = "DEPARTMENT-SERVICE")

@FeignClient(name="DEPARTMENT-SERVICE")
public interface APIClient {


    @GetMapping("api/departments/{department-code}")
    DepartmentDto getDepartment(@PathVariable(value="department-code") String departmentCode);







}
