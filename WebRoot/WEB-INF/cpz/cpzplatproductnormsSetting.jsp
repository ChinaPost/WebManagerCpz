<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>平台商品规格表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var normsid='${normsid}';
	$("#normsid").val(normsid);
	var productid='${entity.productid}';
	$("#productid").val(productid);
	var normsname='${entity.normsname}';
	$("#normsname").val(normsname);
	var remark1='${entity.remark1}';
	$("#remark1").val(remark1);
	
});
	function doSave() {
	  
		if ($("#normsid").val() == "") {
			alert("请输入规格代号！");
			return false;
		}
		var normsid=$('#normsid').val();
		if(normsid=="" || !/^\d+$/.test(normsid)){  
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
		
		if ($("#normsname").val() == "") {
			alert("请输入规格名称！");
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
 <form action="CpzPlatProductNormsAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">平台商品规格表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="normsid" name="entity.normsid" value="${normsid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productid" name="entity.productid" value="${ entity.productid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>规格名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="normsname" name="entity.normsname" value="${ entity.normsname}"/></td>
					
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

