<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>平台商品推荐表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var id='${id}';
	$("#id").val(id);
	var productid='${entity.productid}';
	$("#productid").val(productid);
	var channelno='${entity.channelno}';
	$("#channelno").val(channelno);
	var saletype='${entity.saletype}';
	$("#saletype").val(saletype);
	var starttime='${entity.starttime}';
	$("#starttime").val(starttime);
	var endtime='${entity.endtime}';
	$("#endtime").val(endtime);
	var createtime='${entity.createtime}';
	$("#createtime").val(createtime);
	var remark1='${entity.remark1}';
	$("#remark1").val(remark1);
	
});
	function doSave() {
	  
		if ($("#id").val() == "") {
			alert("请输入Id！");
			return false;
		}
		var id=$('#id').val();
		if(id=="" || !/^\d+$/.test(id)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#productid").val() == "") {
			alert("请输入商品代号！");
			return false;
		}
		var productid=$('#productid').val();
		if(productid=="" || !/^\d+$/.test(productid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#channelno").val() == "") {
			alert("请输入推荐渠道！");
			return false;
		}
		if ($("#saletype").val() == "") {
			alert("请输入推荐标识0：推荐1：热门！");
			return false;
		}
		if ($("#starttime").val() == "") {
			alert("请输入有效开始时间格式：yyyymmdd hh24miss！");
			return false;
		}
		if($("#end").val()!="" && $("#starttime").val() > $("#end").val()){
				alert("开始时间不能大于结束时间");
				return ;
		}
		if ($("#endtime").val() == "") {
			alert("请输入有效结束时间格式：yyyymmdd hh24miss！");
			return false;
		}
		if ($("#createtime").val() == "") {
			alert("请输入记录创建时间格式：yyyymmdd hh24miss！");
			return false;
		}
		if ($("#remark1").val() == "") {
			alert("请输入备注！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzPlatProductRecommendAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">平台商品推荐表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="id" name="entity.id" value="${id}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productid" name="entity.productid" value="${ entity.productid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>推荐渠道：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="channelno" name="entity.channelno" value="${ entity.channelno}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">推荐标识0：推荐1：热门：
						</td>
						<td>
<select id="saletype" name="entity.saletype" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${saletypeSelectList}">	
						<c:choose>
							<c:when test="${item==entity.saletype}">								
								<option value='${fn:substringBefore(item,"-")}' selected="selected">${fn:substringAfter(item,"-")} </option>
							</c:when>
							<c:otherwise>
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
							</c:otherwise>
						</c:choose>		  				
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>有效开始时间格式：yyyymmdd hh24miss：</td>
					<td><input id="starttime" name="entity.starttime" value="${ entity.starttime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>有效结束时间格式：yyyymmdd hh24miss：</td>
					<td><input id="endtime" name="entity.endtime" value="${ entity.endtime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>记录创建时间格式：yyyymmdd hh24miss：</td>
					<td><input id="createtime" name="entity.createtime" value="${ entity.createtime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>备注：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="remark1" name="entity.remark1" value="${ entity.remark1}"/></td>
					
				</tr>
				<tr height="60px">
					<td align="right" style="width: 120px"></td>
					<td><input type="button" value="保存" name="btn"
						onmouseover="this.style.cursor='hand'" class="subBtn"
						onclick="doSave()"> <input type="button" value="返回"
						name="btn2" onmouseover="this.style.cursor='hand'" class="subBtn"
						onclick="history.go(-1)">
				</tr>
			</tbody>
		</table>
	</div>
	</form>
</body>
</html>

