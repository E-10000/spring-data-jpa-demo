package com.qiang.jpa;

import com.qiang.jpa.dao.CompanyDao;
import com.qiang.jpa.dao.EmployeeDao;
import com.qiang.jpa.dao.RoleDao;
import com.qiang.jpa.dao.UserDao;
import com.qiang.jpa.entity.Company;
import com.qiang.jpa.entity.Employee;
import com.qiang.jpa.entity.Role;
import com.qiang.jpa.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class JpaApplicationTests2 {

    @Autowired
    UserDao userDao;

    @Autowired
    RoleDao roleDao;

    @Test
    void test1(){
        User user = new User();
        user.setUserName("小明");
        user.setUserAge(23);

        Role role = new Role();
        role.setRoleName("程序猿");

        user.getRoles().add(role);

        userDao.save(user);
        roleDao.save(role);

    }

    @Test
    void test2(){
        userDao.deleteById(11l);
    }
}
