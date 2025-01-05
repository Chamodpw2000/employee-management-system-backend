package com.example.employee_service.controller;


import com.example.employee_service.dto.ApiResponceDto;
import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.saveEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }



    @GetMapping("{id}")
    public ResponseEntity<ApiResponceDto> getEmployee(@PathVariable Long id){
        ApiResponceDto apiResponceDto = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(apiResponceDto,HttpStatus.OK);
    }


}
