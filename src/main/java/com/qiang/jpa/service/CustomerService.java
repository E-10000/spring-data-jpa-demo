package com.qiang.jpa.service;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.qiang.jpa.dao.CustomerDao;
import com.qiang.jpa.entity.Company;
import com.qiang.jpa.entity.Customer;
import com.qiang.jpa.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;


    public List<Customer> findAll(){
        return customerDao.findAll();
    }

    public Customer findByI(Long id){
        return customerDao.findById(id).get();
    }

    public Customer findJpql(String cust_name){
        return customerDao.findJpql(cust_name);
    }

    public Customer findCustNameAndCustId(String name,Long id){
        return customerDao.findCustNameAndCustId(id,name);
    }

    public void updateJpql(String name,Long id){
        customerDao.updateJpql(name,id);
    }

    public List<Object[]> findSql(String name){
        return customerDao.findSql(name);
    }

    public Customer specFindByCustName(String name){

        Customer customer = customerDao.findOne(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取属性，返回一个我不知道的对象。。
                Path<Object> custName = root.get("custName");
                /*构造语句：  select * from cst_customer where cust_name = ?
                第一个参数：要被比较的参数
                第二个参数：想要拿去比较的值
                 */
                Predicate predicate = criteriaBuilder.equal(custName, name);
                return predicate;
            }
        }).get();
        return customer;
    }

    /*
    custName模糊查找与（and）custAddress精准查找，两个条件是与的关系
     */
    public Customer specFindByCustNameLikeAndCustAddress(String name,String address){
        Customer customer = customerDao.findOne(new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //两个属性
                Path<Object> custName = root.get("custName");
                Path<Object> custAddress = root.get("custAddress");

                //where custName like '%av%' and custAddress = '123'
                Predicate p1 = criteriaBuilder.like(custName.as(String.class), name);
                Predicate p2 = criteriaBuilder.equal(custAddress, address);

                //and连接起来，返回来也是一个对象
                return criteriaBuilder.and(p1, p2);//如果是 .or() 就是或
            }
        }).get();
        return customer;
    }

    //查询名字不为空的数据，并且降序排序
    public List<Customer> specSort(){
        //查询用户名字不为空的
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //查询用户名字不为空的
                Path custName = root.get("custName");
                Predicate notNull = criteriaBuilder.isNotNull(custName);
                return notNull;
            }
        };
        //desc降序排序
        Sort sort = Sort.by(Sort.Direction.DESC, "custId");
        //List<T> findAll(@Nullable Specification<T> var1, Sort var2);
        List<Customer> all = customerDao.findAll(specification, sort);
        return all;
    }

    public Page findAllPage(int index,int num){
        Specification specification = null;//暂时不写查的
        Sort sort = Sort.by(Sort.Direction.DESC,"custId");//ID降序
        //当前查询页数，每页查询数量，排序方式
        Pageable pageable = PageRequest.of(index, num,sort);
        //    Page<T> findAll(@Nullable Specification<T> var1, Pageable var2);
        Page page = customerDao.findAll(specification, pageable);
        //返回Page类，里面可以获得页数，数据集合，总条数等东西
        return page;
    }


}
