package com.mynee.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mynee.exception.ResourceNotFoundException;
import com.mynee.model.Employee;
import com.mynee.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;
	@Mock
	private EmployeeRepository employeeRepository;

	Employee employee;

	@BeforeEach
	public void setUp() {
		employee = Employee.builder().id(1006L).name("Veera Mynee").phNumber("9642727290").build();
	}

	@DisplayName("Junit test to save employee")
	@Test
	void givenEmp_whenEmpSave_thenReturnEmp() {

		// stubbing
		given(employeeRepository.findByPhNumber(employee.getPhNumber())).willReturn(Optional.empty());
		given(employeeRepository.save(employee)).willReturn(employee);

		// when- action or behaviour that we are going test
		Employee saveEmp = employeeServiceImpl.saveEmp(employee);

		// then - verify the o/p
		assertThat(saveEmp).isNotNull();

	}

	@DisplayName("Junit test to save employee -ve scenario.....which throws Exception")
	@Test
	void givenExistingEmp_whenEmpSave_thenThrowsException() {

		// stubbing
		given(employeeRepository.findByPhNumber(employee.getPhNumber())).willReturn(Optional.of(employee));

		// verify
		assertThrows(ResourceNotFoundException.class, () -> employeeServiceImpl.saveEmp(employee));

	}

	@Test
	@DisplayName("Junit test to retrive Employee list....")
	void givenEmpList_whenFindAllEmpList_thenReturnListEmp() {

		Employee employee2 = Employee.builder().id(6174L).name("Ravella Baji").phNumber("9703434021").build();
		given(employeeRepository.findAll()).willReturn(List.of(employee, employee2));
		List<Employee> allEmployees = employeeServiceImpl.getAllEmployees();
		Assertions.assertThat(allEmployees).isNotNull();
		Assertions.assertThat(allEmployees.size()).isEqualTo(2);

	}

	@Test
	@DisplayName("Junit test to retrive Employee list -ve scenario....")
	void givenEmptyEmpList_whenFindAllEmpList_thenReturnEmptyListEmp() {

		given(employeeRepository.findAll()).willReturn(Collections.emptyList());
		List<Employee> allEmployees = employeeServiceImpl.getAllEmployees();

		assertThat(allEmployees).isEmpty();
		assertThat(allEmployees.size()).isZero();

	}

	@Test
	@DisplayName("Junit test to find Emp by id")
	void givenEmpId_whenFindEmp_thenRetrunEmp() {

		given(employeeRepository.findById(employee.getId())).willReturn(Optional.of(employee));
		Employee empById = employeeServiceImpl.getEmpById(employee.getId()).get();
		assertThat(empById).isNotNull();
		assertThat(empById.getId()).isEqualTo(employee.getId());

	}

	@Test
	@DisplayName("Junit test case for update employee")
	void givenEmpObj_whenUpdatedEmp_thenReturnEmp() {

		given(employeeRepository.save(employee)).willReturn(employee);

		employee.setName("SHAH RUKH KHAN");
		employee.setPhNumber("9000459126");

		Employee updateEmp = employeeServiceImpl.updateEmp(employee);

		assertThat(updateEmp.getName()).isEqualTo("SHAH RUKH KHAN");
		assertThat(updateEmp.getPhNumber()).isEqualTo("9000459126");

	}

	@Test
	void deleteById() {
		Long empId = 6174L;
		willDoNothing().given(employeeRepository).deleteById(empId);
		employeeServiceImpl.deleteEmpById(empId);

		verify(employeeRepository, times(1)).deleteById(empId);
	}

}