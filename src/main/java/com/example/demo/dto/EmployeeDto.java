package com.example.demo.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class EmployeeDto {

	private Integer id;
	@NotEmpty(message="name can not be empty")
	@NotNull(message="name can not be null")
	private String name;
	@NotNull(message="contacts can not be null")
	private List<String> contact;
	@NotNull(message="IsActive can not be null")
	private Boolean isActive;
	@NotNull(message="age can not be null")
	private Integer age;
	@NotNull(message="department can not be null")
	@NotEmpty(message="department can not be empty")
	private String department;
	
}
