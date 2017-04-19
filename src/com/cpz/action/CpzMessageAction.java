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

import com.cpz.entity.CpzMessageEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//消息表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzMessageAction" ,results = { 
		@Result(name = "cpzmessage", location = "/WEB-INF/cpz/cpzmessage.jsp"),
		@Result(name = "cpzmessageSetting", location = "/WEB-INF/cpz/cpzmessageSetting.jsp"),
		@Result(name = "cpzmessageAdd", location = "/WEB-INF/cpz/cpzmessageAdd.jsp"),
		
	})
public class CpzMessageAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzMessageEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzMessageEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzMessageEntity  cpzmessage) {
		this.entity = cpzmessage;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int messageid =Integer.valueOf( StrutsParamUtils.getPraramValue("messageid", "0"));
request.setAttribute("messageid", messageid);
		return "cpzmessage";
	}
	//消息表列表
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
	String messageid = StrutsParamUtils.getPraramValue("messageid", "");
		if(StringUtils.isBlank(messageid)||"0".equals(messageid)){
			//return;
		}else{
where+="messageid=? And ";
where2+="messageid=:messageid And ";
argslist.add(Integer.valueOf(messageid));
argsMap.put("messageid",Integer.valueOf( messageid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzMessageEntity  ");
		 sb = new StringBuffer(" select a from CpzMessageEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzMessageEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzMessageEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzMessageEntity> list = (List<CpzMessageEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzMessageAction!list?messageid="+messageid+"%26pageNo=",true);
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

//01：业务通知02：系统变更通知03：业务进展通知04：其它通知
List messagetypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	messagetypelist.add("01"+"-"+"业务通知");
	messagetypelist.add("02"+"-"+"系统变更通知");
	messagetypelist.add("03"+"-"+"业务进展通知");
	messagetypelist.add("04"+"-"+"其它通知");
//}
request.setAttribute("messagetypeSelectList",messagetypelist);
//页面数据
int messageid =Integer.valueOf( StrutsParamUtils.getPraramValue("messageid", "0"));
request.setAttribute("messageid", messageid);
		StringBuffer sb = new StringBuffer(" select a from CpzMessageEntity a  where messageid=:messageid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("messageid",messageid);
		List<CpzMessageEntity> list = (List<CpzMessageEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzMessageEntity) list.get(0));
			return "cpzmessageSetting";
		}else{
			return "cpzmessageAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzmessage";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//01：业务通知02：系统变更通知03：业务进展通知04：其它通知
List messagetypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	messagetypelist.add("01"+"-"+"业务通知");
	messagetypelist.add("02"+"-"+"系统变更通知");
	messagetypelist.add("03"+"-"+"业务进展通知");
	messagetypelist.add("04"+"-"+"其它通知");
//}
request.setAttribute("messagetypeSelectList",messagetypelist);
//页面数据
int messageid =Integer.valueOf( StrutsParamUtils.getPraramValue("messageid", "0"));
request.setAttribute("messageid", messageid);
			return "cpzmessageAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzMessageEntity a  where messageid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getMessageid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzmessage";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int messageid =Integer.valueOf( StrutsParamUtils.getPraramValue("messageid", "0"));
request.setAttribute("messageid", messageid);
CpzMessageEntity entity = new CpzMessageEntity();
entity.setMessageid(messageid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

