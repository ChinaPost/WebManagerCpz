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

import com.cpz.entity.CpzBuyerRefundEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//买家退款表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzBuyerRefundAction" ,results = { 
		@Result(name = "cpzbuyerrefund", location = "/WEB-INF/cpz/cpzbuyerrefund.jsp"),
		@Result(name = "cpzbuyerrefundSetting", location = "/WEB-INF/cpz/cpzbuyerrefundSetting.jsp"),
		@Result(name = "cpzbuyerrefundAdd", location = "/WEB-INF/cpz/cpzbuyerrefundAdd.jsp"),
		
	})
public class CpzBuyerRefundAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzBuyerRefundEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzBuyerRefundEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzBuyerRefundEntity  cpzbuyerrefund) {
		this.entity = cpzbuyerrefund;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int refundid =Integer.valueOf( StrutsParamUtils.getPraramValue("refundid", "0"));
request.setAttribute("refundid", refundid);
		return "cpzbuyerrefund";
	}
	//买家退款表列表
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
	String refundid = StrutsParamUtils.getPraramValue("refundid", "");
		if(StringUtils.isBlank(refundid)||"0".equals(refundid)){
			//return;
		}else{
where+="refundid=? And ";
where2+="refundid=:refundid And ";
argslist.add(Integer.valueOf(refundid));
argsMap.put("refundid",Integer.valueOf( refundid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzBuyerRefundEntity  ");
		 sb = new StringBuffer(" select a from CpzBuyerRefundEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzBuyerRefundEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzBuyerRefundEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzBuyerRefundEntity> list = (List<CpzBuyerRefundEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzBuyerRefundAction!list?refundid="+refundid+"%26pageNo=",true);
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

//是否与卖家已协商0：是1：否
List isconsultsellerlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	isconsultsellerlist.add("0"+"-"+"是");
	isconsultsellerlist.add("1"+"-"+"否");
//}
request.setAttribute("isconsultsellerSelectList",isconsultsellerlist);

//退款方式0：系统退款1：人工退款
List refundstylelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	refundstylelist.add("0"+"-"+"系统退款");
	refundstylelist.add("1"+"-"+"人工退款");
//}
request.setAttribute("refundstyleSelectList",refundstylelist);
//页面数据
int refundid =Integer.valueOf( StrutsParamUtils.getPraramValue("refundid", "0"));
request.setAttribute("refundid", refundid);
		StringBuffer sb = new StringBuffer(" select a from CpzBuyerRefundEntity a  where refundid=:refundid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("refundid",refundid);
		List<CpzBuyerRefundEntity> list = (List<CpzBuyerRefundEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzBuyerRefundEntity) list.get(0));
			return "cpzbuyerrefundSetting";
		}else{
			return "cpzbuyerrefundAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzbuyerrefund";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//是否与卖家已协商0：是1：否
List isconsultsellerlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	isconsultsellerlist.add("0"+"-"+"是");
	isconsultsellerlist.add("1"+"-"+"否");
//}
request.setAttribute("isconsultsellerSelectList",isconsultsellerlist);

//退款方式0：系统退款1：人工退款
List refundstylelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	refundstylelist.add("0"+"-"+"系统退款");
	refundstylelist.add("1"+"-"+"人工退款");
//}
request.setAttribute("refundstyleSelectList",refundstylelist);
//页面数据
int refundid =Integer.valueOf( StrutsParamUtils.getPraramValue("refundid", "0"));
request.setAttribute("refundid", refundid);
			return "cpzbuyerrefundAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzBuyerRefundEntity a  where refundid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getRefundid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzbuyerrefund";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int refundid =Integer.valueOf( StrutsParamUtils.getPraramValue("refundid", "0"));
request.setAttribute("refundid", refundid);
CpzBuyerRefundEntity entity = new CpzBuyerRefundEntity();
entity.setRefundid(refundid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

