package com.ynyes.csb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.csb.entity.TdGather;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdGatherRepo extends
		PagingAndSortingRepository<TdGather, Long>,
		JpaSpecificationExecutor<TdGather> 
{ 
    Page<TdGather> findByTitleContainingOrderBySortIdAsc(String keywords, Pageable page);
    
    List<TdGather> findByUsername(String username, Sort sort);

}