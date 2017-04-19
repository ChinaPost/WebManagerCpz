<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>店铺经营范围表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var shopbusineerangeid='${shopbusineerangeid}';
	$("#shopbusineerangeid").val(shopbusineerangeid);
	var businame='${entity.businame}';
	$("#businame").val(businame);
	
});
	function doSave() {
	  
		if ($("#shopbusineerangeid").val() == "") {
			alert("请输入主键id！");
			return false;
		}
		var shopbusineerangeid=$('#shopbusineerangeid').val();
		if(shopbusineerangeid=="" || !/^\d+$/.test(shopbusineerangeid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#businame").val() == "") {
			alert("请输入经营范围名称！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzShopBusineeRangeAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">店铺经营范围表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="shopbusineerangeid" name="entity.shopbusineerangeid" value="${shopbusineerangeid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>经营范围名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="businame" name="entity.businame" value="${ entity.businame}"/></td>
					
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

