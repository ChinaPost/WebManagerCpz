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

import com.cpz.entity.CpzBuyerLogEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//订单或者退款单日志表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzBuyerLogAction" ,results = { 
		@Result(name = "cpzbuyerlog", location = "/WEB-INF/cpz/cpzbuyerlog.jsp"),
		@Result(name = "cpzbuyerlogSetting", location = "/WEB-INF/cpz/cpzbuyerlogSetting.jsp"),
		@Result(name = "cpzbuyerlogAdd", location = "/WEB-INF/cpz/cpzbuyerlogAdd.jsp"),
		
	})
public class CpzBuyerLogAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzBuyerLogEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzBuyerLogEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzBuyerLogEntity  cpzbuyerlog) {
		this.entity = cpzbuyerlog;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int orderlogid =Integer.valueOf( StrutsParamUtils.getPraramValue("orderlogid", "0"));
request.setAttribute("orderlogid", orderlogid);
		return "cpzbuyerlog";
	}
	//订单或者退款单日志表列表
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
	String orderlogid = StrutsParamUtils.getPraramValue("orderlogid", "");
		if(StringUtils.isBlank(orderlogid)||"0".equals(orderlogid)){
			//return;
		}else{
where+="orderlogid=? And ";
where2+="orderlogid=:orderlogid And ";
argslist.add(Integer.valueOf(orderlogid));
argsMap.put("orderlogid",Integer.valueOf( orderlogid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzBuyerLogEntity  ");
		 sb = new StringBuffer(" select a from CpzBuyerLogEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzBuyerLogEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzBuyerLogEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzBuyerLogEntity> list = (List<CpzBuyerLogEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzBuyerLogAction!list?orderlogid="+orderlogid+"%26pageNo=",true);
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

//处理类型0：下单 1：支付 2：配货 3：配货取消 4：取货 5：退款申请6：退款完成
List dealtypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	dealtypelist.add("0"+"-"+"下单");
	dealtypelist.add("1"+"-"+"支付");
	dealtypelist.add("2"+"-"+"配货");
	dealtypelist.add("3"+"-"+"配货取消");
	dealtypelist.add("4"+"-"+"取货");
	dealtypelist.add("5"+"-"+"退款申请");
	dealtypelist.add("6"+"-"+"退款完成");
//}
request.setAttribute("dealtypeSelectList",dealtypelist);
//页面数据
int orderlogid =Integer.valueOf( StrutsParamUtils.getPraramValue("orderlogid", "0"));
request.setAttribute("orderlogid", orderlogid);
		StringBuffer sb = new StringBuffer(" select a from CpzBuyerLogEntity a  where orderlogid=:orderlogid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("orderlogid",orderlogid);
		List<CpzBuyerLogEntity> list = (List<CpzBuyerLogEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzBuyerLogEntity) list.get(0));
			return "cpzbuyerlogSetting";
		}else{
			return "cpzbuyerlogAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzbuyerlog";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//处理类型0：下单 1：支付 2：配货 3：配货取消 4：取货 5：退款申请6：退款完成
List dealtypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	dealtypelist.add("0"+"-"+"下单");
	dealtypelist.add("1"+"-"+"支付");
	dealtypelist.add("2"+"-"+"配货");
	dealtypelist.add("3"+"-"+"配货取消");
	dealtypelist.add("4"+"-"+"取货");
	dealtypelist.add("5"+"-"+"退款申请");
	dealtypelist.add("6"+"-"+"退款完成");
//}
request.setAttribute("dealtypeSelectList",dealtypelist);
//页面数据
int orderlogid =Integer.valueOf( StrutsParamUtils.getPraramValue("orderlogid", "0"));
request.setAttribute("orderlogid", orderlogid);
			return "cpzbuyerlogAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzBuyerLogEntity a  where orderlogid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getOrderlogid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzbuyerlog";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int orderlogid =Integer.valueOf( StrutsParamUtils.getPraramValue("orderlogid", "0"));
request.setAttribute("orderlogid", orderlogid);
CpzBuyerLogEntity entity = new CpzBuyerLogEntity();
entity.setOrderlogid(orderlogid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

