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

import com.cpz.entity.CpzProductLevelEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//商品分类表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzProductLevelAction" ,results = { 
		@Result(name = "cpzproductlevel", location = "/WEB-INF/cpz/cpzproductlevel.jsp"),
		@Result(name = "cpzproductlevelSetting", location = "/WEB-INF/cpz/cpzproductlevelSetting.jsp"),
		@Result(name = "cpzproductlevelAdd", location = "/WEB-INF/cpz/cpzproductlevelAdd.jsp"),
		
	})
public class CpzProductLevelAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzProductLevelEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzProductLevelEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzProductLevelEntity  cpzproductlevel) {
		this.entity = cpzproductlevel;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int productcategoryid =Integer.valueOf( StrutsParamUtils.getPraramValue("productcategoryid", "0"));
request.setAttribute("productcategoryid", productcategoryid);
		return "cpzproductlevel";
	}
	//商品分类表列表
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
	String productcategoryid = StrutsParamUtils.getPraramValue("productcategoryid", "");
		if(StringUtils.isBlank(productcategoryid)||"0".equals(productcategoryid)){
			//return;
		}else{
where+="productcategoryid=? And ";
where2+="productcategoryid=:productcategoryid And ";
argslist.add(Integer.valueOf(productcategoryid));
argsMap.put("productcategoryid",Integer.valueOf( productcategoryid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzProductLevelEntity  ");
		 sb = new StringBuffer(" select a from CpzProductLevelEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzProductLevelEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzProductLevelEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzProductLevelEntity> list = (List<CpzProductLevelEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzProductLevelAction!list?productcategoryid="+productcategoryid+"%26pageNo=",true);
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

//级别等级0：根级别1：第1级2：第2级
List productcategorygradelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	productcategorygradelist.add("0"+"-"+"根级别");
	productcategorygradelist.add("1"+"-"+"第");
	productcategorygradelist.add("1"+"-"+"第");
//}
request.setAttribute("productcategorygradeSelectList",productcategorygradelist);
//页面数据
int productcategoryid =Integer.valueOf( StrutsParamUtils.getPraramValue("productcategoryid", "0"));
request.setAttribute("productcategoryid", productcategoryid);
		StringBuffer sb = new StringBuffer(" select a from CpzProductLevelEntity a  where productcategoryid=:productcategoryid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("productcategoryid",productcategoryid);
		List<CpzProductLevelEntity> list = (List<CpzProductLevelEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzProductLevelEntity) list.get(0));
			return "cpzproductlevelSetting";
		}else{
			return "cpzproductlevelAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzproductlevel";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//级别等级0：根级别1：第1级2：第2级
List productcategorygradelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	productcategorygradelist.add("0"+"-"+"根级别");
	productcategorygradelist.add("1"+"-"+"第");
	productcategorygradelist.add("1"+"-"+"第");
//}
request.setAttribute("productcategorygradeSelectList",productcategorygradelist);
//页面数据
int productcategoryid =Integer.valueOf( StrutsParamUtils.getPraramValue("productcategoryid", "0"));
request.setAttribute("productcategoryid", productcategoryid);
			return "cpzproductlevelAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzProductLevelEntity a  where productcategoryid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getProductcategoryid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzproductlevel";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int productcategoryid =Integer.valueOf( StrutsParamUtils.getPraramValue("productcategoryid", "0"));
request.setAttribute("productcategoryid", productcategoryid);
CpzProductLevelEntity entity = new CpzProductLevelEntity();
entity.setProductcategoryid(productcategoryid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

