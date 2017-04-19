package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//商品分类表
@Entity
@Table(name = "T_CPZ_PRODUCT_LEVEL")
public class CpzProductLevelEntity {
	private int productcategoryid;//级别代号顺序号，从1开始
	private String productcategoryname;//级别名称
	private int parentid;//父级别代号
	private String parentname;//父级别名称
	private String productcategorygrade;//级别等级"0：根级别1：第1级2：第2级"
	private String path;//树路径
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getProductcategoryid() {
		return productcategoryid;
	}
	public void setProductcategoryid(int mproductcategoryid) {
		productcategoryid = mproductcategoryid;
	}
	public String getProductcategoryname() {
		return productcategoryname;
	}
	public void setProductcategoryname(String mproductcategoryname) {
		productcategoryname = mproductcategoryname;
	}
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int mparentid) {
		parentid = mparentid;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String mparentname) {
		parentname = mparentname;
	}
	public String getProductcategorygrade() {
		return productcategorygrade;
	}
	public void setProductcategorygrade(String mproductcategorygrade) {
		productcategorygrade = mproductcategorygrade;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String mpath) {
		path = mpath;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

