package com.cpz.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
//消息表
@Entity
@Table(name = "T_CPZ_MESSAGE")
public class CpzMessageEntity {
	private int messageid;//主键
	private String messagedetail;//内容
	private String messagetype;//01：业务通知02：系统变更通知03：业务进展通知04：其它通知
	private int readmessageflag;//0：未读 1：已读
	private int systemno;//系统用户
	private String messagechannel;//消息渠道01：微信02：安卓03：IOS
	private String messagelinktype;//消息链接类型
	private String messagelinkpara;//消息链接业务参数
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int mmessageid) {
		messageid = mmessageid;
	}
	public String getMessagedetail() {
		return messagedetail;
	}
	public void setMessagedetail(String mmessagedetail) {
		messagedetail = mmessagedetail;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String mmessagetype) {
		messagetype = mmessagetype;
	}
	public int getReadmessageflag() {
		return readmessageflag;
	}
	public void setReadmessageflag(int mreadmessageflag) {
		readmessageflag = mreadmessageflag;
	}
	public int getSystemno() {
		return systemno;
	}
	public void setSystemno(int msystemno) {
		systemno = msystemno;
	}
	public String getMessagechannel() {
		return messagechannel;
	}
	public void setMessagechannel(String mmessagechannel) {
		messagechannel = mmessagechannel;
	}
	public String getMessagelinktype() {
		return messagelinktype;
	}
	public void setMessagelinktype(String mmessagelinktype) {
		messagelinktype = mmessagelinktype;
	}
	public String getMessagelinkpara() {
		return messagelinkpara;
	}
	public void setMessagelinkpara(String mmessagelinkpara) {
		messagelinkpara = mmessagelinkpara;
	}
	//public String toString() {
    //    return ToStringBuilder.reflectionToString(this);
    //}
}

