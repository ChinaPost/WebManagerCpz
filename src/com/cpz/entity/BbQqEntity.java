package com.cpz.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "bbqq")
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BbQqEntity {
	private int dd;//ID
	private String b;//好吧
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getDd() {
		return dd;
	}
	public void setDd(int mdd) {
		dd = mdd;
	}
	public String getB() {
		return b;
	}
	public void setB(String mb) {
		b = mb;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

