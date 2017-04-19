<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>平台商品图片表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var productid='${productid}';
	$("#productid").val(productid);
	
});
	function doSave() {
	  
		if ($("#productid").val() == "") {
			alert("请输入商品代号！");
			return false;
		}
		var productid=$('#productid').val();
		if(productid=="" || !/^\d+$/.test(productid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#picname").val() == "") {
			alert("请输入图片名称！");
			return false;
		}
		if ($("#picshowno").val() == "") {
			alert("请输入图片显示顺序！");
			return false;
		}
		var picshowno=$('#picshowno').val();
		if(picshowno=="" || !/^\d+$/.test(picshowno)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#picnorms").val() == "") {
			alert("请输入图片规格0:大1：中2：小！");
			return false;
		}
		if ($("#picurl").val() == "") {
			alert("请输入图片地址！");
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
 <form action="CpzPlatProductPicAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">平台商品图片表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="productid" name="entity.productid" value="${productid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>图片名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="picname" name="entity.picname" value="${ entity.picname}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>图片显示顺序：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="picshowno" name="entity.picshowno" value="${ entity.picshowno}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">图片规格0:大1：中2：小：
						</td>
						<td>
<select id="picnorms" name="entity.picnorms" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${picnormsSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
					<tr >
						<td align="right" style="width: 120px">图片地址：
						</td>
						<td>
							<input type="file" id="file1" name="file1"  />
								
						</td>
						
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

