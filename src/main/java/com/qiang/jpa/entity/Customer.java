package com.qiang.jpa.entity;

import lombok.Data;

import javax.persistence.*;

@Entity//声明实体类
@Table(name = "cst_customer")//配置数据库表的名字
@Data
public class Customer {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "cust_id")
    private Long custId;

    @Column(name = "cust_name")
    private String custName;

    @Column(name = "cust_source")
    private String custSource;

    @Column(name = "cust_level")
    private String custLevel;

    @Column(name = "cust_industry")
    private String custIndustry;

    @Column(name = "cust_phone")
    private String custPhone;

    @Column(name = "cust_address")
    private String custAddress;
}
