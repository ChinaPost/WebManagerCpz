package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//平台商品图片表
@Entity
@Table(name = "T_CPZ_PLAT_PRODUCT_PIC")
public class CpzPlatProductPicEntity {
	private int productid;//商品代号
	private String picname;//图片名称
	private int picshowno;//图片显示顺序
	private String picnorms;//图片规格"0:大1：中2：小"
	private String picurl;//图片地址
	private String remark1;//备注
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProductid() {
		return productid;
	}
	public void setProductid(int mproductid) {
		productid = mproductid;
	}
	public String getPicname() {
		return picname;
	}
	public void setPicname(String mpicname) {
		picname = mpicname;
	}
	public int getPicshowno() {
		return picshowno;
	}
	public void setPicshowno(int mpicshowno) {
		picshowno = mpicshowno;
	}
	public String getPicnorms() {
		return picnorms;
	}
	public void setPicnorms(String mpicnorms) {
		picnorms = mpicnorms;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String mpicurl) {
		picurl = mpicurl;
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

