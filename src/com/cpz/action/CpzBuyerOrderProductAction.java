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

import com.cpz.entity.CpzBuyerOrderProductEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//订单关联商品表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzBuyerOrderProductAction" ,results = { 
		@Result(name = "cpzbuyerorderproduct", location = "/WEB-INF/cpz/cpzbuyerorderproduct.jsp"),
		@Result(name = "cpzbuyerorderproductSetting", location = "/WEB-INF/cpz/cpzbuyerorderproductSetting.jsp"),
		@Result(name = "cpzbuyerorderproductAdd", location = "/WEB-INF/cpz/cpzbuyerorderproductAdd.jsp"),
		
	})
public class CpzBuyerOrderProductAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzBuyerOrderProductEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzBuyerOrderProductEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzBuyerOrderProductEntity  cpzbuyerorderproduct) {
		this.entity = cpzbuyerorderproduct;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
String orderno = StrutsParamUtils.getPraramValue("orderno", "");
request.setAttribute("orderno", orderno);
int shopproductid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopproductid", "0"));
request.setAttribute("shopproductid", shopproductid);
		return "cpzbuyerorderproduct";
	}
	//订单关联商品表列表
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
argslist.add(orderno);
argsMap.put("orderno", orderno);
}
	String shopproductid = StrutsParamUtils.getPraramValue("shopproductid", "");
		if(StringUtils.isBlank(shopproductid)||"0".equals(shopproductid)){
			//return;
		}else{
where+="shopproductid=? And ";
where2+="shopproductid=:shopproductid And ";
argslist.add(Integer.valueOf(shopproductid));
argsMap.put("shopproductid",Integer.valueOf( shopproductid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzBuyerOrderProductEntity  ");
		 sb = new StringBuffer(" select a from CpzBuyerOrderProductEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzBuyerOrderProductEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzBuyerOrderProductEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzBuyerOrderProductEntity> list = (List<CpzBuyerOrderProductEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzBuyerOrderProductAction!list?orderno="+orderno+"%26shopproductid="+shopproductid+"%26pageNo=",true);
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

//商品类型0：单个商品:1：套餐
List producttypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	producttypelist.add("0"+"-"+"单个商品");
	producttypelist.add("1"+"-"+"套餐");
//}
request.setAttribute("producttypeSelectList",producttypelist);
//页面数据
String orderno = StrutsParamUtils.getPraramValue("orderno", "");
request.setAttribute("orderno", orderno);
int shopproductid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopproductid", "0"));
request.setAttribute("shopproductid", shopproductid);
		StringBuffer sb = new StringBuffer(" select a from CpzBuyerOrderProductEntity a  where orderno=:orderno And shopproductid=:shopproductid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("orderno",orderno);
argsMap.put("shopproductid",shopproductid);
		List<CpzBuyerOrderProductEntity> list = (List<CpzBuyerOrderProductEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzBuyerOrderProductEntity) list.get(0));
			return "cpzbuyerorderproductSetting";
		}else{
			return "cpzbuyerorderproductAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzbuyerorderproduct";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//商品类型0：单个商品:1：套餐
List producttypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	producttypelist.add("0"+"-"+"单个商品");
	producttypelist.add("1"+"-"+"套餐");
//}
request.setAttribute("producttypeSelectList",producttypelist);
//页面数据
String orderno = StrutsParamUtils.getPraramValue("orderno", "");
request.setAttribute("orderno", orderno);
int shopproductid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopproductid", "0"));
request.setAttribute("shopproductid", shopproductid);
			return "cpzbuyerorderproductAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzBuyerOrderProductEntity a  where orderno=? And shopproductid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getOrderno);
argslist.add(entity.getShopproductid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzbuyerorderproduct";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
String orderno = StrutsParamUtils.getPraramValue("orderno", "");
request.setAttribute("orderno", orderno);
int shopproductid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopproductid", "0"));
request.setAttribute("shopproductid", shopproductid);
CpzBuyerOrderProductEntity entity = new CpzBuyerOrderProductEntity();
entity.setOrderno(orderno);
entity.setShopproductid(shopproductid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

