package com.poc.app.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.app.entity.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public int createEmployee(final Employee employee) {
		System.out.println("inside Service class createEmployee() method");
		Session session = entityManager.unwrap(Session.class);
		int id = (int) session.save((Object) employee);
		return id;
	}

	public Employee fetchEmployee(final int id) {
		Session session = entityManager.unwrap(Session.class);
		Employee employee = session.get(Employee.class, id);
		return employee;
	}

	public List<Employee> fetchAllEmployees() {
		Session session = entityManager.unwrap(Session.class);
		Query<Employee> query = session.createQuery("from Employee", Employee.class);
		List<Employee> employeeList = query.getResultList();
		return employeeList;
	}

	@Transactional
	public void updateEmployee(final Employee employee) {
		Session session =entityManager.unwrap(Session.class);
		session.update((Object) employee);
	}

	@Transactional
	public void deleteEmployee(final int id) {
		System.out.println("inside Service deleteEmployee() method");
		Session session =entityManager.unwrap(Session.class);
		Employee employee =session.get(Employee.class,id);
		session.delete((Object) employee);
	}
}