package com.ynyes.csb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.csb.entity.TdBill;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdBillRepo extends
		PagingAndSortingRepository<TdBill, Long>,
		JpaSpecificationExecutor<TdBill> 
{ 
	
	List<TdBill> findByUsername(String username, Sort sort);
    Page<TdBill> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    List<TdBill>findByStatusIdAndUsername(Long statusId, String username, Sort sort);

   

}