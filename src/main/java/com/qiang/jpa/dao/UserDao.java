package com.qiang.jpa.dao;

import com.qiang.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<User,Long> , JpaSpecificationExecutor<User> {
}
