package com.qiang.jpa;

import com.qiang.jpa.dao.CompanyDao;
import com.qiang.jpa.dao.CustomerDao;
import com.qiang.jpa.dao.EmployeeDao;
import com.qiang.jpa.entity.Company;
import com.qiang.jpa.entity.Employee;
import com.qiang.jpa.entity.Role;
import com.qiang.jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Soundbank;
import java.util.Set;

@SpringBootTest
class JpaApplicationTests {

    @Autowired
    CompanyDao companyDao;

    @Autowired
    EmployeeDao employeeDao;

    @Test
    void contextLoads() {
        Company company = new Company();
        company.setCompanyName("小强公司");
        company.setCompanyNum(444l);

        Employee employee = new Employee();
        employee.setEmployeeJob("程序猿");
        employee.setCompany(company);//添加外键的值

        companyDao.save(company);
        employeeDao.save(employee);
    }

    @Test
    void test(){
        //删除3号公司，因为使用了级连查询，所以3号公司的员工都会被删除
        Company company = companyDao.findById(3l).get();
        companyDao.delete(company);
    }

    @Test
    @Transactional
    @Rollback(value = false)//是否回滚事务
    void test1(){
        Company company = companyDao.findById(4l).get();

        Set<Employee> employees = company.getEmployees();

//        System.out.println(company);

        for (Employee employee: employees) {
            System.out.println(employee);
        }
    }

}
