<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品分类表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var productcategoryid='${productcategoryid}';
	$("#productcategoryid").val(productcategoryid);
	
});
	function doSave() {
	  
		if ($("#productcategoryid").val() == "") {
			alert("请输入级别代号顺序号，从1开始！");
			return false;
		}
		var productcategoryid=$('#productcategoryid').val();
		if(productcategoryid=="" || !/^\d+$/.test(productcategoryid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#productcategoryname").val() == "") {
			alert("请输入级别名称！");
			return false;
		}
		if ($("#parentid").val() == "") {
			alert("请输入父级别代号！");
			return false;
		}
		var parentid=$('#parentid').val();
		if(parentid=="" || !/^\d+$/.test(parentid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#parentname").val() == "") {
			alert("请输入父级别名称！");
			return false;
		}
		if ($("#productcategorygrade").val() == "") {
			alert("请输入级别等级0：根级别1：第1级2：第2级！");
			return false;
		}
		if ($("#path").val() == "") {
			alert("请输入树路径！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzProductLevelAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">商品分类表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="productcategoryid" name="entity.productcategoryid" value="${productcategoryid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>级别名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productcategoryname" name="entity.productcategoryname" value="${ entity.productcategoryname}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>父级别代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="parentid" name="entity.parentid" value="${ entity.parentid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>父级别名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="parentname" name="entity.parentname" value="${ entity.parentname}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">级别等级0：根级别1：第1级2：第2级：
						</td>
						<td>
<select id="productcategorygrade" name="entity.productcategorygrade" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${productcategorygradeSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>树路径：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="path" name="entity.path" value="${ entity.path}"/></td>
					
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

