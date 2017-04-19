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

import com.cpz.entity.CpzPlatProductEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//平台商品信息表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzPlatProductAction" ,results = { 
		@Result(name = "cpzplatproduct", location = "/WEB-INF/cpz/cpzplatproduct.jsp"),
		@Result(name = "cpzplatproductSetting", location = "/WEB-INF/cpz/cpzplatproductSetting.jsp"),
		@Result(name = "cpzplatproductAdd", location = "/WEB-INF/cpz/cpzplatproductAdd.jsp"),
		
	})
public class CpzPlatProductAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzPlatProductEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzPlatProductEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzPlatProductEntity  cpzplatproduct) {
		this.entity = cpzplatproduct;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int productid =Integer.valueOf( StrutsParamUtils.getPraramValue("productid", "0"));
request.setAttribute("productid", productid);
		return "cpzplatproduct";
	}
	//平台商品信息表列表
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
	String productid = StrutsParamUtils.getPraramValue("productid", "");
		if(StringUtils.isBlank(productid)||"0".equals(productid)){
			//return;
		}else{
where+="productid=? And ";
where2+="productid=:productid And ";
argslist.add(Integer.valueOf(productid));
argsMap.put("productid",Integer.valueOf( productid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzPlatProductEntity  ");
		 sb = new StringBuffer(" select a from CpzPlatProductEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzPlatProductEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzPlatProductEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzPlatProductEntity> list = (List<CpzPlatProductEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzPlatProductAction!list?productid="+productid+"%26pageNo=",true);
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

//商品类型0：单个商品1：套餐
List producttypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	producttypelist.add("0"+"-"+"单个商品");
	producttypelist.add("1"+"-"+"套餐");
//}
request.setAttribute("producttypeSelectList",producttypelist);

//商品状态0:上架1：下架
List productstatuslist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	productstatuslist.add("0"+"-"+"上架");
	productstatuslist.add("1"+"-"+"下架");
//}
request.setAttribute("productstatusSelectList",productstatuslist);

//交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶
List tradingunitlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	tradingunitlist.add("0"+"-"+"按斤");
	tradingunitlist.add("1"+"-"+"按块");
	tradingunitlist.add("2"+"-"+"按只");
	tradingunitlist.add("3"+"-"+"按支");
	tradingunitlist.add("4"+"-"+"按");
//}
request.setAttribute("tradingunitSelectList",tradingunitlist);

//是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？）
List isfreshlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	isfreshlist.add("0"+"-"+"否");
	isfreshlist.add("1"+"-"+"是");
//}
request.setAttribute("isfreshSelectList",isfreshlist);

//是否支持退货0：否 1：是
List iscanrefundlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	iscanrefundlist.add("0"+"-"+"否");
	iscanrefundlist.add("1"+"-"+"是");
//}
request.setAttribute("iscanrefundSelectList",iscanrefundlist);

//是否需要当场处理0：否1：是（是否支持当场处理？）
List isneedspotlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	isneedspotlist.add("0"+"-"+"否");
	isneedspotlist.add("1"+"-"+"是");
//}
request.setAttribute("isneedspotSelectList",isneedspotlist);
//页面数据
int productid =Integer.valueOf( StrutsParamUtils.getPraramValue("productid", "0"));
request.setAttribute("productid", productid);
		StringBuffer sb = new StringBuffer(" select a from CpzPlatProductEntity a  where productid=:productid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("productid",productid);
		List<CpzPlatProductEntity> list = (List<CpzPlatProductEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzPlatProductEntity) list.get(0));
			return "cpzplatproductSetting";
		}else{
			return "cpzplatproductAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzplatproduct";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//商品类型0：单个商品1：套餐
List producttypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	producttypelist.add("0"+"-"+"单个商品");
	producttypelist.add("1"+"-"+"套餐");
//}
request.setAttribute("producttypeSelectList",producttypelist);

//商品状态0:上架1：下架
List productstatuslist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	productstatuslist.add("0"+"-"+"上架");
	productstatuslist.add("1"+"-"+"下架");
//}
request.setAttribute("productstatusSelectList",productstatuslist);

//交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶
List tradingunitlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	tradingunitlist.add("0"+"-"+"按斤");
	tradingunitlist.add("1"+"-"+"按块");
	tradingunitlist.add("2"+"-"+"按只");
	tradingunitlist.add("3"+"-"+"按支");
	tradingunitlist.add("4"+"-"+"按");
//}
request.setAttribute("tradingunitSelectList",tradingunitlist);

//是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？）
List isfreshlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	isfreshlist.add("0"+"-"+"否");
	isfreshlist.add("1"+"-"+"是");
//}
request.setAttribute("isfreshSelectList",isfreshlist);

//是否支持退货0：否 1：是
List iscanrefundlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	iscanrefundlist.add("0"+"-"+"否");
	iscanrefundlist.add("1"+"-"+"是");
//}
request.setAttribute("iscanrefundSelectList",iscanrefundlist);

//是否需要当场处理0：否1：是（是否支持当场处理？）
List isneedspotlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	isneedspotlist.add("0"+"-"+"否");
	isneedspotlist.add("1"+"-"+"是");
//}
request.setAttribute("isneedspotSelectList",isneedspotlist);
//页面数据
int productid =Integer.valueOf( StrutsParamUtils.getPraramValue("productid", "0"));
request.setAttribute("productid", productid);
			return "cpzplatproductAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzPlatProductEntity a  where productid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getProductid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzplatproduct";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int productid =Integer.valueOf( StrutsParamUtils.getPraramValue("productid", "0"));
request.setAttribute("productid", productid);
CpzPlatProductEntity entity = new CpzPlatProductEntity();
entity.setProductid(productid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

