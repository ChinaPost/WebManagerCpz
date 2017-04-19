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

import com.cpz.entity.CpzSellerEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//卖家表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzSellerAction" ,results = { 
		@Result(name = "cpzseller", location = "/WEB-INF/cpz/cpzseller.jsp"),
		@Result(name = "cpzsellerSetting", location = "/WEB-INF/cpz/cpzsellerSetting.jsp"),
		@Result(name = "cpzsellerAdd", location = "/WEB-INF/cpz/cpzsellerAdd.jsp"),
		
	})
public class CpzSellerAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzSellerEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzSellerEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzSellerEntity  cpzseller) {
		this.entity = cpzseller;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int sellerid =Integer.valueOf( StrutsParamUtils.getPraramValue("sellerid", "0"));
request.setAttribute("sellerid", sellerid);
		return "cpzseller";
	}
	//卖家表列表
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
	String sellerid = StrutsParamUtils.getPraramValue("sellerid", "");
		if(StringUtils.isBlank(sellerid)||"0".equals(sellerid)){
			//return;
		}else{
where+="sellerid=? And ";
where2+="sellerid=:sellerid And ";
argslist.add(Integer.valueOf(sellerid));
argsMap.put("sellerid",Integer.valueOf( sellerid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzSellerEntity  ");
		 sb = new StringBuffer(" select a from CpzSellerEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzSellerEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzSellerEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzSellerEntity> list = (List<CpzSellerEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzSellerAction!list?sellerid="+sellerid+"%26pageNo=",true);
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
int sellerid =Integer.valueOf( StrutsParamUtils.getPraramValue("sellerid", "0"));
request.setAttribute("sellerid", sellerid);
		StringBuffer sb = new StringBuffer(" select a from CpzSellerEntity a  where sellerid=:sellerid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("sellerid",sellerid);
		List<CpzSellerEntity> list = (List<CpzSellerEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzSellerEntity) list.get(0));
			return "cpzsellerSetting";
		}else{
			return "cpzsellerAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzseller";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据
//页面数据
int sellerid =Integer.valueOf( StrutsParamUtils.getPraramValue("sellerid", "0"));
request.setAttribute("sellerid", sellerid);
			return "cpzsellerAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzSellerEntity a  where sellerid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getSellerid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzseller";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int sellerid =Integer.valueOf( StrutsParamUtils.getPraramValue("sellerid", "0"));
request.setAttribute("sellerid", sellerid);
CpzSellerEntity entity = new CpzSellerEntity();
entity.setSellerid(sellerid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

