package com.uxpsystems.assignment.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Emp {    
private int id;    
private String name;    
private float salary;    
private String status;    
    
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
public int getId() {    
    return id;    
}    
public void setId(int id) {    
    this.id = id;    
}    
public String getName() {    
    return name;    
}    
public void setName(String name) {    
    this.name = name;    
}    
public float getSalary() {    
    return salary;    
}    
public void setSalary(float salary) {    
    this.salary = salary;    
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}    
   
    
}   