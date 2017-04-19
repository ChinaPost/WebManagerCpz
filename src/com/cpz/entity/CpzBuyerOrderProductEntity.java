package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//订单关联商品表
@Entity
@Table(name = "T_CPZ_BUYER_ORDER_PRODUCT")
@IdClass(CpzBuyerOrderProductEntityIds.class)
public class CpzBuyerOrderProductEntity {
	private String orderno;//订单号
	private int shopproductid;//卖家商品代号
	private String shopproductname;//商品名称
	private int levelid;//商品所属分类代号
	private String producttype;//商品类型"0：单个商品:1：套餐"
	private String shopnormsid;//购买规格
	private int shopnormsnum;//购买数量
	private String shopnormsprice;//购买价格
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public String getOrderno() {
		return orderno;
	}
	public void setOrderno(String morderno) {
		orderno = morderno;
	}
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
	public int getShopproductid() {
		return shopproductid;
	}
	public void setShopproductid(int mshopproductid) {
		shopproductid = mshopproductid;
	}
	public String getShopproductname() {
		return shopproductname;
	}
	public void setShopproductname(String mshopproductname) {
		shopproductname = mshopproductname;
	}
	public int getLevelid() {
		return levelid;
	}
	public void setLevelid(int mlevelid) {
		levelid = mlevelid;
	}
	public String getProducttype() {
		return producttype;
	}
	public void setProducttype(String mproducttype) {
		producttype = mproducttype;
	}
	public String getShopnormsid() {
		return shopnormsid;
	}
	public void setShopnormsid(String mshopnormsid) {
		shopnormsid = mshopnormsid;
	}
	public int getShopnormsnum() {
		return shopnormsnum;
	}
	public void setShopnormsnum(int mshopnormsnum) {
		shopnormsnum = mshopnormsnum;
	}
	public String getShopnormsprice() {
		return shopnormsprice;
	}
	public void setShopnormsprice(String mshopnormsprice) {
		shopnormsprice = mshopnormsprice;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

