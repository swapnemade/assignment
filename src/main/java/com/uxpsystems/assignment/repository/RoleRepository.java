package com.uxpsystems.assignment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.uxpsystems.assignment.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
}