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
public class TdGather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    // 排序
    @Column
    private Long  sortId;
    
    //用户名
    @Column
    private String username;
    
    // 名称
    @Column
    private String title;
  
    //状态id
    @Column
    private Long statusId;
    
    //填写时间
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date time;
    
    /*---------------------------------------------------------
    ===============  收入 =================
    ---------------------------------------------------------*/
    // 【普票】数量
    @Column
    private String generalAmount;
    
    // 【普票】不含税收入
    @Column
    private String generalIncome;
    
    // 【普票】销项税
    @Column
    private String generalTax;
    
    // 【专票】数量
    @Column
    private String specialAmount;
    
    // 【专票】不含税收入
    @Column
    private String specialIncome;
    
    // 【专票】销项税
    @Column
    private String specialTax;
    
    // 【不开票】收入
    @Column
    private String noTicketIncome;
    
    // 【不开票】销项税
    @Column
    private String noTicketTax;
    
    // 不含税收入合计
    @Column
    private String TotalIncome;
    
    // 销项税合计
    @Column
    private String TotalTax;
    
    /*---------------------------------------------------------
    ===============  进货 =================
    ---------------------------------------------------------*/
    // 上月留抵税金
    @Column
    private String taxRetention;
    
    // 【上月进项税额】数量
    @Column
    private String vatAmount;
    
    // 【上月进项税额】税额
    @Column
    private String vat;
    
    // 【运费抵扣】数量
    @Column
    private String transDeductionAmount;
    
    // 【运费抵扣】税额
    @Column
    private String transDeduction;
    
    // 【增值税抵扣】数量
    @Column
    private String taxDeductionAmount;
    
    // 【增值税抵扣】税额
    @Column
    private String taxDeduction;
    
    // 进项税额合计
    @Column
    private String totalVat;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSortId() {
		return sortId;
	}

	public void setSortId(Long sortId) {
		this.sortId = sortId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getGeneralAmount() {
		return generalAmount;
	}

	public void setGeneralAmount(String generalAmount) {
		this.generalAmount = generalAmount;
	}

	public String getGeneralIncome() {
		return generalIncome;
	}

	public void setGeneralIncome(String generalIncome) {
		this.generalIncome = generalIncome;
	}

	public String getGeneralTax() {
		return generalTax;
	}

	public void setGeneralTax(String generalTax) {
		this.generalTax = generalTax;
	}

	public String getSpecialAmount() {
		return specialAmount;
	}

	public void setSpecialAmount(String specialAmount) {
		this.specialAmount = specialAmount;
	}

	public String getSpecialIncome() {
		return specialIncome;
	}

	public void setSpecialIncome(String specialIncome) {
		this.specialIncome = specialIncome;
	}

	public String getSpecialTax() {
		return specialTax;
	}

	public void setSpecialTax(String specialTax) {
		this.specialTax = specialTax;
	}

	public String getNoTicketIncome() {
		return noTicketIncome;
	}

	public void setNoTicketIncome(String noTicketIncome) {
		this.noTicketIncome = noTicketIncome;
	}

	public String getNoTicketTax() {
		return noTicketTax;
	}

	public void setNoTicketTax(String noTicketTax) {
		this.noTicketTax = noTicketTax;
	}

	public String getTotalIncome() {
		return TotalIncome;
	}

	public void setTotalIncome(String totalIncome) {
		TotalIncome = totalIncome;
	}

	public String getTotalTax() {
		return TotalTax;
	}

	public void setTotalTax(String totalTax) {
		TotalTax = totalTax;
	}

	public String getTaxRetention() {
		return taxRetention;
	}

	public void setTaxRetention(String taxRetention) {
		this.taxRetention = taxRetention;
	}

	public String getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(String vatAmount) {
		this.vatAmount = vatAmount;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getTransDeductionAmount() {
		return transDeductionAmount;
	}

	public void setTransDeductionAmount(String transDeductionAmount) {
		this.transDeductionAmount = transDeductionAmount;
	}

	public String getTransDeduction() {
		return transDeduction;
	}

	public void setTransDeduction(String transDeduction) {
		this.transDeduction = transDeduction;
	}

	public String getTaxDeductionAmount() {
		return taxDeductionAmount;
	}

	public void setTaxDeductionAmount(String taxDeductionAmount) {
		this.taxDeductionAmount = taxDeductionAmount;
	}

	public String getTaxDeduction() {
		return taxDeduction;
	}

	public void setTaxDeduction(String taxDeduction) {
		this.taxDeduction = taxDeduction;
	}

	public String getTotalVat() {
		return totalVat;
	}

	public void setTotalVat(String totalVat) {
		this.totalVat = totalVat;
	}

}
  