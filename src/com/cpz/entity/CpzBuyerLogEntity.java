package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//订单或者退款单日志表
@Entity
@Table(name = "T_CPZ_BUYER_LOG")
public class CpzBuyerLogEntity {
	private int orderlogid;//日志流水号 顺序号，从1开始
	private int dealtime;//处理时间
	private String dealtype;//处理类型"0：下单 1：支付 2：配货 3：配货取消 4：取货 5：退款申请6：退款完成"
	private String dealcontent;//处理信息内容
	private int dealperson;//处理人"0：买家 1：卖家"
	private String lonvalue;//操作经度 格式：小数点后2位
	private String latvalue;//操作纬度
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getOrderlogid() {
		return orderlogid;
	}
	public void setOrderlogid(int morderlogid) {
		orderlogid = morderlogid;
	}
	public int getDealtime() {
		return dealtime;
	}
	public void setDealtime(int mdealtime) {
		dealtime = mdealtime;
	}
	public String getDealtype() {
		return dealtype;
	}
	public void setDealtype(String mdealtype) {
		dealtype = mdealtype;
	}
	public String getDealcontent() {
		return dealcontent;
	}
	public void setDealcontent(String mdealcontent) {
		dealcontent = mdealcontent;
	}
	public int getDealperson() {
		return dealperson;
	}
	public void setDealperson(int mdealperson) {
		dealperson = mdealperson;
	}
	public String getLonvalue() {
		return lonvalue;
	}
	public void setLonvalue(String mlonvalue) {
		lonvalue = mlonvalue;
	}
	public String getLatvalue() {
		return latvalue;
	}
	public void setLatvalue(String mlatvalue) {
		latvalue = mlatvalue;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

