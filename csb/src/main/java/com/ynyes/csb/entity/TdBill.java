package com.ynyes.csb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 票据
 * @author Zhangji
 * 2016-1-5 9:48:25
 */

@Entity
public class TdBill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 排序
    @Column
    private Long  sortId;
    
    //用户名
    @Column
    private String username;
    
    //用户id
    @Column
    private Long userId;
    
    // 名称
    @Column
    private String title;
  
    //状态id（进度）【0】单个上传待确认【1】单个上传确认；【2】上传完成；【3】票据已整理；【4】财务处理；【5】税费扣缴；【6】财务状况表
    @Column
    private Long statusId;
    
    //图片
    @Column
    private String imgUrl;
    
    //上传时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;
    
    // 票据类型id
    @Column
    private Long billTypeId;
    

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getBillTypeId() {
		return billTypeId;
	}

	public void setBillTypeId(Long billTypeId) {
		this.billTypeId = billTypeId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	

	
 
}
  