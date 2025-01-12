package com.example.employee_service.controller;


import com.example.employee_service.dto.ApiResponceDto;
import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")

@CrossOrigin(origins = "http://localhost:5173")
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


    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @DeleteMapping(path = "deleteemployee/{id}")
    public String deleteEmployee(@PathVariable (value = "id") long employeeId){
        String deletedEmployee = employeeService.deleteEmployee(employeeId);

        return deletedEmployee;
    }


    @PutMapping(path = "updateemployee/{id}")
    public String updateEmployee(@PathVariable (value = "id") long employeeId, @RequestBody EmployeeDto employeeDto){
        String edited = employeeService.updateEmployee(employeeDto);
        return edited;
    }


}
