package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee getEmployeeById(Integer id) throws ResourceNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			throw new ResourceNotFoundException("Record Not Found with id:" + id);
		}
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();

	}

	@Override
	public Employee saveEmployee(EmployeeDto employeeDto) {

		Employee savedEmployee = employeeRepository.save(toEntity(employeeDto));

		System.out.println(savedEmployee.toString());
		return employeeRepository.save(toEntity(employeeDto));

	}

	@Override
	public void deleteEmployee(Integer id) throws ResourceNotFoundException {
		Optional<Employee> employee = employeeRepository.findById(id);
		if (employee.isPresent()) {
			employeeRepository.delete(employee.get());
		} else {
			throw new ResourceNotFoundException("Record Not Found with id:" + id);
		}

	}

	@Override
	public Employee updateEmployee(Employee employee) throws ResourceNotFoundException {
		Optional<Employee> db = employeeRepository.findById(employee.getId());

		if (db.isPresent()) {
			Employee employeeUpdate = db.get();
			employeeUpdate.setId(employee.getId());
			employeeUpdate.setName(employee.getName());
			employeeUpdate.setAge(employee.getAge());

			employeeUpdate.setContact(employee.getContact());
			employeeUpdate.setIsActive(employee.getIsActive());
			employeeUpdate.setDepartment(employee.getDepartment());
			employeeRepository.save(employeeUpdate);
			return employeeUpdate;
		} else {
			throw new ResourceNotFoundException("Record Not Found with id:" + employee.getId());
		}

	}

	private Employee toEntity(EmployeeDto dto) {
		Employee entity = new Employee();
		if (null != dto) {
			entity.setId(dto.getId());
			entity.setName(dto.getName());
			entity.setContact(dto.getContact());
			entity.setIsActive(dto.getIsActive());
			entity.setAge(dto.getAge());
			entity.setDepartment(dto.getDepartment());
		}

		System.out.println(dto.toString());
		System.out.println(entity.toString());
		return entity;
	}
}
