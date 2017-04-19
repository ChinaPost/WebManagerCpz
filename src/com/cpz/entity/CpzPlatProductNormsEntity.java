package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//平台商品规格表
@Entity
@Table(name = "T_CPZ_PLAT_PRODUCT_NORMS")
public class CpzPlatProductNormsEntity {
	private int normsid;//规格代号
	private int productid;//商品代号
	private String normsname;//规格名称
	private String remark1;//备注
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getNormsid() {
		return normsid;
	}
	public void setNormsid(int mnormsid) {
		normsid = mnormsid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int mproductid) {
		productid = mproductid;
	}
	public String getNormsname() {
		return normsname;
	}
	public void setNormsname(String mnormsname) {
		normsname = mnormsname;
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

