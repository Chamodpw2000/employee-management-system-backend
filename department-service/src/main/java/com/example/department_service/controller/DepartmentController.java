package com.example.department_service.controller;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.entity.Department;
import com.example.department_service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/departments")
@CrossOrigin(origins = "http://localhost:5173")
public class DepartmentController {
    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
        DepartmentDto saveDepartment = departmentService.saveDepartment(departmentDto);
        return new ResponseEntity<>(saveDepartment, HttpStatus.CREATED);

    }

    @GetMapping("{department-code}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable(value = "department-code") String departmentCode) {
        DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
        return new ResponseEntity<>(departmentDto, HttpStatus.OK);
    }

    ;

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departmentDtos = departmentService.getAllDepartments();
        return new ResponseEntity<>(departmentDtos, HttpStatus.OK);
    }

    ;

    @DeleteMapping(path = "deletedepartment/{id}")
    public String deleteDepartment(@PathVariable(value = "id") long departmentId) {
        String deleted = departmentService.deleteDepartment(departmentId);

        return deleted;


    }


    @PutMapping(path = "updatedepartment/{id}")
    public String updateDepartment(@PathVariable(value = "id") String departmentId, @RequestBody DepartmentDto departmentDto) {
        String edited = departmentService.updateDepartment(departmentDto);
        return edited;
    }




}
