package com.example.demo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@ApiModel(description = "All details about the Employee")
public class Employee {

	@ApiModelProperty(notes = "The database generated employee Id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@ApiModelProperty(notes = "The employee name")
	private String name;

	@Column
	@ElementCollection(targetClass = String.class)
	@ApiModelProperty(notes = "The employee contact details")
	private List<String> contact;

	@ApiModelProperty(notes = "The employee status")
	private Boolean isActive;

	@ApiModelProperty(notes = "The employee age")
	private Integer age;

	@ApiModelProperty(notes = "The employee department")
	private String department;
}
