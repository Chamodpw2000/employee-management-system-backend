package com.example.employee_service.service.impl;

import com.example.employee_service.dto.ApiResponceDto;
import com.example.employee_service.dto.DepartmentDto;
import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.service.APIClient;
import com.example.employee_service.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;

//    @Autowired
//    private WebClient webClient;

    private APIClient apiClient;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode()

        );

        return savedEmployeeDto;

    }

    @Override
    public ApiResponceDto getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id).get();

        // Communication with rest template

//        ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+ employee.getDepartmentCode(),
//                DepartmentDto.class
//                );


//        DepartmentDto departmentDto = responseEntity.getBody();

// Communication with web client
//       DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .bodyToMono(DepartmentDto.class)
//                .block();



        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());




        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode()
        );

        ApiResponceDto apiResponceDto = new ApiResponceDto(

        );

        apiResponceDto.setEmployeeDto(employeeDto);
        apiResponceDto.setDepartmentDto(departmentDto);



        return apiResponceDto;
    }
}
