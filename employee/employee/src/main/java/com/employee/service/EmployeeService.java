package com.employee.service;
import java.util.List;

import com.employee.exception.EmployeeManagementSystemException;
import com.employee.model.EmployeeDTO;


public interface EmployeeService {
	
	List<EmployeeDTO> getAllEmployees() throws EmployeeManagementSystemException;

	EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO);
	
	EmployeeDTO getEmployeeById(Long id) throws EmployeeManagementSystemException;

	EmployeeDTO updateEmployee(Long id,EmployeeDTO employeeDTO) throws EmployeeManagementSystemException;
	
	void deleteEmployee(Long id) throws EmployeeManagementSystemException;
}
