package com.greatLearning.app.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.greatLearning.app.entity.Employee;
import com.greatLearning.app.entity.Role;
import com.greatLearning.app.entity.User;
import com.greatLearning.app.service.EmployeeService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api")
@Log4j2
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	

	/*
	 * Accessible to ADMIN only 
	 * http://localhost:8080/api/user
	 */
	@PostMapping("/user")
	public User saveUser(@RequestBody User user) {
		return employeeService.saveUser(user);
	}

	/*
	 * Accessible to ADMIN only 
	 * http://localhost:8080/api/role
	 */
	@PostMapping("/role")
	public Role saveRole(@RequestBody Role role) {
		return employeeService.saveRole(role);
	}

	/*
	 * Accessible to ADMIN & USER 
	 * http://localhost:8080/api/employees
	 */
	@GetMapping("/employees")
	public List<Employee> findAll() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> currentPrincipalName = authentication.getAuthorities();
		System.out.println(currentPrincipalName);
		return employeeService.findAll();
	}

	/*
	 * Accessible to ADMIN only 
	 * http://localhost:8080/api/employees 
	 * body-> raw(json)
	 */
	@PostMapping("/employees")
	public String addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);

	}

	/*
	 * Accessible to ADMIN only 
	 * http://localhost:8080/api/employees
	 * body-> raw(json)
	 */
	@PutMapping("/employees")
	public String UpdateEmployee(@RequestBody Employee employee) {
		return employeeService.updateEmployee(employee);
	}

	/*
	 * Accessible to ADMIN & USER
	 * http://localhost:8080/api/employees/getEmployeeById?employee_id=1
	 */
	@GetMapping("/employees/getEmployeeById")
	public Optional<Employee> getEmployeeById(@RequestParam("employee_id") int theId) {
		return employeeService.getEmployeeById(theId);
	}

	/*
	 * Accessible to ADMIN only
	 * http://localhost:8080/api/employees/{employee_id}1
	 */
	@DeleteMapping("/employees/{employee_id}")
	public String deleteEmployeeById(@PathVariable int employee_id) {
		return employeeService.deleteEmployeeById(employee_id);
	}

	/*
	 * Accessible to ADMIN & USER
	 * http://localhost:8080/api/employees/sort?order=desc
	 * http://localhost:8080/api/employees/sort?order=asc
	 */
	@GetMapping("/employees/sort")
	public List<Employee> getEmployeesByOrder(@RequestParam("order") String sortBy) {

		return employeeService.getEmployeesByOrder(sortBy);

	}

	/*
	 * Accessible to ADMIN & USER
	 * http://localhost:8080/api/employees/search/ambika
	 */
	@GetMapping("/employees/search/{firstName}")
	@ResponseBody
	public List<Employee> getEmployeesByFirstName(@PathVariable String firstName) {

		return employeeService.getEmployeesByFirstName(firstName);

	}

	/*
	 * Accessible to ADMIN only 
	 * http://localhost:8080/api/listHeaders
	 */
	@GetMapping("/listHeaders")
	public String listAllHeaders(@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			log.info(String.format("Header '%s' = %s", key, value));
		});

		return String.format("Open Log to View - Listed %d headers", headers.size());
	}
}
