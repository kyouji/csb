package com.ynyes.csb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.csb.entity.TdFinance;
import com.ynyes.csb.repository.TdFinanceRepo;

/**
 * TdMallService 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdFinanceService {
    @Autowired
    TdFinanceRepo repository;
    
    
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
    public void delete(TdFinance e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdFinance> entities)
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
    public TdFinance findOne(Long id)
    {
        if (null == id)
        {
            return null;
        }
        
        return repository.findOne(id);
    }
    
    /**
     * 查找
     * 
     * @param ids
     * @return
     */
    public List<TdFinance> findAll(Iterable<Long> ids)
    {
        return (List<TdFinance>) repository.findAll(ids);
    }
    
    public List<TdFinance> findAllOrderBySortIdAsc()
    {
        Sort sort = new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "time"));
        
        return (List<TdFinance>) repository.findAll(sort);
    }
    
    /**
     * 根据username查找所有【票据整理】信息
     * @return
     */
    public List<TdFinance> findByUsername(String username)
    {
        Sort sort = new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "time"));
        
        return (List<TdFinance>) repository.findByUsername(username, sort);
    }
    
    
    public Page<TdFinance> findAllOrderBySortIdAsc(int page, int size)
    {
        PageRequest pageRequest = new PageRequest(page, size, new Sort(Direction.ASC, "sortId"));
        
        return repository.findAll(pageRequest);
    }
    


    
    /**
     * 保存
     * 
     * @param e
     * @return
     */
    public TdFinance save(TdFinance e)
    {
        
        return repository.save(e);
    }
    
    public List<TdFinance> save(List<TdFinance> entities)
    {
        
        return (List<TdFinance>) repository.save(entities);
    }
    
    public List<TdFinance> findAll(){
    	return (List<TdFinance>) repository.findAll();
    }

}
