package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//销量统计表
@Entity
@Table(name = "T_GSP_SALES_REPORT")
public class GspSalesReportEntity {
	private int salesreportid;//主键
	private String datestr;//时间（以天为单位）
	private String merchname;//商品名称
	private String weight;//重量
	private String settsumprice;//结算费用
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSalesreportid() {
		return salesreportid;
	}
	public void setSalesreportid(int msalesreportid) {
		salesreportid = msalesreportid;
	}
	public String getDatestr() {
		return datestr;
	}
	public void setDatestr(String mdatestr) {
		datestr = mdatestr;
	}
	public String getMerchname() {
		return merchname;
	}
	public void setMerchname(String mmerchname) {
		merchname = mmerchname;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String mweight) {
		weight = mweight;
	}
	public String getSettsumprice() {
		return settsumprice;
	}
	public void setSettsumprice(String msettsumprice) {
		settsumprice = msettsumprice;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

