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

import com.cpz.entity.CpzPlatProductPicEntity;
import com.tools.CommonFunction;
import com.tools.PaginationUtil;
import com.tools.StrutsParamUtils;
import com.tools.hibernate.ObjectDao;
//平台商品图片表
//@SuppressWarnings("unchecked")
//@Namespace(value = "/cpz")
@Action(value = "CpzPlatProductPicAction" ,results = { 
		@Result(name = "cpzplatproductpic", location = "/WEB-INF/cpz/cpzplatproductpic.jsp"),
		@Result(name = "cpzplatproductpicSetting", location = "/WEB-INF/cpz/cpzplatproductpicSetting.jsp"),
		@Result(name = "cpzplatproductpicAdd", location = "/WEB-INF/cpz/cpzplatproductpicAdd.jsp"),
		
	})
public class CpzPlatProductPicAction {
	
	@Resource
	private ObjectDao objectDao;
	
    private CpzPlatProductPicEntity entity;
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
	
	public CpzPlatProductPicEntity getEntity() {
		return entity;
	}
	public void setEntity(CpzPlatProductPicEntity  cpzplatproductpic) {
		this.entity = cpzplatproductpic;
	}
	
	public String index(){
		HttpServletRequest request = StrutsParamUtils.getRequest();
int productid =Integer.valueOf( StrutsParamUtils.getPraramValue("productid", "0"));
request.setAttribute("productid", productid);
		return "cpzplatproductpic";
	}
	//平台商品图片表列表
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
	String productid = StrutsParamUtils.getPraramValue("productid", "");
		if(StringUtils.isBlank(productid)||"0".equals(productid)){
			//return;
		}else{
where+="productid=? And ";
where2+="productid=:productid And ";
argslist.add(Integer.valueOf(productid));
argsMap.put("productid",Integer.valueOf( productid));
}
StringBuffer sql=null;
StringBuffer sb=null;
if(where.length()==0)
{
		 sql = new StringBuffer("select count(*) from CpzPlatProductPicEntity  ");
		 sb = new StringBuffer(" select a from CpzPlatProductPicEntity a   ");
	}else{
		 sql = new StringBuffer("select count(*) from CpzPlatProductPicEntity where "+where.substring(0,where.lastIndexOf("And")));
		 sb = new StringBuffer(" select a from CpzPlatProductPicEntity a  where "+where2.substring(0,where2.lastIndexOf("And")));
	}
 Object[] args = (Object[])argslist.toArray();
	//	sb.append(" order by activity_code desc");
		int count = objectDao.countObjectByHql(sql.toString(),args);
		List<CpzPlatProductPicEntity> list = (List<CpzPlatProductPicEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				(Integer.parseInt(pageNo) - 1) * Integer.parseInt(pageSize),
				Integer.parseInt(pageSize));
		String pageString = PaginationUtil.getPaginationHtml(
				Integer.valueOf(count), Integer.valueOf(pageSize),
				Integer.valueOf(pageNo), Integer.valueOf(2),
				Integer.valueOf(5),
				"javascript:getAll('CpzPlatProductPicAction!list?productid="+productid+"%26pageNo=",true);
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

//图片规格0:大1：中2：小
List picnormslist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	picnormslist.add("0"+"-"+"大");
	picnormslist.add("1"+"-"+"中");
	picnormslist.add("2"+"-"+"小");
//}
request.setAttribute("picnormsSelectList",picnormslist);
//页面数据
int productid =Integer.valueOf( StrutsParamUtils.getPraramValue("productid", "0"));
request.setAttribute("productid", productid);
		StringBuffer sb = new StringBuffer(" select a from CpzPlatProductPicEntity a  where productid=:productid  ");

Map<String, Object> argsMap=new HashMap<String, Object>();
argsMap.put("productid",productid);
		List<CpzPlatProductPicEntity> list = (List<CpzPlatProductPicEntity>) objectDao.findByHqlPage(
				sb.toString(),argsMap,
				0,
				10);
		if (list!=null && list.size()==1) {
				request.setAttribute("entity", (CpzPlatProductPicEntity) list.get(0));
			return "cpzplatproductpicSetting";
		}else{
			return "cpzplatproductpicAdd";
		}
		
	}
	public String doUpdate() throws IOException {
		HttpServletRequest request = StrutsParamUtils.getRequest();
		  if(file1 != null){
		 ServletContext sc = ServletActionContext.getServletContext();
		       	 String activityBannerPath=CommonFunction.readDefVal("activityBannerPath");
		            String storePath = sc.getRealPath(activityBannerPath+entity.getProductid());
		            FileUtils.copyFile(file1, new File(storePath,"picurl.jpg"));
		            entity.setPicurl(activityBannerPath+entity.getProductid()+"/picurl.jpg");
		       }
	  //批量删除    //  List  objectList = objectDao.findByProperty("MerchMsg", "belong_activity", activityInfo.getActivity_code() );
	       // objectDao.deleteAll(objectList);
			objectDao.saveOrUpdate(entity);//form表单提交过来的对象
			return "cpzplatproductpic";
	}
//跳到新增页
	public String toAdd() {
		HttpServletRequest request = StrutsParamUtils.getRequest();
//选择器数据

//图片规格0:大1：中2：小
List picnormslist=new ArrayList();
//for(int i=0;i<list.size();i++)
//{
	picnormslist.add("0"+"-"+"大");
	picnormslist.add("1"+"-"+"中");
	picnormslist.add("2"+"-"+"小");
//}
request.setAttribute("picnormsSelectList",picnormslist);
//页面数据
int productid =Integer.valueOf( StrutsParamUtils.getPraramValue("productid", "0"));
request.setAttribute("productid", productid);
			return "cpzplatproductpicAdd";
		
	}
	
	public String doAdd()  throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
		  if(file1 != null){
		 ServletContext sc = ServletActionContext.getServletContext();
		       	 String activityBannerPath=CommonFunction.readDefVal("activityBannerPath");
		            String storePath = sc.getRealPath(activityBannerPath+entity.getProductid());
		            FileUtils.copyFile(file1, new File(storePath,"picurl.jpg"));
		            entity.setPicurl(activityBannerPath+entity.getProductid()+"/picurl.jpg");
		       }
//id 当前最大值加一
/*{
StringBuffer sb = new StringBuffer(
" select max(a.) from CpzPlatProductPicEntity a  where productid=?   ");
List argslist = new ArrayList();
argslist.add(entity.getProductid);
List list = (List) objectDao
		.findByHql(sb.toString(), argslist.toArray());
int max=0;
if(list!=null && list.size()>0){
 max=Integer.valueOf((String)list.get(0));
}
entity.setnull(StrutsParamUtils.beforeAppend0(max+1+"")+"");
		}*/
		objectDao.save(entity);//form表单提交过来的对象
		return "cpzplatproductpic";
	}
	
	public void doDelete() throws IOException{
		HttpServletRequest request = StrutsParamUtils.getRequest();
int productid =Integer.valueOf( StrutsParamUtils.getPraramValue("productid", "0"));
request.setAttribute("productid", productid);
CpzPlatProductPicEntity entity = new CpzPlatProductPicEntity();
entity.setProductid(productid);
		objectDao.delete(entity);
		StrutsParamUtils.getResponse().getWriter().write("success");
		return ;
	}
	
}

