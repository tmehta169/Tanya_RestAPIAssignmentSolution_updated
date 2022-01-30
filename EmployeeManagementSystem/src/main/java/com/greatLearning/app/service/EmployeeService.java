package com.greatLearning.app.service;

import java.util.List;
import java.util.Optional;

import com.greatLearning.app.entity.Employee;
import com.greatLearning.app.entity.Role;
import com.greatLearning.app.entity.User;


public interface EmployeeService {

	public List<Employee> findAll();

	public String addEmployee(Employee employee);

	public String updateEmployee(Employee employee);

	public Optional<Employee> getEmployeeById(Integer id);

	public String deleteEmployeeById(Integer id);

	public List<Employee> getEmployeesByOrder(String sortBy);

	public List<Employee> getEmployeesByFirstName(String firstName);

	public User saveUser(User user);

	public Role saveRole(Role role);

}
