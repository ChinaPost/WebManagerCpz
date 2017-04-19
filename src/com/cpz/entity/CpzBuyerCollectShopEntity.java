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
//买家店铺收藏信息表
@Entity
@Table(name = "T_CPZ_BUYER_COLLECT_SHOP")
@IdClass(CpzBuyerCollectShopEntityIds.class)
public class CpzBuyerCollectShopEntity {
	private int userid;//用户id
	private int shopid;//店铺代号
	private int marketid;//店铺所在市场代号
	private int shopbusineerangeid;//店铺经营范围表id
	private String isdefaultshop;//是否默认店铺"0：否1：是"
	private String colletctime;//收藏时间
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUserid() {
		return userid;
	}
	public void setUserid(int muserid) {
		userid = muserid;
	}
	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO)
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
	public int getShopbusineerangeid() {
		return shopbusineerangeid;
	}
	public void setShopbusineerangeid(int mshopbusineerangeid) {
		shopbusineerangeid = mshopbusineerangeid;
	}
	public String getIsdefaultshop() {
		return isdefaultshop;
	}
	public void setIsdefaultshop(String misdefaultshop) {
		isdefaultshop = misdefaultshop;
	}
	public String getColletctime() {
		return colletctime;
	}
	public void setColletctime(String mcolletctime) {
		colletctime = mcolletctime;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

