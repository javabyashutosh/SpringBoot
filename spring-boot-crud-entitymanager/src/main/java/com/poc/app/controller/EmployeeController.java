package com.poc.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.app.entity.Employee;
import com.poc.app.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@PostMapping({ "/createEmp" })
	public String createEmployee(@RequestBody Employee employee) {
		System.out.println("Inside createEmployee() method");
		int id = employeeService.createEmployee(employee);
		return "Employee Created with Employee id:" + id;
	}

	@GetMapping({ "/getEmpById/{id}" })
	public Employee fetchEmployee(@PathVariable("id") int id) {
		System.out.println("Inside fetchEmployee() method");
		Employee employee = employeeService.fetchEmployee(id);
		return employee;
	}

	@GetMapping({ "/getAllEmp" })
	public List<Employee> fetchAllEmployee() {
		System.out.println("Inside fetchAllEmployee() method");
		List<Employee> employeeList = (List<Employee>) employeeService.fetchAllEmployees();
		return employeeList;
	}

	@PutMapping({ "/updateEmp" })
	public void updateEmployee(@RequestBody Employee employee) {
		System.out.println("Inside updateEmployee() method");
		employeeService.updateEmployee(employee);
	}

	@DeleteMapping({ "/deleteEmp/{id}" })
	public void deleteEmployee(@PathVariable("id") int id) {
		System.out.println("Inside deleteEmployee() method");
		employeeService.deleteEmployee(id);
	}
}