package employeesystem.project.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import employeesystem.project.entity.Compensation;

public interface CompensationRepository extends JpaRepository<Compensation, Long> {
	

@Query(value="SELECT compensation_id, SUM(amount) AS amount, comp_description, compensation_date, compensation_type, employee_id "
		+ "FROM compensation "
		+ "WHERE employee_id = ?1 GROUP BY MONTH(compensation_date) , YEAR(compensation_date)", nativeQuery=true)
public List<Compensation> getCompensationHistory(Long employee_id);

@Query(value="SELECT * FROM compensation WHERE compensation_date LIKE CONCAT(YEAR(?1), '-', MONTH(?1), '-%') ORDER BY compensation_date DESC", nativeQuery=true)
public List<Compensation> getCompensationBreakdown(Date compDate);

@Query(value="SELECT compensation_id FROM compensation "
		+ "WHERE compensation_date LIKE CONCAT(YEAR(?1), '-', MONTH(?1), '-%') AND employee_id = ?2 AND compensation_type = 'Salary'", nativeQuery=true)
public Long checkEmployeeSalary(Date compDate, Long employeeId);

}