package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//平台商品推荐表
@Entity
@Table(name = "T_CPZ_PLAT_PRODUCT_RECOMMEND")
public class CpzPlatProductRecommendEntity {
	private int id;//Id
	private int productid;//商品代号
	private String channelno;//推荐渠道
	private String saletype;//推荐标识"0：推荐1：热门"
	private String starttime;//有效开始时间格式：yyyymmdd hh24miss
	private String endtime;//有效结束时间格式：yyyymmdd hh24miss
	private String createtime;//记录创建时间格式：yyyymmdd hh24miss
	private String remark1;//备注
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int mid) {
		id = mid;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int mproductid) {
		productid = mproductid;
	}
	public String getChannelno() {
		return channelno;
	}
	public void setChannelno(String mchannelno) {
		channelno = mchannelno;
	}
	public String getSaletype() {
		return saletype;
	}
	public void setSaletype(String msaletype) {
		saletype = msaletype;
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
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String mcreatetime) {
		createtime = mcreatetime;
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

