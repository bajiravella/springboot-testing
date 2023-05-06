package com.mynee.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.mynee.model.Employee;

@DataJpaTest
class EmployeeRepositoryTests {

	@Autowired
	EmployeeRepository employeeRepository;

	private Employee employee;

	@BeforeEach
	void setUp() {
		employee = Employee.builder().name("Veera Narayana").phNumber("9642727290").build();

	}

	@Test
	void givenEmp_whenSaveEmp_thenReturnEmp() {
		Employee savedEmp = employeeRepository.save(employee);
		assertThat(savedEmp.getId()).isPositive();
		assertThat(savedEmp).isNotNull();
	}

	@Test
	void givenEmp_whenFindEmp_thenReturnEmp() {
		Employee savedEmp = employeeRepository.save(employee);
		Employee findEmpByPhNumber = employeeRepository.findByPhNumber(savedEmp.getPhNumber()).get();
		assertThat(findEmpByPhNumber.getPhNumber()).isEqualTo("9642727290");

	}

	@Test
	void givenEmp_whenUpdateEmp_thenReturnUpdatedEmp() {

		Employee savedEmp = employeeRepository.save(employee);

		savedEmp.setPhNumber("9703434021");

		assertThat(savedEmp.getPhNumber()).isEqualTo("9703434021");
	}

	@Test
	@DisplayName("Junit test case for delete operation")
	void givenEmp_whenDeleteEmp_thenReturnEmp() {

		Employee savedEmp = employeeRepository.save(employee);

		employeeRepository.deleteById(savedEmp.getId());

		Optional<Employee> empOptional = employeeRepository.findById(employee.getId());
		assertThat(empOptional.isEmpty()).isTrue();
	}

	@DisplayName("Jnuit test case for JPQL query")
	@Test
	void givenNameAndPhNumber_whenFindByJPQL_thenReturnEmpObj() {

		employeeRepository.save(employee);

		Employee findByJPQL = employeeRepository.findByJPQL(employee.getName(), employee.getPhNumber());
		assertThat(findByJPQL).isNotNull();
	}

	@DisplayName("Jnuit test case for named query")
	@Test
	 void givenNameAndPhNumber_whenFindByJPQLNamedParams_thenReturnEmpObj() {
		employeeRepository.save(employee);

		Employee findByJPQLNamedParams = employeeRepository.findByJPQLNamedParams(employee.getName(),
				employee.getPhNumber());
		assertThat(findByJPQLNamedParams).isNotNull();
	}

	@DisplayName("Junit test case for custom query using native sql with index...")
	@Test
	void givenNameAndPhNumber_whenFindByNativeSQL_thenReturnEmpObj() {

		employeeRepository.save(employee);

		Employee findEmpByNativeSQL = employeeRepository.findByNativeSQL(employee.getName(), employee.getPhNumber());

		assertThat(findEmpByNativeSQL).isNotNull();

	}

	@DisplayName("Junit test case for custom query using native sql and named param...")
	@Test
	void givenNameAndPhNumber_whenFindByNativeSQLAndParam_thenReturnEmpObj() {

		employeeRepository.save(employee);

		Employee findEmpByNativeSQLNamedParam = employeeRepository.findByNativeSQLNamedParam(employee.getName(),
				employee.getPhNumber());

		assertThat(findEmpByNativeSQLNamedParam).isNotNull();

	}

}