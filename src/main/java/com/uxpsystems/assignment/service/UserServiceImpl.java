package com.uxpsystems.assignment.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uxpsystems.assignment.model.Emp;
import com.uxpsystems.assignment.model.User;
import com.uxpsystems.assignment.repository.EmpRepository;
import com.uxpsystems.assignment.repository.RoleRepository;
import com.uxpsystems.assignment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private EmpRepository empRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    @Override
    public List<Emp> getEmployees() {
    	return empRepository.findAll();
    }
    
    public void saveEmployee(Emp emp) {
    	empRepository.save(emp);
    }
    
    public Emp getEmpById(int id) {
    	return empRepository.findOne(id);
    }
    
    public void updateEmployee(Emp emp){
    	empRepository.save(emp);
    }
    
    public void deleteEmployee(int id) {
    	empRepository.delete(id);
    }
}