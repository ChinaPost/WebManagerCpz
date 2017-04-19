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

import com.cpz.entity.CpzBuyerOrderEntity;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//买家订单表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzBuyerOrderAction" ,results = { 
		@Result(name = "cpzbuyerorder", location = "/WEB-INF/cpz/cpzbuyerorder.jsp"),
		@Result(name = "cpzbuyerorderSetting", location = "/WEB-INF/cpz/cpzbuyerorderSetting.jsp"),
		@Result(name = "cpzbuyerorderAdd", location = "/WEB-INF/cpz/cpzbuyerorderAdd.jsp"),
		
	})
public class CpzBuyerOrderAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzBuyerOrderEntity entity;
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzBuyerOrderEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzBuyerOrderEntity  cpzbuyerorder) {
		this.entity = cpzbuyerorder;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
String orderno = StrutsParamUtils.getPraramValue("orderno", "");
request.setAttribute("orderno", orderno);
		return "cpzbuyerorder";
	}
	//买家订单表列表
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
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzBuyerOrderEntity  ");
		 sb = new StringBuffer(" select a from CpzBuyerOrderEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzBuyerOrderEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzBuyerOrderEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzBuyerOrderEntity> list = (List<CpzBuyerOrderEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzBuyerOrderAction!list?orderno="+orderno+"%26pageNo=",true);
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

//订单状态01：待付款02：已付款03：等待发货04：已备货05：已确认收货06：已完成07：已取消08：退款中09：已退款10：退款拒绝
List orderstatuslist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	orderstatuslist.add("01"+"-"+"待付款");
	orderstatuslist.add("02"+"-"+"已付款");
	orderstatuslist.add("03"+"-"+"等待发货");
	orderstatuslist.add("04"+"-"+"已备货");
	orderstatuslist.add("05"+"-"+"已确认收货");
	orderstatuslist.add("06"+"-"+"已完成");
	orderstatuslist.add("07"+"-"+"已取消");
	orderstatuslist.add("08"+"-"+"退款中");
	orderstatuslist.add("09"+"-"+"已退款");
	orderstatuslist.add("10"+"-"+"退款拒绝");
//}
request.setAttribute("orderstatusSelectList",orderstatuslist);

//订单支付状态0：未支付1：支付中2：已支付
List paystatuslist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	paystatuslist.add("0"+"-"+"未支付");
	paystatuslist.add("1"+"-"+"支付中");
	paystatuslist.add("2"+"-"+"已支付");
//}
request.setAttribute("paystatusSelectList",paystatuslist);

//配送方式预留 01：自提02：寄递
List shiptypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	shiptypelist.add("01"+"-"+"自提");
	shiptypelist.add("02"+"-"+"寄递");
//}
request.setAttribute("shiptypeSelectList",shiptypelist);

//发票类型预留发票模块：目前阶段接口送0：不开发票 1：个人发票 2：单位发票
List invoicetypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	invoicetypelist.add("0"+"-"+"不开发票");
	invoicetypelist.add("1"+"-"+"个人发票");
	invoicetypelist.add("2"+"-"+"单位发票");
//}
request.setAttribute("invoicetypeSelectList",invoicetypelist);

//订单受理渠道01：微信02：安卓03：IOS
List channelnolist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	channelnolist.add("01"+"-"+"微信");
	channelnolist.add("02"+"-"+"安卓");
//}
request.setAttribute("channelnoSelectList",channelnolist);
//页面数据
String orderno = StrutsParamUtils.getPraramValue("orderno", "");
request.setAttribute("orderno", orderno);
		StringBuffer sb = new StringBuffer(" select a from CpzBuyerOrderEntity a  where orderno=:orderno  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("orderno",orderno);
		List<CpzBuyerOrderEntity> list = (List<CpzBuyerOrderEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzBuyerOrderEntity) list.get(0));
			return "cpzbuyerorderSetting";
		}else{
			return "cpzbuyerorderAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzbuyerorder";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//订单状态01：待付款02：已付款03：等待发货04：已备货05：已确认收货06：已完成07：已取消08：退款中09：已退款10：退款拒绝
List orderstatuslist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	orderstatuslist.add("01"+"-"+"待付款");
	orderstatuslist.add("02"+"-"+"已付款");
	orderstatuslist.add("03"+"-"+"等待发货");
	orderstatuslist.add("04"+"-"+"已备货");
	orderstatuslist.add("05"+"-"+"已确认收货");
	orderstatuslist.add("06"+"-"+"已完成");
	orderstatuslist.add("07"+"-"+"已取消");
	orderstatuslist.add("08"+"-"+"退款中");
	orderstatuslist.add("09"+"-"+"已退款");
	orderstatuslist.add("10"+"-"+"退款拒绝");
//}
request.setAttribute("orderstatusSelectList",orderstatuslist);

//订单支付状态0：未支付1：支付中2：已支付
List paystatuslist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	paystatuslist.add("0"+"-"+"未支付");
	paystatuslist.add("1"+"-"+"支付中");
	paystatuslist.add("2"+"-"+"已支付");
//}
request.setAttribute("paystatusSelectList",paystatuslist);

//配送方式预留 01：自提02：寄递
List shiptypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	shiptypelist.add("01"+"-"+"自提");
	shiptypelist.add("02"+"-"+"寄递");
//}
request.setAttribute("shiptypeSelectList",shiptypelist);

//发票类型预留发票模块：目前阶段接口送0：不开发票 1：个人发票 2：单位发票
List invoicetypelist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	invoicetypelist.add("0"+"-"+"不开发票");
	invoicetypelist.add("1"+"-"+"个人发票");
	invoicetypelist.add("2"+"-"+"单位发票");
//}
request.setAttribute("invoicetypeSelectList",invoicetypelist);

//订单受理渠道01：微信02：安卓03：IOS
List channelnolist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	channelnolist.add("01"+"-"+"微信");
	channelnolist.add("02"+"-"+"安卓");
//}
request.setAttribute("channelnoSelectList",channelnolist);
//页面数据
String orderno = StrutsParamUtils.getPraramValue("orderno", "");
request.setAttribute("orderno", orderno);
			return "cpzbuyerorderAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzBuyerOrderEntity a  where orderno=?   ");
List argslist = new ArrayList();
argslist.add(entity.getOrderno);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzbuyerorder";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
String orderno = StrutsParamUtils.getPraramValue("orderno", "");
request.setAttribute("orderno", orderno);
CpzBuyerOrderEntity entity = new CpzBuyerOrderEntity();
entity.setOrderno(orderno);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

