package com.ynyes.csb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ynyes.csb.entity.TdUser;

/**
 * TdUser 实体数据库操作接口
 * 
 * @author Sharon
 *
 */

public interface TdUserRepo extends
		PagingAndSortingRepository<TdUser, Long>,
		JpaSpecificationExecutor<TdUser> 
{
    // 根据角色查找
    Page<TdUser> findByRoleIdOrderByIdDesc(Long roleId, Pageable page);
    
    Page<TdUser> findByUsernameContainingOrMobileContainingOrEmailContainingOrderByIdDesc(String keywords1, String keywords2, String keywords3, Pageable page);
    
    Page<TdUser> findByUsernameContainingAndRoleIdOrMobileContainingAndRoleIdOrEmailContainingAndRoleIdOrRealNameContainingAndRoleIdOrderByIdDesc(String keywords1, 
                                                                Long roleId1,
                                                                String keywords2,
                                                                Long roleId2,
                                                                String keyword3,
                                                                Long roleId3,
                                                                String keyword4,
                                                                Long roleId4, 
                                                                Pageable page);
    
    TdUser findByUsernameAndStatusIdOrUsernameAndStatusId(String username, Long statusId, String username1, Long statusId1);
    
    TdUser findByUsernameAndStatusIdOrUsernameAndStatusIdOrUsernameAndStatusIdIsNull(String username, Long statusId, String username1, Long statusId1 , String username2); //企业忘了加这个，空值也算吧。zhangji
    
    TdUser findByUsernameIgnoreCase(String username);
    
    TdUser findByUsernameAndIdNot(String username, Long id);
    
    
    TdUser findByMobileAndStatusIdOrMobileAndStatusId(String mobile,Long statusId,String mobile1,Long statusId1);		//手机号已验证查找
    
    TdUser findByMobile(String mobile);		//手机号查找
    
}
