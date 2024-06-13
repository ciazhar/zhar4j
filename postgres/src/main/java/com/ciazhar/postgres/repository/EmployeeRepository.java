package com.ciazhar.postgres.repository;

import com.ciazhar.postgres.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

/**
 * Repository is an interface that provides access to data in a database
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // Derived query to find an employee by designation
    Employee findByDesignation(String designation);

    // Derived query to find an employee by first name wrapped in Optional
    Optional<Employee> findByFirstName(String firstname);

    // Derived query to find employees by date of birth
    List<Employee> findByDateOfBirth(LocalDate dateOfBirth);

    // Custom query to limit the number of results returned
    List<Employee> findByDateOfBirth(LocalDate dateOfBirth, Pageable pageable);

    // Derived query to find employees by a date range
    List<Employee> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);

    // Custom query to find employees by first name or last name
    @Query("SELECT e FROM Employee e WHERE e.firstName = :name OR e.lastName = :name")
    List<Employee> findByFirstNameOrLastName(String name);

    // Derived query with pagination
    Slice<Employee> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

    // SpEL expression in custom query to find by first or last name
    @Query("SELECT e FROM Employee e WHERE e.firstName = :#{#employee.firstName} OR e.lastName = :#{#employee.lastName}")
    Iterable<Employee> findByFirstnameOrLastname(Employee employee);

    // Streaming query, good for retrieve large amounts of data
    @Query("SELECT e FROM Employee e")
    Stream<Employee> streamAllEmployee();

    // Asynchronous query, create new thread
    @Async
    CompletableFuture<List<Employee>> readAllBy();

    // Custom query to find employees by age greater than a certain value
    @Query("SELECT e FROM Employee e WHERE e.age > :age")
    List<Employee> findByAgeGreaterThan(int age);

    // Derived query to find employees by a list of designations
    List<Employee> findByDesignationIn(List<String> designations);

    // Custom query to count employees by department
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.designation = :designation")
    long countByDesignation(String designation);

    // Custom query with sorting
    @Query("SELECT e FROM Employee e WHERE e.age > :salary ORDER BY e.age DESC")
    List<Employee> findByAgeGreaterThanOrderByAgeDesc(double salary);

    // Derived query to find top 5 employees by age
    List<Employee> findTop5ByOrderByAgeDesc();

    // Custom query using native SQL
    @Query(value = "SELECT * FROM employee WHERE first_name = :firstName AND last_name = :lastName", nativeQuery = true)
    List<Employee> findByFirstNameAndLastNameNative(String firstName, String lastName);
}