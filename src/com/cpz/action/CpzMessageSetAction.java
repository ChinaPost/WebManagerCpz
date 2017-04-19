package com.cpz.action;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.cpz.entity.CpzMessageSetEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//消息设置表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzMessageSetAction" ,results = { 
		@Result(name = "cpzmessageset", location = "/WEB-INF/cpz/cpzmessageset.jsp"),
		@Result(name = "cpzmessagesetSetting", location = "/WEB-INF/cpz/cpzmessagesetSetting.jsp"),
		@Result(name = "cpzmessagesetAdd", location = "/WEB-INF/cpz/cpzmessagesetAdd.jsp"),
		
	})
public class CpzMessageSetAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzMessageSetEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzMessageSetEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzMessageSetEntity  cpzmessageset) {
		this.entity = cpzmessageset;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int messagesetid =Integer.valueOf( StrutsParamUtils.getPraramValue("messagesetid", "0"));
request.setAttribute("messagesetid", messagesetid);
		return "cpzmessageset";
	}
	//消息设置表列表
	public void list(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
		String pageNo = request.getParameter("pageNo");
		if (StringUtils.isBlank(pageNo)) {//判断某字符串是否为空或长度为0或由空白符(whitespace) 构成
			pageNo = "1";
			request.setAttribute("pageNo", pageNo);
		}
		String pageSize = request.getParameter("pageSize");
		if (StringUtils.isBlank(pageSize)) {
			pageSize = "10";
			request.setAttribute("pageSize", pageSize);
		}
String where="";
String where2="";
List argslist=new ArrayList();
Map<String, Object> argsMap = new HashMap<String, Object>();
	String messagesetid = StrutsParamUtils.getPraramValue("messagesetid", "");
		if(StringUtils.isBlank(messagesetid)||"0".equals(messagesetid)){
			//return;
		}else{
where+="messagesetid=? And ";
where2+="messagesetid=:messagesetid And ";
argslist.add(Integer.valueOf(messagesetid));
argsMap.put("messagesetid",Integer.valueOf( messagesetid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzMessageSetEntity  ");
		 sb = new StringBuffer(" select a from CpzMessageSetEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzMessageSetEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzMessageSetEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzMessageSetEntity> list = (List<CpzMessageSetEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzMessageSetAction!list?messagesetid="+messagesetid+"%26pageNo=",true);
		pageString = pageString.replace(".html", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		jsonObject.put("pageString", pageString);
		jsonObject.put("count", count);
		try{
			StrutsParamUtils.getResponse().setCharacterEncoding("UTF-8");
			StrutsParamUtils.getResponse().getWriter().write(jsonObject.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
//跳到修改页
	public String toUpdate() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据
//页面数据
int messagesetid =Integer.valueOf( StrutsParamUtils.getPraramValue("messagesetid", "0"));
request.setAttribute("messagesetid", messagesetid);
		StringBuffer sb = new StringBuffer(" select a from CpzMessageSetEntity a  where messagesetid=:messagesetid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("messagesetid",messagesetid);
		List<CpzMessageSetEntity> list = (List<CpzMessageSetEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzMessageSetEntity) list.get(0));
			return "cpzmessagesetSetting";
		}else{
			return "cpzmessagesetAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzmessageset";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据
//页面数据
int messagesetid =Integer.valueOf( StrutsParamUtils.getPraramValue("messagesetid", "0"));
request.setAttribute("messagesetid", messagesetid);
			return "cpzmessagesetAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzMessageSetEntity a  where messagesetid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getMessagesetid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzmessageset";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int messagesetid =Integer.valueOf( StrutsParamUtils.getPraramValue("messagesetid", "0"));
request.setAttribute("messagesetid", messagesetid);
CpzMessageSetEntity entity = new CpzMessageSetEntity();
entity.setMessagesetid(messagesetid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

