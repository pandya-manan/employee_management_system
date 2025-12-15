package com.employee.config;

import com.employee.model.Employee;
import com.employee.model.EmployeeDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    
    private final ObjectMapper objectMapper;
    
    public EmployeeMapper() {
        this.objectMapper = new ObjectMapper();
        
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
    
    public Employee toEntity(EmployeeDTO employeeDTO) {
        return objectMapper.convertValue(employeeDTO, Employee.class);
    }
    
    public EmployeeDTO toDTO(Employee employee) {
        return objectMapper.convertValue(employee, EmployeeDTO.class);
    }
}