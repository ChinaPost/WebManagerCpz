<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>结算表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var settid='${settid}';
	$("#settid").val(settid);
	var orderno='${entity.orderno}';
	$("#orderno").val(orderno);
	var settsn='${entity.settsn}';
	$("#settsn").val(settsn);
	var status='${entity.status}';
	$("#status").val(status);
	var sumprice='${entity.sumprice}';
	$("#sumprice").val(sumprice);
	var sellerid='${entity.sellerid}';
	$("#sellerid").val(sellerid);
	
});
	function doSave() {
	  
		if ($("#settid").val() == "") {
			alert("请输入主键！");
			return false;
		}
		var settid=$('#settid').val();
		if(settid=="" || !/^\d+$/.test(settid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#orderno").val() == "") {
			alert("请输入订单号！");
			return false;
		}
		if ($("#settsn").val() == "") {
			alert("请输入结算流水！");
			return false;
		}
		if ($("#status").val() == "") {
			alert("请输入结算状态！");
			return false;
		}
		var status=$('#status').val();
		if(status=="" || !/^\d+$/.test(status)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#sumprice").val() == "") {
			alert("请输入总费用！");
			return false;
		}
		if ($("#sellerid").val() == "") {
			alert("请输入商家ID！");
			return false;
		}
		var sellerid=$('#sellerid').val();
		if(sellerid=="" || !/^\d+$/.test(sellerid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="GspSettlementAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">结算表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="settid" name="entity.settid" value="${settid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>订单号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="orderno" name="entity.orderno" value="${ entity.orderno}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>结算流水：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="settsn" name="entity.settsn" value="${ entity.settsn}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>结算状态：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="status" name="entity.status" value="${ entity.status}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>总费用：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="sumprice" name="entity.sumprice" value="${ entity.sumprice}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商家ID：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="sellerid" name="entity.sellerid" value="${ entity.sellerid}"/></td>
					
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

