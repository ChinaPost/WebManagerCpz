package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//买家基本信息表
@Entity
@Table(name = "T_CPZ_BUYER_MSG")
public class CpzBuyerMsgEntity {
	private int userid;//会员号
	private String moileno;//手机号码
	private String nickname;//会员呢称
	private String sex;//性别"0：女1：男"
	private String headurl;//会员头像图片URL地址
	private String cstmlevel;//会员等级"预留01：铜牌会员02：银牌会员03：金牌会员"
	private String lonvalue;//注册时所在经度格式：小数点后2位
	private String latvalue;//注册时所在纬度格式：小数点后2位
	private String createtime;//注册时间格式：yyyymmdd hh24miss
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUserid() {
		return userid;
	}
	public void setUserid(int muserid) {
		userid = muserid;
	}
	public String getMoileno() {
		return moileno;
	}
	public void setMoileno(String mmoileno) {
		moileno = mmoileno;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String mnickname) {
		nickname = mnickname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String msex) {
		sex = msex;
	}
	public String getHeadurl() {
		return headurl;
	}
	public void setHeadurl(String mheadurl) {
		headurl = mheadurl;
	}
	public String getCstmlevel() {
		return cstmlevel;
	}
	public void setCstmlevel(String mcstmlevel) {
		cstmlevel = mcstmlevel;
	}
	public String getLonvalue() {
		return lonvalue;
	}
	public void setLonvalue(String mlonvalue) {
		lonvalue = mlonvalue;
	}
	public String getLatvalue() {
		return latvalue;
	}
	public void setLatvalue(String mlatvalue) {
		latvalue = mlatvalue;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String mcreatetime) {
		createtime = mcreatetime;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

