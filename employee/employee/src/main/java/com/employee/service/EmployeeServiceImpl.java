package com.employee.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.config.EmployeeMapper;
import com.employee.exception.EmployeeManagementSystemException;
import com.employee.model.Employee;
import com.employee.model.EmployeeDTO;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public List<EmployeeDTO> getAllEmployees() throws EmployeeManagementSystemException {
        List<Employee> employees = employeeRepository.findAll();
        if(employees.isEmpty() || employees==null)
        {
        	throw new EmployeeManagementSystemException("No employees available");
        }
        return employees.stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

	@Override
	public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
		Employee employee=employeeMapper.toEntity(employeeDTO);
		Employee savedEmployee=employeeRepository.save(employee);
		return employeeMapper.toDTO(savedEmployee);
	}

	@Override
	public EmployeeDTO getEmployeeById(Long id) throws EmployeeManagementSystemException {
		Employee employee=employeeRepository.findById(id).get();
		if(employee==null)
		{
			throw new EmployeeManagementSystemException("No employee found for this id");
		}
		return employeeMapper.toDTO(employee);
	}

	@Override
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) throws EmployeeManagementSystemException {
		Employee employee=employeeRepository.findById(id).get();
		if(employee==null)
		{
			throw new EmployeeManagementSystemException("No employee found for this id");
		}
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setEmailId(employeeDTO.getEmailId());
		employee.setLastName(employeeDTO.getLastName());
		
		Employee employeeFinal=employeeRepository.save(employee);
		return employeeMapper.toDTO(employeeFinal);
	}

	@Override
	public void deleteEmployee(Long id) throws EmployeeManagementSystemException {
		Employee employee=employeeRepository.findById(id).get();
		if(employee==null)
		{
			throw new EmployeeManagementSystemException("There is no employee with this id: "+id);
		}
		employeeRepository.deleteById(id);
		
	}

}
