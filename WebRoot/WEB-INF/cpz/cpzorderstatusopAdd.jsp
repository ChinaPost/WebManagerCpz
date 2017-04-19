<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单状态操作表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var orderno='${orderno}';
	$("#orderno").val(orderno);
	
});
	function doSave() {
	  
		if ($("#orderno").val() == "") {
			alert("请输入订单号！");
			return false;
		}
		var orderno=$('#orderno').val();
		if(orderno=="" || !/^\d+$/.test(orderno)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#orderstatus").val() == "") {
			alert("请输入当前订单状态！");
			return false;
		}
		if ($("#opfalg").val() == "") {
			alert("请输入可操作标志01：支付02：订单取消 03：配货取消 04：退款！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzOrderStatusOpAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">订单状态操作表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="orderno" name="entity.orderno" value="${orderno}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>当前订单状态：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="orderstatus" name="entity.orderstatus" value="${ entity.orderstatus}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">可操作标志01：支付02：订单取消 03：配货取消 04：退款：
						</td>
						<td>
<select id="opfalg" name="entity.opfalg" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${opfalgSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
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

