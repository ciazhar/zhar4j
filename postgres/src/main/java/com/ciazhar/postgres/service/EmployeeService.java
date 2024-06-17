package com.ciazhar.postgres.service;


import com.ciazhar.postgres.model.Employee;
import com.ciazhar.postgres.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Service layer is where all the business logic lies
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {

    private final EmployeeRepository employeeRepo;

    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            return optionalEmployee.get();
        }
        log.info("Employee with id: {} doesn't exist", id);
        return null;
    }

    public Employee saveEmployee(Employee employee) {
        employee.setCreatedDate(LocalDateTime.now());
        employee.setLastModifiedDate(LocalDateTime.now());
        Employee savedEmployee = employeeRepo.save(employee);

        log.info("Employee with id: {} saved successfully", employee.getId());
        return savedEmployee;
    }

    public Employee updateEmployee(Employee employee) {
        Optional<Employee> existingEmployee = employeeRepo.findById(employee.getId());
        if (existingEmployee.isPresent()) {
            employee.setCreatedDate(existingEmployee.get().getCreatedDate());
            employee.setLastModifiedDate(LocalDateTime.now());

            Employee updatedEmployee = employeeRepo.save(employee);

            log.info("Employee with id: {} updated successfully", employee.getId());
            return updatedEmployee;
        }

        log.info("Employee with id: {} doesn't exist", employee.getId());
        return null;

    }

    public void deleteEmployeeById(Integer id) {
        employeeRepo.deleteById(id);
    }

    @Transactional
    public Stream<Employee> streamAllEmployee() {
        return employeeRepo.streamAllEmployee();
    }

    @Async
    public CompletableFuture<List<Employee>> readAllBy() {
        return employeeRepo.readAllBy();
    }

}