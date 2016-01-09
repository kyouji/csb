package com.ynyes.csb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 财务状况
 * @author Zhangji
 */

@Entity
public class TdFinance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 用户名
    @Column
    private String  username;
    
    // 排序
    @Column
    private Long  sortId;
    
    //账面存货Id
    @Column
    private Long stockId;
    
    // 状态
    @Column
    private Boolean isEnable;
    
    //时间（月份）
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;
    
    //附件
    @Column
    private String fileUrl;
    
    // 补充说明
    @Column
    private String remark;
    
    // 本月不含税收入
    @Column
    private Double noTax;
    
    //累计收入
    @Column
    private Double totalIncome;
    
    //本月利润
    @Column
    private Double profit;
    
    //累计利润
    @Column
    private Double totalProfit;
    
    //累计毛利率
    @Column
    private Double totalGross;
    
    //本年累计上缴增值税
    @Column
    private Double totalDeduction;
    
    //本年累计上缴所得税
    @Column
    private Double totalIncomeTax;
    
    /*---------------------------------------------------------
    ============== 一般纳税人==============
    ---------------------------------------------------------*/
    
    //本月留抵税金
    @Column
    private Double taxRetention;
    
    //折合为价税合计
    @Column
    private Double valorem;
    
    //税负
    @Column
    private Double taxBearing;
    
    //本月待抵扣税金
    @Column
    private Double todo;
    
    //折合为价税合计
    @Column
    private Double todoValorem;
    
    //未收抵扣联，数量
    @Column
    private Double todoAmount;
    
    //未收抵扣联，最早日期
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Double todoEarlyDate;
    
    //已收抵扣联，数量
    @Column
    private Double doneAmount;
    
    //已收抵扣联，最早日期
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Double doneEarlyDate;
    
    
    
    /*---------------------------------------------------------
    ============= 小规模纳税人==============
    ---------------------------------------------------------*/
    
    //最高可开票金额
    @Column
    private Double maxTicket;
    
    //提示小消息
    @Column
    private String tip;
    


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

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public Double getNoTax() {
		return noTax;
	}

	public void setNoTax(Double noTax) {
		this.noTax = noTax;
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		this.totalIncome = totalIncome;
	}

	public Double getProfit() {
		return profit;
	}

	public void setProfit(Double profit) {
		this.profit = profit;
	}

	public Double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(Double totalProfit) {
		this.totalProfit = totalProfit;
	}

	public Double getTotalGross() {
		return totalGross;
	}

	public void setTotalGross(Double totalGross) {
		this.totalGross = totalGross;
	}

	public Double getTaxRetention() {
		return taxRetention;
	}

	public void setTaxRetention(Double taxRetention) {
		this.taxRetention = taxRetention;
	}

	public Double getValorem() {
		return valorem;
	}

	public void setValorem(Double valorem) {
		this.valorem = valorem;
	}

	public Double getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(Double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public Double getTotalIncomeTax() {
		return totalIncomeTax;
	}

	public void setTotalIncomeTax(Double totalIncomeTax) {
		this.totalIncomeTax = totalIncomeTax;
	}

	public Double getTodo() {
		return todo;
	}

	public void setTodo(Double todo) {
		this.todo = todo;
	}

	public Double getTodoValorem() {
		return todoValorem;
	}

	public void setTodoValorem(Double todoValorem) {
		this.todoValorem = todoValorem;
	}

	public Double getDoneAmount() {
		return doneAmount;
	}

	public void setDoneAmount(Double doneAmount) {
		this.doneAmount = doneAmount;
	}

	public Double getDoneEarlyDate() {
		return doneEarlyDate;
	}

	public void setDoneEarlyDate(Double doneEarlyDate) {
		this.doneEarlyDate = doneEarlyDate;
	}

	public Double getTodoAmount() {
		return todoAmount;
	}

	public void setTodoAmount(Double todoAmount) {
		this.todoAmount = todoAmount;
	}

	public Double getTodoEarlyDate() {
		return todoEarlyDate;
	}

	public void setTodoEarlyDate(Double todoEarlyDate) {
		this.todoEarlyDate = todoEarlyDate;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public Boolean getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getMaxTicket() {
		return maxTicket;
	}

	public void setMaxTicket(Double maxTicket) {
		this.maxTicket = maxTicket;
	}

	public Double getTaxBearing() {
		return taxBearing;
	}

	public void setTaxBearing(Double taxBearing) {
		this.taxBearing = taxBearing;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	

}
  