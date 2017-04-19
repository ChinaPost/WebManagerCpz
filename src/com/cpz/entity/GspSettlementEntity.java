package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//结算表
@Entity
@Table(name = "T_GSP_SETTLEMENT")
public class GspSettlementEntity {
	private int settid;//主键
	private String orderno;//订单号
	private String settsn;//结算流水
	private int status;//结算状态
	private String sumprice;//总费用
	private int sellerid;//商家ID
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSettid() {
		return settid;
	}
	public void setSettid(int msettid) {
		settid = msettid;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String morderno) {
		orderno = morderno;
	}
	public String getSettsn() {
		return settsn;
	}
	public void setSettsn(String msettsn) {
		settsn = msettsn;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int mstatus) {
		status = mstatus;
	}
	public String getSumprice() {
		return sumprice;
	}
	public void setSumprice(String msumprice) {
		sumprice = msumprice;
	}
	public int getSellerid() {
		return sellerid;
	}
	public void setSellerid(int msellerid) {
		sellerid = msellerid;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

