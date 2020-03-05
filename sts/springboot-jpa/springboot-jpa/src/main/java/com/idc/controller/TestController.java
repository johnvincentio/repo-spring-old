package com.idc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idc.persistence.EmployeeRepository;
import com.idc.persistence.entity.Employee;

@RestController
public class TestController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@RequestMapping("/employee")
	public List<Employee> getTest() {
		System.out.println("--- getTest");
//		return new ArrayList<Employee>();
		return employeeRepository.findAll();
	}
}
