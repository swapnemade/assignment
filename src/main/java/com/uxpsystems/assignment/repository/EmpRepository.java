package com.uxpsystems.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uxpsystems.assignment.model.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer>{
}