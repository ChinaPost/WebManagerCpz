<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家基本信息表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var userid='${userid}';
	$("#userid").val(userid);
	
});
	function doSave() {
	  
		if ($("#userid").val() == "") {
			alert("请输入会员号！");
			return false;
		}
		var userid=$('#userid').val();
		if(userid=="" || !/^\d+$/.test(userid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#moileno").val() == "") {
			alert("请输入手机号码！");
			return false;
		}
		if ($("#nickname").val() == "") {
			alert("请输入会员呢称！");
			return false;
		}
		if ($("#sex").val() == "") {
			alert("请输入性别0：女1：男！");
			return false;
		}
		if ($("#headurl").val() == "") {
			alert("请输入会员头像图片URL地址！");
			return false;
		}
		if ($("#cstmlevel").val() == "") {
			alert("请输入会员等级预留01：铜牌会员02：银牌会员03：金牌会员！");
			return false;
		}
		if ($("#lonvalue").val() == "") {
			alert("请输入注册时所在经度格式：小数点后2位！");
			return false;
		}
		if ($("#latvalue").val() == "") {
			alert("请输入注册时所在纬度格式：小数点后2位！");
			return false;
		}
		if ($("#createtime").val() == "") {
			alert("请输入注册时间格式：yyyymmdd hh24miss！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzBuyerMsgAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">买家基本信息表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="userid" name="entity.userid" value="${userid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>手机号码：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="moileno" name="entity.moileno" value="${ entity.moileno}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>会员呢称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="nickname" name="entity.nickname" value="${ entity.nickname}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">性别0：女1：男：
						</td>
						<td>
<select id="sex" name="entity.sex" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${sexSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
					<tr >
						<td align="right" style="width: 120px">会员头像图片URL地址：
						</td>
						<td>
							<input type="file" id="file1" name="file1"  />
								
						</td>
						
					</tr>
					<tr >
						<td align="right" style="width: 120px">会员等级预留01：铜牌会员02：银牌会员03：金牌会员：
						</td>
						<td>
<select id="cstmlevel" name="entity.cstmlevel" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${cstmlevelSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>注册时所在经度格式：小数点后2位：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="lonvalue" name="entity.lonvalue" value="${ entity.lonvalue}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>注册时所在纬度格式：小数点后2位：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="latvalue" name="entity.latvalue" value="${ entity.latvalue}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>注册时间格式：yyyymmdd hh24miss：</td>
					<td><input id="createtime" name="entity.createtime" value="${ entity.createtime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
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

