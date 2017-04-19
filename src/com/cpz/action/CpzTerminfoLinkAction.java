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

import com.cpz.entity.CpzTerminfoLinkEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//买家或者卖家关联第三方信息表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzTerminfoLinkAction" ,results = { 
		@Result(name = "cpzterminfolink", location = "/WEB-INF/cpz/cpzterminfolink.jsp"),
		@Result(name = "cpzterminfolinkSetting", location = "/WEB-INF/cpz/cpzterminfolinkSetting.jsp"),
		@Result(name = "cpzterminfolinkAdd", location = "/WEB-INF/cpz/cpzterminfolinkAdd.jsp"),
		
	})
public class CpzTerminfoLinkAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzTerminfoLinkEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzTerminfoLinkEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzTerminfoLinkEntity  cpzterminfolink) {
		this.entity = cpzterminfolink;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int systemno =Integer.valueOf( StrutsParamUtils.getPraramValue("systemno", "0"));
request.setAttribute("systemno", systemno);
		return "cpzterminfolink";
	}
	//买家或者卖家关联第三方信息表列表
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
	String systemno = StrutsParamUtils.getPraramValue("systemno", "");
		if(StringUtils.isBlank(systemno)||"0".equals(systemno)){
			//return;
		}else{
where+="systemno=? And ";
where2+="systemno=:systemno And ";
argslist.add(Integer.valueOf(systemno));
argsMap.put("systemno",Integer.valueOf( systemno));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzTerminfoLinkEntity  ");
		 sb = new StringBuffer(" select a from CpzTerminfoLinkEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzTerminfoLinkEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzTerminfoLinkEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzTerminfoLinkEntity> list = (List<CpzTerminfoLinkEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzTerminfoLinkAction!list?systemno="+systemno+"%26pageNo=",true);
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

//系统类别0:买家1：卖家2：批发商
List systemtyoelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	systemtyoelist.add("0"+"-"+"买家");
	systemtyoelist.add("1"+"-"+"卖家");
	systemtyoelist.add("2"+"-"+"批发商");
//}
request.setAttribute("systemtyoeSelectList",systemtyoelist);

//第三方代号类型0：微信OPENID1：安卓设备号2：IOS设备号
List linktypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	linktypelist.add("0"+"-"+"微信");
	linktypelist.add("1"+"-"+"安卓设备号");
	linktypelist.add("2"+"-"+"设备号");
//}
request.setAttribute("linktypeSelectList",linktypelist);
//页面数据
int systemno =Integer.valueOf( StrutsParamUtils.getPraramValue("systemno", "0"));
request.setAttribute("systemno", systemno);
		StringBuffer sb = new StringBuffer(" select a from CpzTerminfoLinkEntity a  where systemno=:systemno  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("systemno",systemno);
		List<CpzTerminfoLinkEntity> list = (List<CpzTerminfoLinkEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzTerminfoLinkEntity) list.get(0));
			return "cpzterminfolinkSetting";
		}else{
			return "cpzterminfolinkAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzterminfolink";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//系统类别0:买家1：卖家2：批发商
List systemtyoelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	systemtyoelist.add("0"+"-"+"买家");
	systemtyoelist.add("1"+"-"+"卖家");
	systemtyoelist.add("2"+"-"+"批发商");
//}
request.setAttribute("systemtyoeSelectList",systemtyoelist);

//第三方代号类型0：微信OPENID1：安卓设备号2：IOS设备号
List linktypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	linktypelist.add("0"+"-"+"微信");
	linktypelist.add("1"+"-"+"安卓设备号");
	linktypelist.add("2"+"-"+"设备号");
//}
request.setAttribute("linktypeSelectList",linktypelist);
//页面数据
int systemno =Integer.valueOf( StrutsParamUtils.getPraramValue("systemno", "0"));
request.setAttribute("systemno", systemno);
			return "cpzterminfolinkAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzTerminfoLinkEntity a  where systemno=?   ");
List argslist = new ArrayList();
argslist.add(entity.getSystemno);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzterminfolink";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int systemno =Integer.valueOf( StrutsParamUtils.getPraramValue("systemno", "0"));
request.setAttribute("systemno", systemno);
CpzTerminfoLinkEntity entity = new CpzTerminfoLinkEntity();
entity.setSystemno(systemno);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

