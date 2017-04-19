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

import com.cpz.entity.GspProductNormsMapEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//商品规格关联表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "GspProductNormsMapAction" ,results = { 
		@Result(name = "gspproductnormsmap", location = "/WEB-INF/cpz/gspproductnormsmap.jsp"),
		@Result(name = "gspproductnormsmapSetting", location = "/WEB-INF/cpz/gspproductnormsmapSetting.jsp"),
		@Result(name = "gspproductnormsmapAdd", location = "/WEB-INF/cpz/gspproductnormsmapAdd.jsp"),
		
	})
public class GspProductNormsMapAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private GspProductNormsMapEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public GspProductNormsMapEntity getEntity() {
		return entity;
	}
	public void setEntity(GspProductNormsMapEntity  gspproductnormsmap) {
		this.entity = gspproductnormsmap;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int id =Integer.valueOf( StrutsParamUtils.getPraramValue("id", "0"));
request.setAttribute("id", id);
		return "gspproductnormsmap";
	}
	//商品规格关联表列表
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
	String id = StrutsParamUtils.getPraramValue("id", "");
		if(StringUtils.isBlank(id)||"0".equals(id)){
			//return;
		}else{
where+="id=? And ";
where2+="id=:id And ";
argslist.add(Integer.valueOf(id));
argsMap.put("id",Integer.valueOf( id));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from GspProductNormsMapEntity  ");
		 sb = new StringBuffer(" select a from GspProductNormsMapEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from GspProductNormsMapEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from GspProductNormsMapEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<GspProductNormsMapEntity> list = (List<GspProductNormsMapEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('GspProductNormsMapAction!list?id="+id+"%26pageNo=",true);
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
int id =Integer.valueOf( StrutsParamUtils.getPraramValue("id", "0"));
request.setAttribute("id", id);
		StringBuffer sb = new StringBuffer(" select a from GspProductNormsMapEntity a  where id=:id  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("id",id);
		List<GspProductNormsMapEntity> list = (List<GspProductNormsMapEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (GspProductNormsMapEntity) list.get(0));
			return "gspproductnormsmapSetting";
		}else{
			return "gspproductnormsmapAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "gspproductnormsmap";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据
//页面数据
int id =Integer.valueOf( StrutsParamUtils.getPraramValue("id", "0"));
request.setAttribute("id", id);
			return "gspproductnormsmapAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from GspProductNormsMapEntity a  where id=?   ");
List argslist = new ArrayList();
argslist.add(entity.getId);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "gspproductnormsmap";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int id =Integer.valueOf( StrutsParamUtils.getPraramValue("id", "0"));
request.setAttribute("id", id);
GspProductNormsMapEntity entity = new GspProductNormsMapEntity();
entity.setId(id);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

