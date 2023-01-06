package employeesystem.project.service.implement;

import java.util.List;
import org.springframework.stereotype.Service;
import employeesystem.project.entity.Employee;
import employeesystem.project.repository.ProjectRepository;
//import employeesystem.project.entity.Service;
import employeesystem.project.service.EmployeeService;


@Service

public class EmployeeServiceImplement implements EmployeeService {
	private ProjectRepository projectRepository;
	
	
	
	public EmployeeServiceImplement(ProjectRepository projectRepository) {
		super();
		this.projectRepository = projectRepository;
	}



	@Override
	public List<Employee> getAllEmployees(){
		
		return projectRepository.findAll();
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return projectRepository.save(employee);
	}



	@Override
	public Employee getEmployeeById(Long id) {
		// TODO Auto-generated method stub
		return projectRepository.findById(id).get();
	}



	@Override
	public Employee updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return projectRepository.save(employee);
	}



	@Override
	public void deleteEmployeeById(Long id) {
		// TODO Auto-generated method stub
		 projectRepository.deleteById(id);
		
	}



	@Override
	public List<Employee> projectRepositoryfindByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<Employee> listAll(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean validationExistingEmp(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Long searchDuplicate(String fname, String lname, String mname, String bdate) {
		// TODO Auto-generated method stub
		return projectRepository.searchDuplicate(fname, lname, mname, bdate);
		
	}



	
	



}
