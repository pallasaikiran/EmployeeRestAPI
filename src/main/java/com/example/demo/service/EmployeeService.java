package com.example.demo.service;

import java.util.List;



import com.example.demo.dto.EmployeeDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;

public interface EmployeeService {
	
	public Employee getEmployeeById(Integer id) throws ResourceNotFoundException;
	public List<Employee> getAllEmployees();
	public Employee saveEmployee(EmployeeDto dto);
	public void deleteEmployee(Integer id) throws ResourceNotFoundException;
	public Employee updateEmployee(Employee employee) throws ResourceNotFoundException;
	
}
