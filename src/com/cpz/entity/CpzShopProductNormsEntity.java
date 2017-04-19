package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//卖家商品规格表
@Entity
@Table(name = "T_CPZ_SHOP_PRODUCT_NORMS")
public class CpzShopProductNormsEntity {
	private int shopnormsid;//规格代号
	private int shopproductid;//商品代号
	private String normsname;//规格名称
	private String normsprice;//规格对应价格
	private String remark1;//备注
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getShopnormsid() {
		return shopnormsid;
	}
	public void setShopnormsid(int mshopnormsid) {
		shopnormsid = mshopnormsid;
	}
	public int getShopproductid() {
		return shopproductid;
	}
	public void setShopproductid(int mshopproductid) {
		shopproductid = mshopproductid;
	}
	public String getNormsname() {
		return normsname;
	}
	public void setNormsname(String mnormsname) {
		normsname = mnormsname;
	}
	public String getNormsprice() {
		return normsprice;
	}
	public void setNormsprice(String mnormsprice) {
		normsprice = mnormsprice;
	}
	public String getRemark1() {
		return remark1;
	}
	public void setRemark1(String mremark1) {
		remark1 = mremark1;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

