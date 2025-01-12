package com.example.department_service.service.impl;

import com.example.department_service.dto.DepartmentDto;
import com.example.department_service.entity.Department;
import com.example.department_service.repository.DepartmentRepository;
import com.example.department_service.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRepository departmentRepository;



    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {





        Department department = new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );
        Department savedDepartment = departmentRepository.save(department);

        DepartmentDto savedDepartmentDto = new DepartmentDto(
                savedDepartment.getId(),
                savedDepartment.getDepartmentName(),
                savedDepartment.getDepartmentDescription(),
                savedDepartment.getDepartmentCode()
        );
        return savedDepartmentDto;
    }

    @Override
    public DepartmentDto getDepartmentByCode(String code) {
        Department department = departmentRepository.findByDepartmentCode(code);

        DepartmentDto departmentDto = new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getDepartmentCode()
        );
        return departmentDto;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtoList = new ArrayList<>();
        for(Department department : departments){
            DepartmentDto departmentDto =new DepartmentDto(
                    department.getId(),
                    department.getDepartmentName(),
                    department.getDepartmentDescription(),
                    department.getDepartmentCode()
            );

            departmentDtoList.add(departmentDto);
        }
        return departmentDtoList;
    }

    @Override
    public String deleteDepartment(long departmentId) {
        if(departmentRepository.existsById(departmentId)){
            departmentRepository.deleteById(departmentId);
            return "Deleted Successfully "+ departmentId;

        }else{

            throw new RuntimeException("No Department for that id");
        }


    }

    @Override
    public String updateDepartment(DepartmentDto departmentDto) {
        Department department = new Department(
              departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getDepartmentCode()
        );



        departmentRepository.save(department);
        return "Employee updated successfully with ID: " + department.getId();
    }
}
