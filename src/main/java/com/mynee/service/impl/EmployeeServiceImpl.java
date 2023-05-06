package com.mynee.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mynee.exception.ResourceNotFoundException;
import com.mynee.model.Employee;
import com.mynee.repository.EmployeeRepository;
import com.mynee.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee saveEmp(Employee employee) {
		Optional<Employee> savedEmp = employeeRepository.findByPhNumber("9642727290");
		if (savedEmp.isPresent()) {
			throw new ResourceNotFoundException("Employee already registered....." + employee.getPhNumber());
		}
		return employeeRepository.save(employee);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		logger.info("All employee records are retrived");
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmpById(Long id) {
		if (id == 0) {
			throw new ResourceNotFoundException("Employee already registered....." + id);
		}
		return employeeRepository.findById(id);
	}

	@Override
	public Employee updateEmp(Employee updatedEmp) {
		logger.info("The updated employee name is {}", updatedEmp.getName());
		return employeeRepository.save(updatedEmp);
	}

	@Override
	public void deleteEmpById(Long id) {
		employeeRepository.deleteById(id);
	}
}