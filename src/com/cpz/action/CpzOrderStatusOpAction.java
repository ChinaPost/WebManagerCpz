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

import com.cpz.entity.CpzOrderStatusOpEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//订单状态操作表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzOrderStatusOpAction" ,results = { 
		@Result(name = "cpzorderstatusop", location = "/WEB-INF/cpz/cpzorderstatusop.jsp"),
		@Result(name = "cpzorderstatusopSetting", location = "/WEB-INF/cpz/cpzorderstatusopSetting.jsp"),
		@Result(name = "cpzorderstatusopAdd", location = "/WEB-INF/cpz/cpzorderstatusopAdd.jsp"),
		
	})
public class CpzOrderStatusOpAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzOrderStatusOpEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzOrderStatusOpEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzOrderStatusOpEntity  cpzorderstatusop) {
		this.entity = cpzorderstatusop;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int orderno =Integer.valueOf( StrutsParamUtils.getPraramValue("orderno", "0"));
request.setAttribute("orderno", orderno);
		return "cpzorderstatusop";
	}
	//订单状态操作表列表
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
	String orderno = StrutsParamUtils.getPraramValue("orderno", "");
		if(StringUtils.isBlank(orderno)||"0".equals(orderno)){
			//return;
		}else{
where+="orderno=? And ";
where2+="orderno=:orderno And ";
argslist.add(Integer.valueOf(orderno));
argsMap.put("orderno",Integer.valueOf( orderno));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzOrderStatusOpEntity  ");
		 sb = new StringBuffer(" select a from CpzOrderStatusOpEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzOrderStatusOpEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzOrderStatusOpEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzOrderStatusOpEntity> list = (List<CpzOrderStatusOpEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzOrderStatusOpAction!list?orderno="+orderno+"%26pageNo=",true);
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

//可操作标志01：支付02：订单取消 03：配货取消 04：退款
List opfalglist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	opfalglist.add("01"+"-"+"支付");
	opfalglist.add("02"+"-"+"订单取消");
	opfalglist.add("03"+"-"+"配货取消");
	opfalglist.add("04"+"-"+"退款");
//}
request.setAttribute("opfalgSelectList",opfalglist);
//页面数据
int orderno =Integer.valueOf( StrutsParamUtils.getPraramValue("orderno", "0"));
request.setAttribute("orderno", orderno);
		StringBuffer sb = new StringBuffer(" select a from CpzOrderStatusOpEntity a  where orderno=:orderno  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("orderno",orderno);
		List<CpzOrderStatusOpEntity> list = (List<CpzOrderStatusOpEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzOrderStatusOpEntity) list.get(0));
			return "cpzorderstatusopSetting";
		}else{
			return "cpzorderstatusopAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzorderstatusop";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//可操作标志01：支付02：订单取消 03：配货取消 04：退款
List opfalglist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	opfalglist.add("01"+"-"+"支付");
	opfalglist.add("02"+"-"+"订单取消");
	opfalglist.add("03"+"-"+"配货取消");
	opfalglist.add("04"+"-"+"退款");
//}
request.setAttribute("opfalgSelectList",opfalglist);
//页面数据
int orderno =Integer.valueOf( StrutsParamUtils.getPraramValue("orderno", "0"));
request.setAttribute("orderno", orderno);
			return "cpzorderstatusopAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzOrderStatusOpEntity a  where orderno=?   ");
List argslist = new ArrayList();
argslist.add(entity.getOrderno);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzorderstatusop";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int orderno =Integer.valueOf( StrutsParamUtils.getPraramValue("orderno", "0"));
request.setAttribute("orderno", orderno);
CpzOrderStatusOpEntity entity = new CpzOrderStatusOpEntity();
entity.setOrderno(orderno);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

