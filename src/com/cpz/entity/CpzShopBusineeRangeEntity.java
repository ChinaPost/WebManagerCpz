package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//店铺经营范围表
@Entity
@Table(name = "T_CPZ_SHOP_BUSINEE_RANGE")
public class CpzShopBusineeRangeEntity {
	private int shopbusineerangeid;//主键id
	private String businame;//经营范围名称
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getShopbusineerangeid() {
		return shopbusineerangeid;
	}
	public void setShopbusineerangeid(int mshopbusineerangeid) {
		shopbusineerangeid = mshopbusineerangeid;
	}
	public String getBusiname() {
		return businame;
	}
	public void setBusiname(String mbusiname) {
		businame = mbusiname;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

