package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//买家或者卖家关联第三方信息表
@Entity
@Table(name = "T_CPZ_TERMINFO_LINK")
public class CpzTerminfoLinkEntity {
	private int systemno;//系统用户号
	private String systemtyoe;//系统类别"0:买家1：卖家2：批发商"
	private String linktype;//第三方代号类型"0：微信OPENID1：安卓设备号2：IOS设备号"
	private String linkno;//第三方代号 如微信公众号对应的OPENID或者系统设备号
	private String linktime;//关联时间格式：yyyymmdd hh24miss
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSystemno() {
		return systemno;
	}
	public void setSystemno(int msystemno) {
		systemno = msystemno;
	}
	public String getSystemtyoe() {
		return systemtyoe;
	}
	public void setSystemtyoe(String msystemtyoe) {
		systemtyoe = msystemtyoe;
	}
	public String getLinktype() {
		return linktype;
	}
	public void setLinktype(String mlinktype) {
		linktype = mlinktype;
	}
	public String getLinkno() {
		return linkno;
	}
	public void setLinkno(String mlinkno) {
		linkno = mlinkno;
	}
	public String getLinktime() {
		return linktime;
	}
	public void setLinktime(String mlinktime) {
		linktime = mlinktime;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

