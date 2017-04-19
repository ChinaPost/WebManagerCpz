package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//平台商品信息表
@Entity
@Table(name = "T_CPZ_PLAT_PRODUCT")
public class CpzPlatProductEntity {
	private int productid;//商品代号
	private String productname;//商品名称
	private int productcategoryid;//商品所属分类代号
	private String producttype;//商品类型0：单个商品1：套餐
	private String productstatus;//商品状态0:上架1：下架
	private String tradingunit;//交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶
	private String productcolor;//颜色
	private String isfresh;//是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？）
	private String sourcearea;//原产地名称
	private String iscanrefund;//是否支持退货0：否 1：是
	private String isneedspot;//是否需要当场处理0：否1：是（是否支持当场处理？）
	private String productdetail;//详情URL地址
	private int supportday;//配菜支持天数
	private String channelno;//商品发布渠道 "多个渠道以“,”好隔开01：微信02：安卓03：IOS"（应该没有渠道）
	private String remakr1;//备注1
	private String remakr2;//备注2
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProductid() {
		return productid;
	}
	public void setProductid(int mproductid) {
		productid = mproductid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String mproductname) {
		productname = mproductname;
	}
	public int getProductcategoryid() {
		return productcategoryid;
	}
	public void setProductcategoryid(int mproductcategoryid) {
		productcategoryid = mproductcategoryid;
	}
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String mproducttype) {
		producttype = mproducttype;
	}
	public String getProductstatus() {
		return productstatus;
	}
	public void setProductstatus(String mproductstatus) {
		productstatus = mproductstatus;
	}
	public String getTradingunit() {
		return tradingunit;
	}
	public void setTradingunit(String mtradingunit) {
		tradingunit = mtradingunit;
	}
	public String getProductcolor() {
		return productcolor;
	}
	public void setProductcolor(String mproductcolor) {
		productcolor = mproductcolor;
	}
	public String getIsfresh() {
		return isfresh;
	}
	public void setIsfresh(String misfresh) {
		isfresh = misfresh;
	}
	public String getSourcearea() {
		return sourcearea;
	}
	public void setSourcearea(String msourcearea) {
		sourcearea = msourcearea;
	}
	public String getIscanrefund() {
		return iscanrefund;
	}
	public void setIscanrefund(String miscanrefund) {
		iscanrefund = miscanrefund;
	}
	public String getIsneedspot() {
		return isneedspot;
	}
	public void setIsneedspot(String misneedspot) {
		isneedspot = misneedspot;
	}
	public String getProductdetail() {
		return productdetail;
	}
	public void setProductdetail(String mproductdetail) {
		productdetail = mproductdetail;
	}
	public int getSupportday() {
		return supportday;
	}
	public void setSupportday(int msupportday) {
		supportday = msupportday;
	}
	public String getChannelno() {
		return channelno;
	}
	public void setChannelno(String mchannelno) {
		channelno = mchannelno;
	}
	public String getRemakr1() {
		return remakr1;
	}
	public void setRemakr1(String mremakr1) {
		remakr1 = mremakr1;
	}
	public String getRemakr2() {
		return remakr2;
	}
	public void setRemakr2(String mremakr2) {
		remakr2 = mremakr2;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

