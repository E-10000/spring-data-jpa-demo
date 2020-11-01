package com.qiang.jpa.controller;

import com.qiang.jpa.entity.Company;
import com.qiang.jpa.entity.Employee;
import com.qiang.jpa.service.CompanyAndEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class CompanyAndEmployeeController {

    @Autowired
    CompanyAndEmployeeService companyAndEmployeeService;

    @GetMapping("test12/{id}")
    public Set<Employee> findEmployeeByCompany(@PathVariable("id") Long id){
        Set<Employee> employeeByCompany = companyAndEmployeeService.findEmployeeByCompany(id);

        return employeeByCompany;
    }

}
