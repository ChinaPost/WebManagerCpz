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

import com.cpz.entity.CpzPlatProductNormsEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//平台商品规格表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzPlatProductNormsAction" ,results = { 
		@Result(name = "cpzplatproductnorms", location = "/WEB-INF/cpz/cpzplatproductnorms.jsp"),
		@Result(name = "cpzplatproductnormsSetting", location = "/WEB-INF/cpz/cpzplatproductnormsSetting.jsp"),
		@Result(name = "cpzplatproductnormsAdd", location = "/WEB-INF/cpz/cpzplatproductnormsAdd.jsp"),
		
	})
public class CpzPlatProductNormsAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzPlatProductNormsEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzPlatProductNormsEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzPlatProductNormsEntity  cpzplatproductnorms) {
		this.entity = cpzplatproductnorms;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int normsid =Integer.valueOf( StrutsParamUtils.getPraramValue("normsid", "0"));
request.setAttribute("normsid", normsid);
		return "cpzplatproductnorms";
	}
	//平台商品规格表列表
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
	String normsid = StrutsParamUtils.getPraramValue("normsid", "");
		if(StringUtils.isBlank(normsid)||"0".equals(normsid)){
			//return;
		}else{
where+="normsid=? And ";
where2+="normsid=:normsid And ";
argslist.add(Integer.valueOf(normsid));
argsMap.put("normsid",Integer.valueOf( normsid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzPlatProductNormsEntity  ");
		 sb = new StringBuffer(" select a from CpzPlatProductNormsEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzPlatProductNormsEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzPlatProductNormsEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzPlatProductNormsEntity> list = (List<CpzPlatProductNormsEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzPlatProductNormsAction!list?normsid="+normsid+"%26pageNo=",true);
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
int normsid =Integer.valueOf( StrutsParamUtils.getPraramValue("normsid", "0"));
request.setAttribute("normsid", normsid);
		StringBuffer sb = new StringBuffer(" select a from CpzPlatProductNormsEntity a  where normsid=:normsid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("normsid",normsid);
		List<CpzPlatProductNormsEntity> list = (List<CpzPlatProductNormsEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzPlatProductNormsEntity) list.get(0));
			return "cpzplatproductnormsSetting";
		}else{
			return "cpzplatproductnormsAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzplatproductnorms";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据
//页面数据
int normsid =Integer.valueOf( StrutsParamUtils.getPraramValue("normsid", "0"));
request.setAttribute("normsid", normsid);
			return "cpzplatproductnormsAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzPlatProductNormsEntity a  where normsid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getNormsid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzplatproductnorms";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int normsid =Integer.valueOf( StrutsParamUtils.getPraramValue("normsid", "0"));
request.setAttribute("normsid", normsid);
CpzPlatProductNormsEntity entity = new CpzPlatProductNormsEntity();
entity.setNormsid(normsid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

