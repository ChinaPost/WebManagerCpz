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

import com.cpz.entity.CpzSellerProductEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//卖家商品信息表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzSellerProductAction" ,results = { 
		@Result(name = "cpzsellerproduct", location = "/WEB-INF/cpz/cpzsellerproduct.jsp"),
		@Result(name = "cpzsellerproductSetting", location = "/WEB-INF/cpz/cpzsellerproductSetting.jsp"),
		@Result(name = "cpzsellerproductAdd", location = "/WEB-INF/cpz/cpzsellerproductAdd.jsp"),
		
	})
public class CpzSellerProductAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzSellerProductEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzSellerProductEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzSellerProductEntity  cpzsellerproduct) {
		this.entity = cpzsellerproduct;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int shopproductid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopproductid", "0"));
request.setAttribute("shopproductid", shopproductid);
		return "cpzsellerproduct";
	}
	//卖家商品信息表列表
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
		 sql = new StringBuffer("select count(*) from CpzSellerProductEntity  ");
		 sb = new StringBuffer(" select a from CpzSellerProductEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzSellerProductEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzSellerProductEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzSellerProductEntity> list = (List<CpzSellerProductEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzSellerProductAction!list?shopproductid="+shopproductid+"%26pageNo=",true);
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
int shopproductid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopproductid", "0"));
request.setAttribute("shopproductid", shopproductid);
		StringBuffer sb = new StringBuffer(" select a from CpzSellerProductEntity a  where shopproductid=:shopproductid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("shopproductid",shopproductid);
		List<CpzSellerProductEntity> list = (List<CpzSellerProductEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzSellerProductEntity) list.get(0));
			return "cpzsellerproductSetting";
		}else{
			return "cpzsellerproductAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzsellerproduct";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据
//页面数据
int shopproductid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopproductid", "0"));
request.setAttribute("shopproductid", shopproductid);
			return "cpzsellerproductAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzSellerProductEntity a  where shopproductid=?   ");
List argslist = new ArrayList();
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
		return "cpzsellerproduct";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int shopproductid =Integer.valueOf( StrutsParamUtils.getPraramValue("shopproductid", "0"));
request.setAttribute("shopproductid", shopproductid);
CpzSellerProductEntity entity = new CpzSellerProductEntity();
entity.setShopproductid(shopproductid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

