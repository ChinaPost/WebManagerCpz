package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//买家订单表
@Entity
@Table(name = "T_CPZ_BUYER_ORDER")
public class CpzBuyerOrderEntity {
	private String orderno;//订单号格式：日期+10位流水号
	private int userid;//会员号
	private String username;//会员姓名
	private String userphone;//会员联系手机
	private String orderstatus;//订单状态"01：待付款02：已付款03：等待发货04：已备货05：已确认收货06：已完成07：已取消08：退款中09：已退款10：退款拒绝"
	private String totalmoney;//订单总金额
	private String paymoney;//订单已支付金额
	private String paystatus;//订单支付状态"0：未支付1：支付中2：已支付"
	private int shopid;//店铺代号
	private int marketid;//所在市场代号
	private String shiptype;//配送方式"预留 01：自提02：寄递"
	private String provcode;//省份代号"预留：目前阶段接口送空值"
	private String citycode;//市局代号"预留：目前阶段接口送空值"
	private String countycode;//区县代号"预留：目前阶段接口送空值"
	private String detailaddr;//详细地址代号
	private String invoicetype;//发票类型"预留发票模块：目前阶段接口送0：不开发票 1：个人发票 2：单位发票"
	private String invoicetitle;//发票抬头
	private String orderremark;//给卖家留言
	private String getproducttime;//提货时间
	private String booktime;//订单下单时间格式：yyyymmdd hh24miss
	private String paytime;//订单支付时间格式：yyyymmdd hh24miss
	private String channelno;//订单受理渠道"01：微信02：安卓03：IOS"
	private String remark1;//备注1
	private String remark2;//备注2
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String morderno) {
		orderno = morderno;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int muserid) {
		userid = muserid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String musername) {
		username = musername;
	}
	public String getUserphone() {
		return userphone;
	}
	public void setUserphone(String muserphone) {
		userphone = muserphone;
	}
	public String getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(String morderstatus) {
		orderstatus = morderstatus;
	}
	public String getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(String mtotalmoney) {
		totalmoney = mtotalmoney;
	}
	public String getPaymoney() {
		return paymoney;
	}
	public void setPaymoney(String mpaymoney) {
		paymoney = mpaymoney;
	}
	public String getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(String mpaystatus) {
		paystatus = mpaystatus;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int mshopid) {
		shopid = mshopid;
	}
	public int getMarketid() {
		return marketid;
	}
	public void setMarketid(int mmarketid) {
		marketid = mmarketid;
	}
	public String getShiptype() {
		return shiptype;
	}
	public void setShiptype(String mshiptype) {
		shiptype = mshiptype;
	}
	public String getProvcode() {
		return provcode;
	}
	public void setProvcode(String mprovcode) {
		provcode = mprovcode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String mcitycode) {
		citycode = mcitycode;
	}
	public String getCountycode() {
		return countycode;
	}
	public void setCountycode(String mcountycode) {
		countycode = mcountycode;
	}
	public String getDetailaddr() {
		return detailaddr;
	}
	public void setDetailaddr(String mdetailaddr) {
		detailaddr = mdetailaddr;
	}
	public String getInvoicetype() {
		return invoicetype;
	}
	public void setInvoicetype(String minvoicetype) {
		invoicetype = minvoicetype;
	}
	public String getInvoicetitle() {
		return invoicetitle;
	}
	public void setInvoicetitle(String minvoicetitle) {
		invoicetitle = minvoicetitle;
	}
	public String getOrderremark() {
		return orderremark;
	}
	public void setOrderremark(String morderremark) {
		orderremark = morderremark;
	}
	public String getGetproducttime() {
		return getproducttime;
	}
	public void setGetproducttime(String mgetproducttime) {
		getproducttime = mgetproducttime;
	}
	public String getBooktime() {
		return booktime;
	}
	public void setBooktime(String mbooktime) {
		booktime = mbooktime;
	}
	public String getPaytime() {
		return paytime;
	}
	public void setPaytime(String mpaytime) {
		paytime = mpaytime;
	}
	public String getChannelno() {
		return channelno;
	}
	public void setChannelno(String mchannelno) {
		channelno = mchannelno;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String mremark1) {
		remark1 = mremark1;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String mremark2) {
		remark2 = mremark2;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

