package com.ciazhar.postgres.controller;

import com.ciazhar.postgres.model.dto.ApiResponse;
import com.ciazhar.postgres.model.entity.Employee;
import com.ciazhar.postgres.model.projection.EmployeeDetailsProjection;
import com.ciazhar.postgres.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Controller class is where all the user requests are handled and required/appropriate
 * responses are sent
 */
@RestController
@RequestMapping("/employee/v1")
@RequiredArgsConstructor
@Validated
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllEmployees() {
        return ResponseEntity.ok().body(new ApiResponse("success", employeeService.getAllEmployees(), employeeService.count()));
    }

    @GetMapping("/stream")
    public ResponseEntity<ApiResponse> streamAllEmployee() {
        try (Stream<Employee> employeeStream = employeeService.streamAllEmployee()) {
            return ResponseEntity.ok().body(new ApiResponse("success", employeeStream.collect(Collectors.toList())));
        }
    }

    @GetMapping("/async")
    public CompletableFuture<List<Employee>> readAllBy() {
        return employeeService.readAllBy();
    }

    @GetMapping("/older-than")
    public Collection<EmployeeDetailsProjection> getEmployeesOlderThan(@RequestParam int age) {
        return employeeService.getEmployeesOlderThan(age);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getEmployeeById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ApiResponse("success", employeeService.getEmployeeById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> saveEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(new ApiResponse("success", employeeService.saveEmployee(employee)));
    }


    @PostMapping("/hire")
    public ResponseEntity<Employee> hireEmployee(@RequestBody Employee employee,
                                                 @RequestParam String departmentName) {
        Employee hiredEmployee = employeeService.hireEmployeeAndAssignToDepartment(employee, departmentName);
        return ResponseEntity.ok(hiredEmployee);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok().body(new ApiResponse("success", employeeService.updateEmployee(employee)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployeeById(@PathVariable Integer id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok().body(new ApiResponse("success"));
    }
}