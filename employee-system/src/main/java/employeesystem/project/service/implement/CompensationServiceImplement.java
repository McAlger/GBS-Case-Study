package employeesystem.project.service.implement;

import java.sql.Date;
import java.util.List;

import employeesystem.project.entity.Compensation;
import employeesystem.project.entity.Service;
import employeesystem.project.repository.CompensationRepository;
import employeesystem.project.service.CompensationService;

@Service
public class CompensationServiceImplement implements CompensationService {

private CompensationRepository compensationRepository;
	
	public CompensationServiceImplement(CompensationRepository compensationRepository) {
 super();
 this.compensationRepository = compensationRepository;
	}
	@Override
	public List<Compensation> getCompensationHistory(Long employee_id) {
 return compensationRepository.getCompensationHistory(employee_id);
	}
	@Override
	public Compensation saveCompensation(Compensation compensation) {
 return compensationRepository.save(compensation);
	}
	@Override
	public Compensation getCompensationById(Long compId) {
 return compensationRepository.findById(compId).get();
	}
	@Override
	public List<Compensation> getCompensationBreakdown(Date compDate) {
 return compensationRepository.getCompensationBreakdown(compDate);
	}
	@Override
	public Long checkEmployeeSalary(Date compDate, Long employeeId) {
 return compensationRepository.checkEmployeeSalary(compDate, employeeId);
	}

}
