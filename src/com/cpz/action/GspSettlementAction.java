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

import com.cpz.entity.GspSettlementEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//结算表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "GspSettlementAction" ,results = { 
		@Result(name = "gspsettlement", location = "/WEB-INF/cpz/gspsettlement.jsp"),
		@Result(name = "gspsettlementSetting", location = "/WEB-INF/cpz/gspsettlementSetting.jsp"),
		@Result(name = "gspsettlementAdd", location = "/WEB-INF/cpz/gspsettlementAdd.jsp"),
		
	})
public class GspSettlementAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private GspSettlementEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public GspSettlementEntity getEntity() {
		return entity;
	}
	public void setEntity(GspSettlementEntity  gspsettlement) {
		this.entity = gspsettlement;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int settid =Integer.valueOf( StrutsParamUtils.getPraramValue("settid", "0"));
request.setAttribute("settid", settid);
		return "gspsettlement";
	}
	//结算表列表
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
	String settid = StrutsParamUtils.getPraramValue("settid", "");
		if(StringUtils.isBlank(settid)||"0".equals(settid)){
			//return;
		}else{
where+="settid=? And ";
where2+="settid=:settid And ";
argslist.add(Integer.valueOf(settid));
argsMap.put("settid",Integer.valueOf( settid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from GspSettlementEntity  ");
		 sb = new StringBuffer(" select a from GspSettlementEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from GspSettlementEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from GspSettlementEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<GspSettlementEntity> list = (List<GspSettlementEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('GspSettlementAction!list?settid="+settid+"%26pageNo=",true);
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
int settid =Integer.valueOf( StrutsParamUtils.getPraramValue("settid", "0"));
request.setAttribute("settid", settid);
		StringBuffer sb = new StringBuffer(" select a from GspSettlementEntity a  where settid=:settid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("settid",settid);
		List<GspSettlementEntity> list = (List<GspSettlementEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (GspSettlementEntity) list.get(0));
			return "gspsettlementSetting";
		}else{
			return "gspsettlementAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "gspsettlement";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据
//页面数据
int settid =Integer.valueOf( StrutsParamUtils.getPraramValue("settid", "0"));
request.setAttribute("settid", settid);
			return "gspsettlementAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from GspSettlementEntity a  where settid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getSettid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "gspsettlement";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int settid =Integer.valueOf( StrutsParamUtils.getPraramValue("settid", "0"));
request.setAttribute("settid", settid);
GspSettlementEntity entity = new GspSettlementEntity();
entity.setSettid(settid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

