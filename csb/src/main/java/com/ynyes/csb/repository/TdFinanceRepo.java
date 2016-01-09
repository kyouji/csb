package com.ynyes.csb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.csb.entity.TdFinance;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdFinanceRepo extends
		PagingAndSortingRepository<TdFinance, Long>,
		JpaSpecificationExecutor<TdFinance> 
{ 
     
    List<TdFinance> findByUsername(String username, Sort sort); 

}