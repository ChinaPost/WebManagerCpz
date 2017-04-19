package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//消息设置表
@Entity
@Table(name = "T_CPZ_MESSAGE_SET")
public class CpzMessageSetEntity {
	private int messagesetid;//主键
	private int messagetype;//消息类型
	private int systemno;//系统用户号
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMessagesetid() {
		return messagesetid;
	}
	public void setMessagesetid(int mmessagesetid) {
		messagesetid = mmessagesetid;
	}
	public int getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(int mmessagetype) {
		messagetype = mmessagetype;
	}
	public int getSystemno() {
		return systemno;
	}
	public void setSystemno(int msystemno) {
		systemno = msystemno;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

