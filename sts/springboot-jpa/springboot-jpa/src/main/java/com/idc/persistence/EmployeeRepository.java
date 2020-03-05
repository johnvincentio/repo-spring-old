package com.idc.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.idc.persistence.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
}
