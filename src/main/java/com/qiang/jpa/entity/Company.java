package com.qiang.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
@Getter
@Setter
@RequiredArgsConstructor
public class Company {


    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_num")
    private Long companyNum;

    /*
    虽然说主表不用写外键这个东西，但是说写了这个可以双向选择什么的，我也不懂
    @OneToMany：配置1对多，用于主表类中，但是这个数据库不会显示这个列
        targetEntity：对方对象的字节码对象
    @JoinColumn：配置外键（主表也可以配置这个，这样双方都能获取对方的值）
        name：从表外键（即使类中没有这个属性）
        referencedColumnName：主表主键
     */
//    @OneToMany(targetEntity = Employee.class)
//    @JoinColumn(name = "employee_company_id",referencedColumnName = "company_id")
    //或者写以下的方式
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,fetch = FetchType.LAZY)//mappedBy参数写从表中外键的属性名
    @JsonIgnoreProperties(value = {"company"})
    private Set<Employee> employees = new HashSet<>();

}
