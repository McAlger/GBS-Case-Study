package employeesystem.project.controller;

import org.springframework.ui.Model;

import org.springframework.ui.ModelMap;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import employeesystem.project.entity.Employee;
import employeesystem.project.service.CompensationService;
import employeesystem.project.service.EmployeeService;

@Controller
public class EmployeeController {
  
	private EmployeeService employeeService;
	

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//handle list of employees to return and view
	@GetMapping("/employees")
	public String listEmployees(Model model) {
		model.addAttribute("employees", employeeService.getAllEmployees());
		return "employees";
	}
	
	@GetMapping("/employees/new")
	public String createEmployeeForm(Model model ) {
		
		//create employee object to hold employee form data
		Employee employee = new Employee(null, null, null, null, null);
		model.addAttribute("employee", employee);
		return "create_employee";
		
		
	}

	
	@GetMapping("/employees/edit/{id}")
	public String editEmployeeForm(@PathVariable Long id, Model model) {
		model.addAttribute("employee",employeeService.getEmployeeById(id));
		return "edit_employee";
	}
	
	@PostMapping("/employees/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employee, Model model) {
		
		//get employee from the database by using id
		Employee existingEmployee = employeeService.getEmployeeById(id);
		existingEmployee.setId(id);
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setMiddleName(employee.getMiddleName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setBirthDate(employee.getBirthDate());
		existingEmployee.setPosition(employee.getPosition());
		
		//saved update of the employee object
		employeeService.updateEmployee(existingEmployee);
		return "redirect:/employees";
	}
	
		
		  
		
	@GetMapping("/employees/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployeeById(id);
		return "/employees";

	}

	@RequestMapping("/employees/Search")
	public String viewEmployeeList(Model model, @Param("keyword") String keyword) {
		List<Employee> listEmployee = employeeService.listAll(keyword);
		model.addAttribute("employees", listEmployee);
		model.addAttribute("keyword", keyword);
		return "employees";
	}

	// duplicate
	@PostMapping("/employees")
	public String saveEmployee(@ModelAttribute("employee") Employee employee, RedirectAttributes redirAttrs) {
		Long check = duplicateEmployee(employee.getFirstName(), employee.getLastName(), employee.getMiddleName(),
				employee.getBirthDate());
		if (check > 0) {
			redirAttrs.addFlashAttribute("duplicate", " Employee already exists!");
			return "redirect:/employees/new";
		}

		else {
			employeeService.saveEmployee(employee);
			redirAttrs.addFlashAttribute("added", " Employee added successfully!");
			return "redirect:/employees";
		}
	}

	public Long duplicateEmployee(String firstName, String lastName, String middleName, String birthDate) {
		Long duplicate = employeeService.searchDuplicate(firstName, lastName, middleName, birthDate);
		if (duplicate == null) {
			duplicate = (long) 0;
		}
		return duplicate;
	}
}

               

