package com.ynyes.csb.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ynyes.csb.entity.TdPay;
import com.ynyes.csb.repository.TdPayRepo;

/**
 * TdMallService 服务类
 * 
 * @author Sharon
 *
 */

@Service
@Transactional
public class TdPayService {
    @Autowired
    TdPayRepo repository;
    
    @Autowired
    TdStockService tdStockService;
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
    public void delete(TdPay e)
    {
        if (null != e)
        {
            repository.delete(e);
        }
    }
    
    public void delete(List<TdPay> entities)
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
    public TdPay findOne(Long id)
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
    public List<TdPay> findAll(Iterable<Long> ids)
    {
        return (List<TdPay>) repository.findAll(ids);
    }
    
    public List<TdPay> findAllOrderBySortIdAsc()
    {
        Sort sort = new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "time"));
        
        return (List<TdPay>) repository.findAll(sort);
    }
    
    /**
     * 根据username查找所有【票据整理】信息
     * @return
     */
    public List<TdPay> findByUserId(Long userId)
    {
        Sort sort = new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "time"));
        
        return (List<TdPay>) repository.findByUserId(userId, sort);
    }
    
    //找出未缴费的
    public List<TdPay> findByUserIdAndIsPaidFalse(Long userId)
    {
        Sort sort = new Sort(Direction.ASC, "sortId").and(new Sort(Direction.DESC, "time"));
        
        return (List<TdPay>) repository.findByUserIdAndIsPaidFalse(userId, sort);
    }
    
    //根据时间查找？
    public List<TdPay> findByTime(Date time)
    {
        return repository.findByTime(time);
    }
    
    public TdPay findByUserIdAndTime(Long userId, Date time)
    {
        
        return repository.findByUserIdAndTime(userId, time);
    }
    
    
    public Page<TdPay> findAllOrderBySortIdAsc(int page, int size)
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
    public TdPay save(TdPay e)
    {
        return repository.save(e);
    }
    
    public List<TdPay> save(List<TdPay> entities)
    {
        
        return (List<TdPay>) repository.save(entities);
    }
    
    public List<TdPay> findAll(){
    	return (List<TdPay>) repository.findAll();
    }

}
