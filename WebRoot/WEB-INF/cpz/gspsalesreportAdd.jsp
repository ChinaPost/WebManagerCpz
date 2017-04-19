<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>销量统计表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var salesreportid='${salesreportid}';
	$("#salesreportid").val(salesreportid);
	
});
	function doSave() {
	  
		if ($("#salesreportid").val() == "") {
			alert("请输入主键！");
			return false;
		}
		var salesreportid=$('#salesreportid').val();
		if(salesreportid=="" || !/^\d+$/.test(salesreportid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#datestr").val() == "") {
			alert("请输入时间（以天为单位）！");
			return false;
		}
		if ($("#merchname").val() == "") {
			alert("请输入商品名称！");
			return false;
		}
		if ($("#weight").val() == "") {
			alert("请输入重量！");
			return false;
		}
		if ($("#settsumprice").val() == "") {
			alert("请输入结算费用！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="GspSalesReportAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">销量统计表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="salesreportid" name="entity.salesreportid" value="${salesreportid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>时间（以天为单位）：</td>
					<td><input id="datestr" name="entity.datestr" value="${ entity.datestr}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="merchname" name="entity.merchname" value="${ entity.merchname}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>重量：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="weight" name="entity.weight" value="${ entity.weight}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>结算费用：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="settsumprice" name="entity.settsumprice" value="${ entity.settsumprice}"/></td>
					
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

