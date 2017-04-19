package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//卖家表
@Entity
@Table(name = "T_CPZ_SELLER")
public class CpzSellerEntity {
	private int sellerid;//主键
	private String account;//账号
	private String mobile;//手机号码
	private String password;//密码
	private int status;//状态
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int msellerid) {
		sellerid = msellerid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String maccount) {
		account = maccount;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mmobile) {
		mobile = mmobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String mpassword) {
		password = mpassword;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int mstatus) {
		status = mstatus;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

