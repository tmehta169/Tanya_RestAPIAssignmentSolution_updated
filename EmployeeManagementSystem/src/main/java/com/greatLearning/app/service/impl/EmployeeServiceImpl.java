package com.greatLearning.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greatLearning.app.dao.EmployeeRepository;
import com.greatLearning.app.dao.RoleRepository;
import com.greatLearning.app.dao.UserRepository;
import com.greatLearning.app.entity.Employee;
import com.greatLearning.app.entity.Role;
import com.greatLearning.app.entity.User;
import com.greatLearning.app.service.EmployeeService;


@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	
	
	@Override
	public String addEmployee(Employee employee) {
		if (employee.getFirstName().equals("") || employee.getLastName().equals("") || employee.getEmail().equals("")) {
			throw new RuntimeException("Error!!!All fields are mandatory ");

		} else {
			employeeRepository.saveAndFlush(employee);
			return "Employee Added Successfully!\nAdded Employee Details is :\nid : " + employee.getId()
					+ "\nFirst Name : " + employee.getFirstName() + "\nLast Name : " + employee.getLastName()
					+ "\nEmail : " + employee.getEmail();
		}
	}

	@Override
	public String updateEmployee(Employee employee) {
		boolean ifEmployeeExist = employeeRepository.existsById(employee.getId());

		if (ifEmployeeExist) {
			employeeRepository.saveAndFlush(employee);
			return "Employee Updated Successfully!\nUpdated Employee Details is :\nid : " + employee.getId()
					+ "\nFirst Name : " + employee.getFirstName() + "\nLast Name : " + employee.getLastName()
					+ "\nEmail : " + employee.getEmail();
		} else {
			throw new RuntimeException("There is no employee exist with id : " + employee.getId());

		}

	}

	@Override
	public Optional<Employee> getEmployeeById(Integer id) {
		boolean ifEmployeeExist = employeeRepository.existsById(id);

		if (ifEmployeeExist) {
			return employeeRepository.findById(id);

		} else
			throw new RuntimeException("There is no employee exist with id : " + id);

	}

	@Override
	public String deleteEmployeeById(Integer id) {
		boolean ifEmployeeExist = employeeRepository.existsById(id);

		if (ifEmployeeExist) {
			employeeRepository.deleteById(id);
			return "Deleted employee id -" + id;
		} else
			throw new RuntimeException("There is no employee exist with id : " + id);

	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		List<Employee> employees = employeeRepository.findAll();
		if (employees.size() > 0)
			return employees;
		else
			throw new RuntimeException("No employee data found!!!");
	}

	@Override
	public List<Employee> getEmployeesByOrder(String sortBy) {

		List<Employee> employees = employeeRepository.findAll(Sort.by(Direction.fromString(sortBy), "firstName"));
		if (employees.size() > 0)

			return employees;
		else
			throw new RuntimeException("No employee data found!!!");

	}

	@Override
	public List<Employee> getEmployeesByFirstName(String firstName) {
		// TODO Auto-generated method stub
		Employee empFirstName = new Employee();
		empFirstName.setFirstName(firstName); // finding employee having same first name
		ExampleMatcher exampleMatcher = ExampleMatcher.matching()
				.withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.ignoreCase()) // match exact chars
				.withIgnorePaths("id", "lastName", "email"); // leave other fields for matching
		Example<Employee> example = Example.of(empFirstName, exampleMatcher);
		List<Employee> matching_list = employeeRepository.findAll(example); // find all records having name matching

		if (matching_list.size() > 0)

			return matching_list; // find all records having name matching
		else
			throw new RuntimeException("There is no employee exist with firstName : " + firstName);

	}

}
