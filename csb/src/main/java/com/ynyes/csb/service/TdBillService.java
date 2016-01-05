package com.ynyes.csb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.csb.entity.TdBill;
import com.ynyes.csb.repository.TdBillRepo;

/**
 * TdMallService 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdBillService {
    @Autowired
    TdBillRepo repository;
    
    Sort sort = new Sort(Direction.ASC, "sortId");
    
    /**
     * 删除
     * 
     * @param id 菜单项ID
     */
    public void delete(Long id)
    {
        if (null != id)
        {
            repository.delete(id);
        }
    }
    
    /**
     * 删除
     * 
     * @param e 菜单项
     */
    public void delete(TdBill e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdBill> entities)
    {
        if (null != entities)
        {
            repository.delete(entities);
        }
    }
    
    /**
     * 查找
     * 
     * @param id ID
     * @return
     */
    public TdBill findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 根据后台排序查找
     * @author Zhangji
     */
    public List<TdBill> findByUsername(String username)
    {
    	Sort sort = new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "time"));
        return repository.findByUsername( username, sort);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdBill> findAll(Iterable<Long> ids)
    {
        return (List<TdBill>) repository.findAll(ids);
    }
    
    public List<TdBill> findAllOrderBySortIdAsc()
    {
        Sort sort = new Sort(Direction.ASC, "sortId");
        
        return (List<TdBill>) repository.findAll(sort);
    }
    
    public List<TdBill> findByStatusIdAndUsername(Long statusId , String username)
    {
        return repository.findByStatusIdAndUsername(statusId, username, sort);
    }
    
    
    public Page<TdBill> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    
    public Page<TdBill> searchAllOrderBySortIdAsc(String keywords, int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size);
        
        return repository.findByTitleContainingOrderBySortIdAsc(keywords, pageRequest);
    }
    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdBill save(TdBill e)
    {
        
        return repository.save(e);
    }
    
    public List<TdBill> save(List<TdBill> entities)
    {
        
        return (List<TdBill>) repository.save(entities);
    }
    
    public List<TdBill> findAll(){
    	return (List<TdBill>) repository.findAll();
    }
}
