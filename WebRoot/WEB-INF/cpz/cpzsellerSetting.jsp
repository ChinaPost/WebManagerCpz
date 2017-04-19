<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>卖家表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var sellerid='${sellerid}';
	$("#sellerid").val(sellerid);
	var account='${entity.account}';
	$("#account").val(account);
	var mobile='${entity.mobile}';
	$("#mobile").val(mobile);
	var password='${entity.password}';
	$("#password").val(password);
	var status='${entity.status}';
	$("#status").val(status);
	
});
	function doSave() {
	  
		if ($("#sellerid").val() == "") {
			alert("请输入主键！");
			return false;
		}
		var sellerid=$('#sellerid').val();
		if(sellerid=="" || !/^\d+$/.test(sellerid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#account").val() == "") {
			alert("请输入账号！");
			return false;
		}
		if ($("#mobile").val() == "") {
			alert("请输入手机号码！");
			return false;
		}
		if ($("#password").val() == "") {
			alert("请输入密码！");
			return false;
		}
		if ($("#status").val() == "") {
			alert("请输入状态！");
			return false;
		}
		var status=$('#status').val();
		if(status=="" || !/^\d+$/.test(status)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzSellerAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">卖家表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="sellerid" name="entity.sellerid" value="${sellerid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>账号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="account" name="entity.account" value="${ entity.account}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>手机号码：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="mobile" name="entity.mobile" value="${ entity.mobile}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>密码：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="password" name="entity.password" value="${ entity.password}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>状态：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="status" name="entity.status" value="${ entity.status}"/></td>
					
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

