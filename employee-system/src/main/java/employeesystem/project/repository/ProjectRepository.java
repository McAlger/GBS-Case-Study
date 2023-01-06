package employeesystem.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import employeesystem.project.entity.Employee;


public interface ProjectRepository extends JpaRepository<Employee, Long> {
	
	@Query(value="select * from Employee e where e.firstname like %:keyword% or e.lastname like %:keyword%", nativeQuery=true)
	List<Employee> findByKeyword(@Param("keyword") String keyword);
	//Duplicate Query
	@Query(value ="SELECT * FROM employees e WHERE e.first_name LIKE CONCAT ('', :fname, '')"
	+ "AND e.middle_name LIKE CONCAT('', :mname, '')"
	+ "AND e.last_name LIKE CONCAT('', :lname, '')"
	+ "AND e.birth_date LIKE CONCAT('', :bdate, '') LIMIT 1", nativeQuery=true)
	public Long searchDuplicate(String fname, String lname, String mname, String bdate);
	
}
