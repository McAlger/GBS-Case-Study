package employeesystem.project.controller;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import employeesystem.project.entity.Compensation;
import employeesystem.project.service.CompensationService;
import employeesystem.project.service.EmployeeService;

public class CompensationController {

	
	private CompensationService compensationService;
	private EmployeeService employeeService;
	
	public CompensationController(CompensationService compensationService, EmployeeService employeeService) {
		super();
		this.compensationService = compensationService;
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees/compensation/{id}")
	public String listcompensations(@PathVariable("id") Long id, Model model) {
		
		model.addAttribute("employee", employeeService.getEmployeeById(id));
		model.addAttribute("compensation", compensationService.getCompensationHistory(id));

		return "compensation_history";
		
	}
	
	@PostMapping("/employees/compensation/search")
	public String searchCompensation(Model model, Date startDate, Date endDate) {
		//Not yet done
		return null;
	}
	
	@GetMapping("/employees/compensation/new")
	public String createEmployeeCompensationForm(Model model) {
		Compensation compensation = new Compensation();
		
		model.addAttribute("employeeList", employeeService.getAllEmployees());
		model.addAttribute("compensation", compensation);
		
		List<String> typeList = Arrays.asList("Salary", "Bonus", "Commission", "Allowance", "Adjustment");
		model.addAttribute("typeList", typeList);
		
		return "create_compensation";
	}
	
	@PostMapping("/employees/compensation/save")
	public String saveEmployeeCompensation(@ModelAttribute("compensation") Compensation compensation, RedirectAttributes redirAttrs) {
		Long check = checkEmployeeSalary(compensation.getCompDate(), compensation.getEmployeeId());
		if(check > 0) {
			redirAttrs.addFlashAttribute("duplicate", "Employee salary already exists for this month...");
			return "redirect:/employees/compensation/new";
		}else {
			compensationService.saveCompensation(compensation);
			
			redirAttrs.addFlashAttribute("added", "New compensation has been added successfully...");
			
			return "redirect:/employees";
		}
	}
	
	@GetMapping("/employees/compensation/breakdown/{employeeId}/{compDate}")
	public String getCompensationBreakdown(@PathVariable("employeeId") Long employeeId, @PathVariable("compDate") Date compDate, Model model) {
		model.addAttribute("date", compDate);
		model.addAttribute("employee", employeeId);
		model.addAttribute("compensations", compensationService.getCompensationBreakdown(compDate));
		return "compensation";
	}
	
	@GetMapping("/employees/compensation/edit/{compensationId}")
	public String editCompensationForm( @PathVariable("compensationId") Long compId, Model model) {
		model.addAttribute("compensation", compensationService.getCompensationById(compId));
		return "edit_compensation";
	}
	
	public Long checkEmployeeSalary(Date compDate, Long employeeId) {
		Long salary = compensationService.checkEmployeeSalary(compDate, employeeId);
		if(salary == null) {
			salary = (long) 0;
		}
		return salary;
	}
}

