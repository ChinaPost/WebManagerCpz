<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品规格关联表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var id='${id}';
	$("#id").val(id);
	
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
		
		if ($("#shopproductid").val() == "") {
			alert("请输入商品id！");
			return false;
		}
		var shopproductid=$('#shopproductid').val();
		if(shopproductid=="" || !/^\d+$/.test(shopproductid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#shopnormsid").val() == "") {
			alert("请输入规格代号！");
			return false;
		}
		var shopnormsid=$('#shopnormsid').val();
		if(shopnormsid=="" || !/^\d+$/.test(shopnormsid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="GspProductNormsMapAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">商品规格关联表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="id" name="entity.id" value="${id}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品id：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopproductid" name="entity.shopproductid" value="${ entity.shopproductid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>规格代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopnormsid" name="entity.shopnormsid" value="${ entity.shopnormsid}"/></td>
					
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

