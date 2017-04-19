package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//买家退款表
@Entity
@Table(name = "T_CPZ_BUYER_REFUND")
public class CpzBuyerRefundEntity {
	private int refundid;//退款单号 顺序号
	private String userid;//会员号
	private String orderno;//订单号
	private String refundreason;//退款原因
	private String isconsultseller;//是否与卖家已协商"0：是1：否"
	private String refundstyle;//退款方式"0：系统退款1：人工退款"
	private String ordermoney;//订单原金额
	private String applymoney;//退款申请金额
	private String applytime;//申请时 间 格式：yyyymmdd hh24miss
	private String refundtime;//最后退款时间 格式：yyyymmdd hh24miss
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRefundid() {
		return refundid;
	}
	public void setRefundid(int mrefundid) {
		refundid = mrefundid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String muserid) {
		userid = muserid;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String morderno) {
		orderno = morderno;
	}
	public String getRefundreason() {
		return refundreason;
	}
	public void setRefundreason(String mrefundreason) {
		refundreason = mrefundreason;
	}
	public String getIsconsultseller() {
		return isconsultseller;
	}
	public void setIsconsultseller(String misconsultseller) {
		isconsultseller = misconsultseller;
	}
	public String getRefundstyle() {
		return refundstyle;
	}
	public void setRefundstyle(String mrefundstyle) {
		refundstyle = mrefundstyle;
	}
	public String getOrdermoney() {
		return ordermoney;
	}
	public void setOrdermoney(String mordermoney) {
		ordermoney = mordermoney;
	}
	public String getApplymoney() {
		return applymoney;
	}
	public void setApplymoney(String mapplymoney) {
		applymoney = mapplymoney;
	}
	public String getApplytime() {
		return applytime;
	}
	public void setApplytime(String mapplytime) {
		applytime = mapplytime;
	}
	public String getRefundtime() {
		return refundtime;
	}
	public void setRefundtime(String mrefundtime) {
		refundtime = mrefundtime;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

