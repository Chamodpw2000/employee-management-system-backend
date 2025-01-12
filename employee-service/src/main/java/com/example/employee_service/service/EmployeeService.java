package com.example.employee_service.service;

import com.example.employee_service.dto.ApiResponceDto;
import com.example.employee_service.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto saveEmployee(EmployeeDto employeeDto);


    ApiResponceDto getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployees();

    String deleteEmployee(long employeeId);

    String updateEmployee(EmployeeDto employeeDto);


}
