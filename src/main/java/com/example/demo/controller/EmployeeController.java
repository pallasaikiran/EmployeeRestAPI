package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("employee")
@Api(value = "Employee Management System", description = "Operations pertaining to employee in Employee Management System")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ApiOperation(value = "Get an employee by Id")
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(
			@ApiParam(value = "Employee id from which employee object will retrieve", required = true) @PathVariable Integer id)
			throws ResourceNotFoundException {

		return ResponseEntity.ok().body(employeeService.getEmployeeById(id));
	}

	@ApiOperation(value = "View a list of available employees", response = List.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "successfully retrieved list"),
			@ApiResponse(code = 401, message = "you are not authorized to vie the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping("allEmployees")
	public List<Employee> getAllEmployees() {
		List<Employee> employee = new ArrayList<>();
		System.out.println(employee.toString());

		return employeeService.getAllEmployees();
	}

	@ApiOperation(value = "Add an employee")
	@PostMapping("saveEmployee")
	public ResponseEntity<Employee> saveEmployee(
			@ApiParam(value = "Employee object store in database table", required = true) @Valid @RequestBody EmployeeDto dto) {
		return ResponseEntity.ok().body(employeeService.saveEmployee(dto));
	}

	@ApiOperation(value = "Update an employee")
	@PutMapping("update/{id}")
	public ResponseEntity<Employee> updateEmployee(
			@ApiParam(value = "Employee Id to update employee object", required = true) @PathVariable Integer id,
			@ApiParam(value = "Update employee object", required = true) @Valid @RequestBody Employee employeeDetails)
			throws ResourceNotFoundException {
		employeeDetails.setId(id);

		String response = employeeDetails.toString();
		System.out.println(response);
		return ResponseEntity.ok().body(employeeService.updateEmployee(employeeDetails));

	}

	@ApiOperation(value = "Delete an employee")
	@DeleteMapping("delete/{id}")
	public Map<String, Boolean> deleteEmployee(
			@ApiParam(value = "Employee Id from hich employee object will delete from database table", required = true) @PathVariable Integer id)
			throws ResourceNotFoundException {
		employeeService.deleteEmployee(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		System.out.println(response.toString());
		return response;

	}
}
