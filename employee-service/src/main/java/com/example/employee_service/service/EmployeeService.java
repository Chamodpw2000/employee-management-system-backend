package com.example.employee_service.service;

import com.example.employee_service.dto.ApiResponceDto;
import com.example.employee_service.dto.EmployeeDto;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);


    ApiResponceDto getEmployeeById(Long id);
}
