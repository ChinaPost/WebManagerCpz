package com.cpz.action;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.cpz.entity.CpzBuyerMsgEntity;
import com.tools.CommonFunction;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//买家基本信息表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzBuyerMsgAction" ,results = { 
		@Result(name = "cpzbuyermsg", location = "/WEB-INF/cpz/cpzbuyermsg.jsp"),
		@Result(name = "cpzbuyermsgSetting", location = "/WEB-INF/cpz/cpzbuyermsgSetting.jsp"),
		@Result(name = "cpzbuyermsgAdd", location = "/WEB-INF/cpz/cpzbuyermsgAdd.jsp"),
		
	})
public class CpzBuyerMsgAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzBuyerMsgEntity entity;
    private File file1;//对应的就是表单中文件上传的那个输入域的名称，Struts2框架会封装成File类型的
    private String file1FileName;//   上传输入域FileName  文件名
    private String file1ContentType;// 上传文件的MIME类型
	public File getFile1() {
		return file1;
	}
	public void setFile1(File file1) {
		this.file1 = file1;
	}
	public String getFile1FileName() {
		return file1FileName;
	}
	public void setFile1FileName(String file1FileName) {
		this.file1FileName = file1FileName;
	}
	public String getFile1ContentType() {
		return file1ContentType;
	}
	public void setFile1ContentType(String file1ContentType) {
		this.file1ContentType = file1ContentType;
	}
	public ObjectDao getObjectDao() {
		return objectDao;
	}
	public void setObjectDao(ObjectDao objectDao) {
		this.objectDao = objectDao;
	}
	
	public CpzBuyerMsgEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzBuyerMsgEntity  cpzbuyermsg) {
		this.entity = cpzbuyermsg;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int userid =Integer.valueOf( StrutsParamUtils.getPraramValue("userid", "0"));
request.setAttribute("userid", userid);
		return "cpzbuyermsg";
	}
	//买家基本信息表列表
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
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzBuyerMsgEntity  ");
		 sb = new StringBuffer(" select a from CpzBuyerMsgEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzBuyerMsgEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzBuyerMsgEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzBuyerMsgEntity> list = (List<CpzBuyerMsgEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzBuyerMsgAction!list?userid="+userid+"%26pageNo=",true);
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

//性别0：女1：男
List sexlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	sexlist.add("0"+"-"+"女");
	sexlist.add("1"+"-"+"男");
//}
request.setAttribute("sexSelectList",sexlist);

//会员等级预留01：铜牌会员02：银牌会员03：金牌会员
List cstmlevellist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	cstmlevellist.add("01"+"-"+"铜牌会员");
	cstmlevellist.add("02"+"-"+"银牌会员");
	cstmlevellist.add("03"+"-"+"金牌会员");
//}
request.setAttribute("cstmlevelSelectList",cstmlevellist);
//页面数据
int userid =Integer.valueOf( StrutsParamUtils.getPraramValue("userid", "0"));
request.setAttribute("userid", userid);
		StringBuffer sb = new StringBuffer(" select a from CpzBuyerMsgEntity a  where userid=:userid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("userid",userid);
		List<CpzBuyerMsgEntity> list = (List<CpzBuyerMsgEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzBuyerMsgEntity) list.get(0));
			return "cpzbuyermsgSetting";
		}else{
			return "cpzbuyermsgAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
		  if(file1 != null){
		 ServletContext sc = ServletActionContext.getServletContext();
		       	 String activityBannerPath=CommonFunction.readDefVal("activityBannerPath");
		            String storePath = sc.getRealPath(activityBannerPath+entity.getUserid());
		            FileUtils.copyFile(file1, new File(storePath,"headurl.jpg"));
		            entity.setHeadurl(activityBannerPath+entity.getUserid()+"/headurl.jpg");
		       }
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzbuyermsg";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//性别0：女1：男
List sexlist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	sexlist.add("0"+"-"+"女");
	sexlist.add("1"+"-"+"男");
//}
request.setAttribute("sexSelectList",sexlist);

//会员等级预留01：铜牌会员02：银牌会员03：金牌会员
List cstmlevellist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	cstmlevellist.add("01"+"-"+"铜牌会员");
	cstmlevellist.add("02"+"-"+"银牌会员");
	cstmlevellist.add("03"+"-"+"金牌会员");
//}
request.setAttribute("cstmlevelSelectList",cstmlevellist);
//页面数据
int userid =Integer.valueOf( StrutsParamUtils.getPraramValue("userid", "0"));
request.setAttribute("userid", userid);
			return "cpzbuyermsgAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
		  if(file1 != null){
		 ServletContext sc = ServletActionContext.getServletContext();
		       	 String activityBannerPath=CommonFunction.readDefVal("activityBannerPath");
		            String storePath = sc.getRealPath(""+activityBannerPath+entity.getUserid());
		            FileUtils.copyFile(file1, new File(storePath,"headurl.jpg"));
		            entity.setHeadurl(activityBannerPath+entity.getUserid()+"/headurl.jpg");
		       }
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzBuyerMsgEntity a  where userid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getUserid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzbuyermsg";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int userid =Integer.valueOf( StrutsParamUtils.getPraramValue("userid", "0"));
request.setAttribute("userid", userid);
CpzBuyerMsgEntity entity = new CpzBuyerMsgEntity();
entity.setUserid(userid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

