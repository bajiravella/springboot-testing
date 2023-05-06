package com.mynee.service;

import java.util.List;
import java.util.Optional;

import com.mynee.model.Employee;

public interface EmployeeService {

	Employee saveEmp(Employee employee);

	public List<Employee> getAllEmployees();

	Optional<Employee> getEmpById(Long id);

	Employee updateEmp(Employee updatedEmp);
	
	void deleteEmpById(Long id);
	
}