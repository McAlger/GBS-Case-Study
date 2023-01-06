package employeesystem.project.service;

import java.util.List;

import employeesystem.project.entity.Employee;

public interface EmployeeService {
   List<Employee> getAllEmployees();
   
   Employee saveEmployee(Employee employee);
   
   Employee getEmployeeById(Long id);
   Employee updateEmployee(Employee employee);
   
   void deleteEmployeeById(Long id);
   
   public boolean validationExistingEmp(Employee employee) ;
   Long searchDuplicate(String fname, String lname, String mname, String bdate);

    // get employees by keyword
 public default List<Employee> findByKeyword(String keyword){
   return projectRepositoryfindByKeyword(keyword);
   
}

List<Employee> projectRepositoryfindByKeyword(String keyword);

List<Employee> listAll(String keyword);

}
