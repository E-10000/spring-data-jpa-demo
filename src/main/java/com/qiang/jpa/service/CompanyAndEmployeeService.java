package com.qiang.jpa.service;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiang.jpa.dao.CompanyDao;
import com.qiang.jpa.dao.EmployeeDao;
import com.qiang.jpa.entity.Company;
import com.qiang.jpa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class CompanyAndEmployeeService {
    @Autowired
    CompanyDao companyDao;
    @Autowired
    EmployeeDao employeeDao;

    //输入公司ID，显示该公司所有员工
    public Set<Employee> findEmployeeByCompany(Long id){
        Company company = companyDao.findById(id).get();
        Set<Employee> employees = company.getEmployees();
        return employees;
    }
}
