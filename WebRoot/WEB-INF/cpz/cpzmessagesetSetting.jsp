<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>消息设置表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var messagesetid='${messagesetid}';
	$("#messagesetid").val(messagesetid);
	var messagetype='${entity.messagetype}';
	$("#messagetype").val(messagetype);
	var systemno='${entity.systemno}';
	$("#systemno").val(systemno);
	
});
	function doSave() {
	  
		if ($("#messagesetid").val() == "") {
			alert("请输入主键！");
			return false;
		}
		var messagesetid=$('#messagesetid').val();
		if(messagesetid=="" || !/^\d+$/.test(messagesetid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#messagetype").val() == "") {
			alert("请输入消息类型！");
			return false;
		}
		var messagetype=$('#messagetype').val();
		if(messagetype=="" || !/^\d+$/.test(messagetype)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#systemno").val() == "") {
			alert("请输入系统用户号！");
			return false;
		}
		var systemno=$('#systemno').val();
		if(systemno=="" || !/^\d+$/.test(systemno)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzMessageSetAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">消息设置表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="messagesetid" name="entity.messagesetid" value="${messagesetid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>消息类型：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="messagetype" name="entity.messagetype" value="${ entity.messagetype}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>系统用户号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="systemno" name="entity.systemno" value="${ entity.systemno}"/></td>
					
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

