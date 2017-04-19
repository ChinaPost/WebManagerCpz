package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//订单状态操作表
@Entity
@Table(name = "T_CPZ_ORDER_STATUS_OP")
public class CpzOrderStatusOpEntity {
	private int orderno;//订单号
	private String orderstatus;//当前订单状态
	private String opfalg;//可操作标志"01：支付02：订单取消 03：配货取消 04：退款"
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int morderno) {
		orderno = morderno;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String morderstatus) {
		orderstatus = morderstatus;
	}
	public String getOpfalg() {
		return opfalg;
	}
	public void setOpfalg(String mopfalg) {
		opfalg = mopfalg;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

