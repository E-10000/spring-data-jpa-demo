package com.qiang.jpa.dao;

import com.qiang.jpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeDao extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {
}
