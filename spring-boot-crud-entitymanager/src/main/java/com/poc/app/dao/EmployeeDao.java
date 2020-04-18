package com.poc.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.poc.app.entity.*;

public interface EmployeeDao extends CrudRepository<Employee, Integer>
{
}