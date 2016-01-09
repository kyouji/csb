package com.ynyes.csb.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;


/**
 * 用户
 * 
 * 储存所有用户信息
 * 
 * @author Sharon
 *
 */

@Entity
public class TdUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	// 头像
	@Column
	private String headImageUrl;
	
	// 证件照
	@Column
	private String paperImageUri;
	
	// 用户名
	@Column(nullable=false, unique=true)
	private String username;
    
	// 状态
    @Column
    private Long statusId;
	
	// 昵称
    @Column
    private String nickname;
	
	// 密码
	@Column(nullable=false)
	private String password;
	
	// 真实姓名
	@Column
	private String realName;
	
	//企业名称
	@Column
	private String enterName;
	
	//企业类型
	@Column
	private String enterType;
	
	//企业类型id
	@Column
	private Long enterTypeId;

	// 角色 【0】公司；【1】会计
    @Column
    private Long roleId;
    
	// 注册时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date registerTime;
	
	// 最后登录时间
	@Column
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;
	
	// 手机号码
	@Column
	private String mobile;
	
	// 电子邮箱
	@Column
	private String email;
    
    // 排序号
    @Column
    private Long sortId;
    
    /*各种资料*/
    
    //累计利润
    @Column
    private Double totalProfit;
    
    //累计毛利率
    @Column
    private Double totalGross;
    
    //本年累计上缴增值税
    @Column
    private Double totalDeduction;
    
    //本年累计上缴增值税，税负
    @Column
    private Double taxBearing;
    
    //本年累计上缴所得税
    @Column
    private Double totalIncomeTax;
    
    //编号
    @Column
    private String number;
   


	
	public Long getId() {
		return id;
	}






	public void setId(Long id) {
		this.id = id;
	}






	public String getHeadImageUrl() {
		return headImageUrl;
	}






	public void setHeadImageUrl(String headImageUrl) {
		this.headImageUrl = headImageUrl;
	}






	public String getPaperImageUri() {
		return paperImageUri;
	}






	public void setPaperImageUri(String paperImageUri) {
		this.paperImageUri = paperImageUri;
	}






	public String getUsername() {
		return username;
	}






	public void setUsername(String username) {
		this.username = username;
	}






	public Long getStatusId() {
		return statusId;
	}






	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}






	public String getNickname() {
		return nickname;
	}






	public void setNickname(String nickname) {
		this.nickname = nickname;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	public String getRealName() {
		return realName;
	}






	public void setRealName(String realName) {
		this.realName = realName;
	}






	public String getEnterName() {
		return enterName;
	}






	public void setEnterName(String enterName) {
		this.enterName = enterName;
	}






	public String getEnterType() {
		return enterType;
	}






	public void setEnterType(String enterType) {
		this.enterType = enterType;
	}






	public Long getEnterTypeId() {
		return enterTypeId;
	}






	public void setEnterTypeId(Long enterTypeId) {
		this.enterTypeId = enterTypeId;
	}






	public Long getRoleId() {
		return roleId;
	}






	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}






	public Date getRegisterTime() {
		return registerTime;
	}






	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}






	public Date getLastLoginTime() {
		return lastLoginTime;
	}






	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}






	public String getMobile() {
		return mobile;
	}






	public void setMobile(String mobile) {
		this.mobile = mobile;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public Long getSortId() {
		return sortId;
	}






	public void setSortId(Long sortId) {
		this.sortId = sortId;
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






	public Double getTotalDeduction() {
		return totalDeduction;
	}






	public void setTotalDeduction(Double totalDeduction) {
		this.totalDeduction = totalDeduction;
	}






	public Double getTaxBearing() {
		return taxBearing;
	}






	public void setTaxBearing(Double taxBearing) {
		this.taxBearing = taxBearing;
	}






	public Double getTotalIncomeTax() {
		return totalIncomeTax;
	}






	public void setTotalIncomeTax(Double totalIncomeTax) {
		this.totalIncomeTax = totalIncomeTax;
	}






	public String getNumber() {
		return number;
	}






	public void setNumber(String number) {
		this.number = number;
	}






	@Override
	public String toString() {
		return "TdUser [id=" + id + ", lastLoginTime=" + lastLoginTime + "]";
	}

    
    
}
