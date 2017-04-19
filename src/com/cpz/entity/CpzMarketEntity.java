package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//市场表
@Entity
@Table(name = "T_CPZ_MARKET")
public class CpzMarketEntity {
	private int marketid;//市场ID
	private String marketname;//市场名字
	private String lonvalue;//市场经度
	private String latvalue;//市场纬度
	private String provcode;//省份代号
	private String citycode;//市局代号
	private String countycode;//区县代号
	private String marketarea;//市场所属片区
	private String marketaddr;//市场详细地址
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMarketid() {
		return marketid;
	}
	public void setMarketid(int mmarketid) {
		marketid = mmarketid;
	}
	public String getMarketname() {
		return marketname;
	}
	public void setMarketname(String mmarketname) {
		marketname = mmarketname;
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
	public String getProvcode() {
		return provcode;
	}
	public void setProvcode(String mprovcode) {
		provcode = mprovcode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String mcitycode) {
		citycode = mcitycode;
	}
	public String getCountycode() {
		return countycode;
	}
	public void setCountycode(String mcountycode) {
		countycode = mcountycode;
	}
	public String getMarketarea() {
		return marketarea;
	}
	public void setMarketarea(String mmarketarea) {
		marketarea = mmarketarea;
	}
	public String getMarketaddr() {
		return marketaddr;
	}
	public void setMarketaddr(String mmarketaddr) {
		marketaddr = mmarketaddr;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

