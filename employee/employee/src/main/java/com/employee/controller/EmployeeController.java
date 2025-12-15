package com.employee.controller;

import com.employee.exception.EmployeeManagementSystemException;
import com.employee.model.EmployeeDTO;
import com.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Employee API", description = "Employee Management APIs")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;
    
    @Operation(summary = "Get all employees")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Success"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/employees")
    public List<EmployeeDTO> getAllEmployees() throws EmployeeManagementSystemException {
        return employeeService.getAllEmployees();
    }
    
    @Operation(summary = "Create new employee")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee created"),
        @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/employees")
    public EmployeeDTO addNewEmployee(
            @Parameter(description = "Employee data", required = true)
            @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.addNewEmployee(employeeDTO);
    }
    
    @Operation(summary = "Get employee by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee found"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @GetMapping("/employees/{id}")
    public EmployeeDTO getEmployeeById(
            @Parameter(description = "Employee ID", required = true, example = "1")
            @PathVariable Long id) throws Exception {
        return employeeService.getEmployeeById(id);
    }
    
    @Operation(summary = "Update employee")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee updated"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/employees/{id}")
    public EmployeeDTO updateEmployee(
            @Parameter(description = "Employee ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Updated employee data", required = true)
            @RequestBody EmployeeDTO employeeDTO) throws Exception {
        return employeeService.updateEmployee(id, employeeDTO);
    }
    
    @Operation(summary = "Delete employee")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Employee deleted"),
        @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(
            @Parameter(description = "Employee ID", required = true, example = "1")
            @PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
    }
}