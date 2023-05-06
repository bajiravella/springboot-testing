package com.mynee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mynee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByPhNumber(String phNumber);

	// define custom query using JPQL with index parameters....
	@Query("select e from Employee e where e.name =?1 and e.phNumber=?2")
	Employee findByJPQL(String name, String phNumber);

	// named parameters
	@Query("select e from Employee e where e.name =:name and e.phNumber=:phNumber")
	Employee findByJPQLNamedParams(@Param("name") String name, @Param("phNumber") String phNumber);

	@Query(value = "select * from EMP_TBL e where e.Name=?1 and e.Ph_Number=?2", nativeQuery = true)
	Employee findByNativeSQL(String name, String phNumber);

	// custom query using Native SQL with named params
	@Query(value = "select * from EMP_TBL e where e.Name=:name and e.Ph_Number=:phNumber", nativeQuery = true)
	Employee findByNativeSQLNamedParam(@Param("name") String name, @Param("phNumber") String phNumber);

}
