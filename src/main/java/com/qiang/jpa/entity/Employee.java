package com.qiang.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Getter
@Setter
@RequiredArgsConstructor
public class Employee {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "employee_name")
    private String employeeName;

    @Column(name = "employee_job")
    private String employeeJob;

    /*
        @ManyToOne：配置多对1，用于从表类中外键属性前
            targetEntity：对方对象的字节码对象
        @JoinColumn：配置外键（主表也可以配置这个，这样双方都能获取对方的值）
            name：从表外键（即使类中没有这个属性）
            referencedColumnName：主表主键
         */
    @ManyToOne(targetEntity = Company.class,fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_company_id",referencedColumnName = "company_id")
    @JsonIgnoreProperties(value = {"employees"})
    private Company company;
}
