package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//买家购物车表
@Entity
@Table(name = "T_CPZ_BUYER_SHOPPING")
public class CpzBuyerShoppingEntity {
	private int shopcarid;//购物车id
	private int userid;//会员号
	private int shopproductid;//卖家商品代号
	private int shopid;//卖家店铺代号
	private int marketid;//卖家市场代号
	private int normsid;//购买规格
	private int buynum;//购买数量
	private String normsprice;//购买价格
	private String createtime;//加入购物车时间格式：yyyymmdd hh24miss
	private String remark;//备注
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getShopcarid() {
		return shopcarid;
	}
	public void setShopcarid(int mshopcarid) {
		shopcarid = mshopcarid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int muserid) {
		userid = muserid;
	}
	public int getShopproductid() {
		return shopproductid;
	}
	public void setShopproductid(int mshopproductid) {
		shopproductid = mshopproductid;
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
	public int getNormsid() {
		return normsid;
	}
	public void setNormsid(int mnormsid) {
		normsid = mnormsid;
	}
	public int getBuynum() {
		return buynum;
	}
	public void setBuynum(int mbuynum) {
		buynum = mbuynum;
	}
	public String getNormsprice() {
		return normsprice;
	}
	public void setNormsprice(String mnormsprice) {
		normsprice = mnormsprice;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String mcreatetime) {
		createtime = mcreatetime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String mremark) {
		remark = mremark;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

