package com.uxpsystems.assignment.service;

import java.util.List;

import com.uxpsystems.assignment.model.Emp;
import com.uxpsystems.assignment.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

	List<Emp> getEmployees();
	
	void saveEmployee(Emp emp);
	
	Emp getEmpById(int id);
	
	void updateEmployee(Emp emp);
	
	void deleteEmployee(int id);
}