package com.qiang.jpa.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.lang.ref.Reference;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {

    @Id//主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)//自增
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_age")
    private int userAge;

    /*
    @ManyToMany
        targetEntity = 对方表的类
    @JoinTable
        name = 中间表的名字（自己起）

        joinColumns 配置当前用户在中间表的外键
            @JoinColumn 数组
                name 外键名（自己起）
                referencedColumnName 参照主表中的主键名

        inverseJoinColumns 配置对方表在中间表的外键，
            @JoinColumn 数组
                name 外键名（自己起）
                referencedColumnName 参照主表中的主键名
     */

//    @ManyToMany(targetEntity = Role.class)
//    @JoinTable(name = "sys_user_role",
//        joinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")},
//        inverseJoinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")}
//    )
    //简化版
    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",joinColumns = @JoinColumn(name = "sys_user_id"),inverseJoinColumns = @JoinColumn(name = "sys_role_id"))
    private Set<Role> roles = new HashSet<>();
}
