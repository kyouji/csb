package com.ynyes.csb.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.csb.entity.TdPay;

/**
 * TdEnterprise 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdPayRepo extends
		PagingAndSortingRepository<TdPay, Long>,
		JpaSpecificationExecutor<TdPay> 
{ 
	 TdPay findByUserIdAndTime(Long userId, Date time);
	 
    List<TdPay> findByUserId(Long userId, Sort sort);
    List<TdPay> findByUserIdAndIsPaidFalse(Long userId, Sort sort);
    List<TdPay> findByTime(Date time);
}