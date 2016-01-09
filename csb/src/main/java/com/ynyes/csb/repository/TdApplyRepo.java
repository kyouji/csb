package com.ynyes.csb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.csb.entity.TdApply;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdApplyRepo extends
		PagingAndSortingRepository<TdApply, Long>,
		JpaSpecificationExecutor<TdApply> 
{ 
	List<TdApply> findByUserIdAndStatusIdAndApplyTypeId(Long userId, Long statusId, Long applyTypeId);
	
	Page<TdApply>findByApplyTypeIdOrderBySortIdAsc(Long applyTypeId, Pageable pageable);
	
	Page<TdApply>findByStatusIdOrderBySortIdAsc(Long statusId, Pageable pageable);
	
	Page<TdApply>findByApplyTypeIdAndStatusIdOrderBySortIdAsc(Long applyTypeId, Long statusId, Pageable pageable);
	
}