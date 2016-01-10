package com.ynyes.csb.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;


/**
 *缴费支付
 * @author Zhangji
 */

@Entity
public class TdPay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 用户名
    @Column
    private Long  userId;
    
    // 排序
    @Column
    private Long  sortId;
    
    //时间（月份）
    @Column
    @DateTimeFormat(pattern="yyyy-MM")
    private Date time;
    
    // 代理费备注
    @Column
    private String contentOne;
    
    // 其他备注
    @Column
    private String contentTwo;
    
    // 备注
    @Column
    private String remark;
    
    // 会计代理费
    @Column
    private Double price;
    
    // 代理费数量
    @Column
    private Double amount;
    
    //代理费总价
    @Column
    private Double allPrice;
    
    //其他费用
    @Column
    private Double otherPrice;
    
    //总价
    @Column
    private Double totalPrice;
    
    //是否支付
    @Column
    private Boolean isPaid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getContentOne() {
		return contentOne;
	}

	public void setContentOne(String contentOne) {
		this.contentOne = contentOne;
	}

	public String getContentTwo() {
		return contentTwo;
	}

	public void setContentTwo(String contentTwo) {
		this.contentTwo = contentTwo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(Double allPrice) {
		this.allPrice = allPrice;
	}

	public Double getOtherPrice() {
		return otherPrice;
	}

	public void setOtherPrice(Double otherPrice) {
		this.otherPrice = otherPrice;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

}
  