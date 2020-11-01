package com.qiang.jpa.dao;

import com.qiang.jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao extends JpaRepository<Role,Long> , JpaSpecificationExecutor<Role> {
}
