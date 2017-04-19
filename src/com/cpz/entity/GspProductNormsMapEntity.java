package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//商品规格关联表
@Entity
@Table(name = "T_GSP_PRODUCT_NORMS_MAP")
public class GspProductNormsMapEntity {
	private int id;//Id
	private int shopproductid;//商品id
	private int shopnormsid;//规格代号
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int mid) {
		id = mid;
	}
	public int getShopproductid() {
		return shopproductid;
	}
	public void setShopproductid(int mshopproductid) {
		shopproductid = mshopproductid;
	}
	public int getShopnormsid() {
		return shopnormsid;
	}
	public void setShopnormsid(int mshopnormsid) {
		shopnormsid = mshopnormsid;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

