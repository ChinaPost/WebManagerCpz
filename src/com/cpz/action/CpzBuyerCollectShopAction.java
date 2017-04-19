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

import com.cpz.entity.CpzBuyerCollectShopEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//买家店铺收藏信息表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzBuyerCollectShopAction" ,results = { 
		@Result(name = "cpzbuyercollectshop", location = "/WEB-INF/cpz/cpzbuyercollectshop.jsp"),
		@Result(name = "cpzbuyercollectshopSetting", location = "/WEB-INF/cpz/cpzbuyercollectshopSetting.jsp"),
		@Result(name = "cpzbuyercollectshopAdd", location = "/WEB-INF/cpz/cpzbuyercollectshopAdd.jsp"),
		
	})
public class CpzBuyerCollectShopAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzBuyerCollectShopEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzBuyerCollectShopEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzBuyerCollectShopEntity  cpzbuyercollectshop) {
		this.entity = cpzbuyercollectshop;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int userid =Integer.valueOf( StrutsParamUtils.getPraramValue("userid", "0"));
request.setAttribute("userid", userid);
int shopid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopid", "0"));
request.setAttribute("shopid", shopid);
		return "cpzbuyercollectshop";
	}
	//买家店铺收藏信息表列表
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
	String userid = StrutsParamUtils.getPraramValue("userid", "");
		if(StringUtils.isBlank(userid)||"0".equals(userid)){
			//return;
		}else{
where+="userid=? And ";
where2+="userid=:userid And ";
argslist.add(Integer.valueOf(userid));
argsMap.put("userid",Integer.valueOf( userid));
}
	String shopid = StrutsParamUtils.getPraramValue("shopid", "");
		if(StringUtils.isBlank(shopid)||"0".equals(shopid)){
			//return;
		}else{
where+="shopid=? And ";
where2+="shopid=:shopid And ";
argslist.add(Integer.valueOf(shopid));
argsMap.put("shopid",Integer.valueOf( shopid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzBuyerCollectShopEntity  ");
		 sb = new StringBuffer(" select a from CpzBuyerCollectShopEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzBuyerCollectShopEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzBuyerCollectShopEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzBuyerCollectShopEntity> list = (List<CpzBuyerCollectShopEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzBuyerCollectShopAction!list?userid="+userid+"%26shopid="+shopid+"%26pageNo=",true);
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

//是否默认店铺0：否1：是
List isdefaultshoplist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	isdefaultshoplist.add("0"+"-"+"否");
	isdefaultshoplist.add("1"+"-"+"是");
//}
request.setAttribute("isdefaultshopSelectList",isdefaultshoplist);
//页面数据
int userid =Integer.valueOf( StrutsParamUtils.getPraramValue("userid", "0"));
request.setAttribute("userid", userid);
int shopid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopid", "0"));
request.setAttribute("shopid", shopid);
		StringBuffer sb = new StringBuffer(" select a from CpzBuyerCollectShopEntity a  where userid=:userid And shopid=:shopid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("userid",userid);
argsMap.put("shopid",shopid);
		List<CpzBuyerCollectShopEntity> list = (List<CpzBuyerCollectShopEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzBuyerCollectShopEntity) list.get(0));
			return "cpzbuyercollectshopSetting";
		}else{
			return "cpzbuyercollectshopAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzbuyercollectshop";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//是否默认店铺0：否1：是
List isdefaultshoplist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	isdefaultshoplist.add("0"+"-"+"否");
	isdefaultshoplist.add("1"+"-"+"是");
//}
request.setAttribute("isdefaultshopSelectList",isdefaultshoplist);
//页面数据
int userid =Integer.valueOf( StrutsParamUtils.getPraramValue("userid", "0"));
request.setAttribute("userid", userid);
int shopid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopid", "0"));
request.setAttribute("shopid", shopid);
			return "cpzbuyercollectshopAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzBuyerCollectShopEntity a  where userid=? And shopid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getUserid);
argslist.add(entity.getShopid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzbuyercollectshop";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int userid =Integer.valueOf( StrutsParamUtils.getPraramValue("userid", "0"));
request.setAttribute("userid", userid);
int shopid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopid", "0"));
request.setAttribute("shopid", shopid);
CpzBuyerCollectShopEntity entity = new CpzBuyerCollectShopEntity();
entity.setUserid(userid);
entity.setShopid(shopid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

