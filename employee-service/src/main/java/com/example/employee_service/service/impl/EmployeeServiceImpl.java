package com.example.employee_service.service.impl;

import com.example.employee_service.dto.ApiResponceDto;
import com.example.employee_service.dto.DepartmentDto;
import com.example.employee_service.dto.EmployeeDto;
import com.example.employee_service.dto.OrganizationDto;
import com.example.employee_service.entity.Employee;
import com.example.employee_service.repository.EmployeeRepository;
import com.example.employee_service.service.APIClient;
import com.example.employee_service.service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {


    private  static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);




    private EmployeeRepository employeeRepository;

//    private RestTemplate restTemplate;

//    @Autowired
    private WebClient webClient;

    private APIClient apiClient;


    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );
        Employee savedEmployee = employeeRepository.save(employee);

        EmployeeDto savedEmployeeDto = new EmployeeDto(
                savedEmployee.getId(),
                savedEmployee.getFirstName(),
                savedEmployee.getLastName(),
                savedEmployee.getEmail(),
                savedEmployee.getDepartmentCode(),
                savedEmployee.getOrganizationCode()

        );

        return savedEmployeeDto;

    }













    @Override
    public ApiResponceDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));

        EmployeeDto employeeDto = new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartmentCode(),
                employee.getOrganizationCode()
        );

        DepartmentDto departmentDto;
        try {
            departmentDto = webClient.get()
                    .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
                    .retrieve()
                    .onStatus(HttpStatus::is5xxServerError, response ->
                            Mono.error(new ServiceUnavailableException("Department service unavailable"))
                    )
                    .bodyToMono(DepartmentDto.class)
                    .block();
        } catch (Exception e) {
            departmentDto = getDefaultDepartment();
        }

        OrganizationDto organizationDto;
        try {
            organizationDto = webClient.get()
                    .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
                    .retrieve()
                    .onStatus(HttpStatus::is5xxServerError, response ->
                            Mono.error(new ServiceUnavailableException("Organization service unavailable"))
                    )
                    .bodyToMono(OrganizationDto.class)
                    .block();
        } catch (Exception e) {
            organizationDto = getDefaultOrganization();
        }

        return new ApiResponceDto(employeeDto, departmentDto, organizationDto);
    }

    private DepartmentDto getDefaultDepartment() {
        return new DepartmentDto(
                null,
                "No Department available",
                "No Department available",
                "No Department available"
        );
    }

    private OrganizationDto getDefaultOrganization() {
        return new OrganizationDto(

                "No Organization available",
                "No Organization available",
                "No Organization available"
        );
    }























//    @CircuitBreaker(name ="${spring.application.name}", fallbackMethod = "getDefaultDepartment")
   // @Retry(name ="${spring.application.name}", fallbackMethod = "getDefaultDepartment")

//    @Override
//    public ApiResponceDto getEmployeeById(Long id) {
//        Employee employee = employeeRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
//
//        // Create EmployeeDto directly
//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getDepartmentCode(),
//                employee.getOrganizationCode()
//        );
//
//        // Fetch department details
//        DepartmentDto departmentDto = webClient.get()
//                .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
//                .retrieve()
//                .onStatus(HttpStatus::is5xxServerError, response ->
//                        Mono.error(new ServiceUnavailableException("Department service unavailable"))
//                )
//                .bodyToMono(DepartmentDto.class)
//                .block();
//
//        // Fetch organization details
//        OrganizationDto organizationDto = webClient.get()
//                .uri("http://localhost:8083/api/organizations/" + employee.getOrganizationCode())
//                .retrieve()
//                .onStatus(HttpStatus::is5xxServerError, response ->
//                        Mono.error(new ServiceUnavailableException("Organization service unavailable"))
//                )
//                .bodyToMono(OrganizationDto.class)
//                .block();
//
//        // Create and return the response
//        return new ApiResponceDto(employeeDto, departmentDto, organizationDto);
//    }
//
    public class ResourceNotFoundException extends RuntimeException {
        private String resourceName;
        private String fieldName;
        private Object fieldValue;

        public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
            super(String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
            this.resourceName = resourceName;
            this.fieldName = fieldName;
            this.fieldValue = fieldValue;
        }
    }
    public class ServiceUnavailableException extends RuntimeException {
        public ServiceUnavailableException(String message) {
            super(message);
        }
    }



    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtosDto = new ArrayList<>();
        for(Employee employee : employees) {
            EmployeeDto employeeDto = new EmployeeDto(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getDepartmentCode(),
                    employee.getOrganizationCode()
            );
            employeeDtosDto.add(employeeDto);
        }

        return employeeDtosDto;
    }

    @Override
    public String deleteEmployee(long employeeId) {

        if(employeeRepository.existsById(employeeId)){
            employeeRepository.deleteById(employeeId);
            return "Deleted Successfully "+ employeeId;

        }else{

            throw new RuntimeException("No Employee for that id");
        }








    }

    @Override
    public String updateEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode(),
                employeeDto.getOrganizationCode()
        );

        employeeRepository.save(employee);
        return "Employee updated successfully with ID: " + employee.getId();
    }


//    public ApiResponceDto getDefaultDepartment(Long id, Exception exception) {
//        LOGGER.info("Inside getDefaultDepartment method");
//
//
//        Employee employee = employeeRepository.findById(id).get();
//
//
//
//
//
//        DepartmentDto departmentDto = new DepartmentDto();
//
//        departmentDto.setDepartmentName("Default Department");
//        departmentDto.setDepartmentCode("D001");
//        departmentDto.setDepartmentDescription("Devolopment Department");
//
//
//
//
//        EmployeeDto employeeDto = new EmployeeDto(
//                employee.getId(),
//                employee.getFirstName(),
//                employee.getLastName(),
//                employee.getEmail(),
//                employee.getDepartmentCode(),
//                employee.getOrganizationCode()
//        );
//
//        ApiResponceDto apiResponceDto = new ApiResponceDto(
//
//        );
//
//        apiResponceDto.setEmployeeDto(employeeDto);
//        apiResponceDto.setDepartmentDto(departmentDto);
//
//
//
//        return apiResponceDto;
//    }
}
