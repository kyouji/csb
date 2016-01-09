package com.ynyes.csb.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.csb.entity.TdData;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdDataRepo extends
		PagingAndSortingRepository<TdData, Long>,
		JpaSpecificationExecutor<TdData> 
{ 
    List<TdData>findByIsEnableTrueOrderBySortIdAsc();
    
    List<TdData>findByUsernameAndDataTypeIdOrderBySortIdAsc(String username, Long dataTypeId); 

   

}