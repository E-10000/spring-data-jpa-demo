package com.qiang.jpa.dao;

import com.qiang.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//JpaRepository中第一个泛型写实体类，第二个写实体类主键，用于简单的CRUD代码
//JpaSpecificationExecutor泛型写实体类对象，封装了复杂查询
public interface CustomerDao extends JpaRepository<Customer,Long> , JpaSpecificationExecutor<Customer> {


    @Query(value = "from Customer where custName = ?1")
    public Customer findJpql(String custName);

    @Query(value = "from Customer where custName = ?2 and custId = ?1")
    public Customer findCustNameAndCustId(Long id,String name);

    @Query(value = "update Customer set custName = ?1 where custId = ?2")
    @Modifying
    public void updateJpql(String name,Long id);

    @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery=true)
    public List<Object[]> findSql(String name);

    public Customer findByCustName(String name);

    public List<Customer> findByCustNameLike(String name);

    public Customer findByCustNameLikeAndCustAddress(String custName,String custAddress);
}
