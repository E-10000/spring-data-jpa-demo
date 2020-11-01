package com.qiang.jpa.dao;

import com.qiang.jpa.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CompanyDao extends JpaRepository<Company,Long>, JpaSpecificationExecutor<Company> {
}
