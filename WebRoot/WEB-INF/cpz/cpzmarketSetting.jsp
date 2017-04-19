<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>市场表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var marketid='${marketid}';
	$("#marketid").val(marketid);
	var marketname='${entity.marketname}';
	$("#marketname").val(marketname);
	var lonvalue='${entity.lonvalue}';
	$("#lonvalue").val(lonvalue);
	var latvalue='${entity.latvalue}';
	$("#latvalue").val(latvalue);
	var provcode='${entity.provcode}';
	$("#provcode").val(provcode);
	var citycode='${entity.citycode}';
	$("#citycode").val(citycode);
	var countycode='${entity.countycode}';
	$("#countycode").val(countycode);
	var marketarea='${entity.marketarea}';
	$("#marketarea").val(marketarea);
	var marketaddr='${entity.marketaddr}';
	$("#marketaddr").val(marketaddr);
	
});
	function doSave() {
	  
		if ($("#marketid").val() == "") {
			alert("请输入市场ID！");
			return false;
		}
		var marketid=$('#marketid').val();
		if(marketid=="" || !/^\d+$/.test(marketid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#marketname").val() == "") {
			alert("请输入市场名字！");
			return false;
		}
		if ($("#lonvalue").val() == "") {
			alert("请输入市场经度！");
			return false;
		}
		if ($("#latvalue").val() == "") {
			alert("请输入市场纬度！");
			return false;
		}
		if ($("#provcode").val() == "") {
			alert("请输入省份代号！");
			return false;
		}
		if ($("#citycode").val() == "") {
			alert("请输入市局代号！");
			return false;
		}
		if ($("#countycode").val() == "") {
			alert("请输入区县代号！");
			return false;
		}
		if ($("#marketarea").val() == "") {
			alert("请输入市场所属片区！");
			return false;
		}
		if ($("#marketaddr").val() == "") {
			alert("请输入市场详细地址！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzMarketAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">市场表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="marketid" name="entity.marketid" value="${marketid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>市场名字：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="marketname" name="entity.marketname" value="${ entity.marketname}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>市场经度：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="lonvalue" name="entity.lonvalue" value="${ entity.lonvalue}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>市场纬度：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="latvalue" name="entity.latvalue" value="${ entity.latvalue}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>省份代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="provcode" name="entity.provcode" value="${ entity.provcode}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>市局代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="citycode" name="entity.citycode" value="${ entity.citycode}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>区县代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="countycode" name="entity.countycode" value="${ entity.countycode}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>市场所属片区：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="marketarea" name="entity.marketarea" value="${ entity.marketarea}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>市场详细地址：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="marketaddr" name="entity.marketaddr" value="${ entity.marketaddr}"/></td>
					
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

