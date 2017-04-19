package com.cpz.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;

//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "LoginAction", results = {
		@Result(name = "loginsucess", location = "/WEB-INF/mainpage/leftRightFrame.jsp"),
		@Result(name = "loginerror", location = "/WEB-INF/cpz/bbqq/bbqqSetting.jsp"),


})
public class LoginAction {

	@Resource
	private ObjectDao objectDao;

	

	public ObjectDao getObjectDao() {
		return objectDao;
	}

	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}

	
	public String login() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
		String dd = StrutsParamUtils.getPraramValue("dd", "");
		request.setAttribute("dd", dd);
		
		
		return "loginsucess";
	}

	

}
