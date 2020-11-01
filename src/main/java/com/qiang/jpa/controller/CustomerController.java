package com.qiang.jpa.controller;

import com.qiang.jpa.dao.CustomerDao;
import com.qiang.jpa.service.CustomerService;
import com.qiang.jpa.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerDao customerDao;

    @GetMapping("/")
    public List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable("id") Long id){
        return customerService.findByI(id);
    }

    @GetMapping("/test")
    public Customer findJpql(){
        String custName = "php";
        return customerService.findJpql(custName);
    }

    @GetMapping("/test2")
    public Customer findCustNameAndCustId(){
        String name = "java";
        Long id = 1l;
        return customerService.findCustNameAndCustId(name,id);
    }

    @GetMapping("/test3")
    @Transactional//添加事务性，我也不懂，反正Jpql的添加就是要加这个注解
    public Customer updateJpql(){
        String name = "Java真好玩";
        Long id = 3l;
        customerService.updateJpql(name,id);
        return customerService.findByI(id);
    }

    @GetMapping("/test4")
    public List<Object[]> findSql(){
        String name = "%av%";
        return customerService.findSql(name);
    }

    @GetMapping("/test5")
    public Customer findByCustName(){
        return customerDao.findByCustName("java");
    }

    @GetMapping("/test6")
    public List<Customer> findByCustNameLike(){
        return customerDao.findByCustNameLike("%av%");
    }

    @GetMapping("/test7")
    public Customer findByCustNameLikeAndCustAddress(){
        return customerDao.findByCustNameLikeAndCustAddress("%av%","123");
    }

    @GetMapping("/test8")
    public Customer specFindByCustName(){
        return customerService.specFindByCustName("java");
    }

    @GetMapping("/test9")
    public Customer specFindByCustNameLikeAndCustAddress(){
        return customerService.specFindByCustNameLikeAndCustAddress("%av%","123");
    }

    @GetMapping("/test10")
    public List<Customer> specSort(){
        return customerService.specSort();
    }

    @GetMapping("/test11/{index}/{num}")
    public List<Customer> findAllPage(@PathVariable("index")int index,@PathVariable("num")int num){
        Page allPage = customerService.findAllPage(index, num);
        System.out.println("总页数："+allPage.getTotalPages());
        System.out.println("总条数："+allPage.getTotalElements());
        return allPage.getContent();
    }


}
