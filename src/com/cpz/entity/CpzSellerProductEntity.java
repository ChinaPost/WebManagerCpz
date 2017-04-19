package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//卖家商品信息表
@Entity
@Table(name = "T_CPZ_SELLER_PRODUCT")
public class CpzSellerProductEntity {
	private int shopproductid;//卖家商品代号
	private String productname;//商品自定义名称
	private String productpic;//商品自定义图片
	private String merchintro;//商品简介
	private String description;//商品描述
	private String productstatus;//商品状态
	private int productid;//关联的平台商品
	private int shopid;//关联店铺代号
	private String orderno;//订单号格式：日期+10位流水号
	private String starttime;//商品在线开始时间格式：yyyymmdd hh24miss
	private String endtime;//商品在线结束时间格式：yyyymmdd hh24miss
	private String remakr1;//备注
	private int levelid;//关联平台分类
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getShopproductid() {
		return shopproductid;
	}
	public void setShopproductid(int mshopproductid) {
		shopproductid = mshopproductid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String mproductname) {
		productname = mproductname;
	}
	public String getProductpic() {
		return productpic;
	}
	public void setProductpic(String mproductpic) {
		productpic = mproductpic;
	}
	public String getMerchintro() {
		return merchintro;
	}
	public void setMerchintro(String mmerchintro) {
		merchintro = mmerchintro;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String mdescription) {
		description = mdescription;
	}
	public String getProductstatus() {
		return productstatus;
	}
	public void setProductstatus(String mproductstatus) {
		productstatus = mproductstatus;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int mproductid) {
		productid = mproductid;
	}
	public int getShopid() {
		return shopid;
	}
	public void setShopid(int mshopid) {
		shopid = mshopid;
	}
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String morderno) {
		orderno = morderno;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String mstarttime) {
		starttime = mstarttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String mendtime) {
		endtime = mendtime;
	}
	public String getRemakr1() {
		return remakr1;
	}
	public void setRemakr1(String mremakr1) {
		remakr1 = mremakr1;
	}
	public int getLevelid() {
		return levelid;
	}
	public void setLevelid(int mlevelid) {
		levelid = mlevelid;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

