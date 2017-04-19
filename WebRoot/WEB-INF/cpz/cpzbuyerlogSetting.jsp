<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单或者退款单日志表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var orderlogid='${orderlogid}';
	$("#orderlogid").val(orderlogid);
	var dealtime='${entity.dealtime}';
	$("#dealtime").val(dealtime);
	var dealtype='${entity.dealtype}';
	$("#dealtype").val(dealtype);
	var dealcontent='${entity.dealcontent}';
	$("#dealcontent").val(dealcontent);
	var dealperson='${entity.dealperson}';
	$("#dealperson").val(dealperson);
	var lonvalue='${entity.lonvalue}';
	$("#lonvalue").val(lonvalue);
	var latvalue='${entity.latvalue}';
	$("#latvalue").val(latvalue);
	
});
	function doSave() {
	  
		if ($("#orderlogid").val() == "") {
			alert("请输入日志流水号 顺序号，从1开始！");
			return false;
		}
		var orderlogid=$('#orderlogid').val();
		if(orderlogid=="" || !/^\d+$/.test(orderlogid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#dealtime").val() == "") {
			alert("请输入处理时间！");
			return false;
		}
		var dealtime=$('#dealtime').val();
		if(dealtime=="" || !/^\d+$/.test(dealtime)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#dealtype").val() == "") {
			alert("请输入处理类型0：下单 1：支付 2：配货 3：配货取消 4：取货 5：退款申请6：退款完成！");
			return false;
		}
		if ($("#dealcontent").val() == "") {
			alert("请输入处理信息内容！");
			return false;
		}
		if ($("#dealperson").val() == "") {
			alert("请输入处理人0：买家 1：卖家！");
			return false;
		}
		var dealperson=$('#dealperson').val();
		if(dealperson=="" || !/^\d+$/.test(dealperson)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#lonvalue").val() == "") {
			alert("请输入操作经度 格式：小数点后2位！");
			return false;
		}
		if ($("#latvalue").val() == "") {
			alert("请输入操作纬度！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzBuyerLogAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">订单或者退款单日志表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="orderlogid" name="entity.orderlogid" value="${orderlogid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>处理时间：</td>
					<td><input id="dealtime" name="entity.dealtime" value="${ entity.dealtime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
					<tr >
						<td align="right" style="width: 120px">处理类型0：下单 1：支付 2：配货 3：配货取消 4：取货 5：退款申请6：退款完成：
						</td>
						<td>
<select id="dealtype" name="entity.dealtype" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${dealtypeSelectList}">	
						<c:choose>
							<c:when test="${item==entity.dealtype}">								
								<option value='${fn:substringBefore(item,"-")}' selected="selected">${fn:substringAfter(item,"-")} </option>
							</c:when>
							<c:otherwise>
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
							</c:otherwise>
						</c:choose>		  				
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>处理信息内容：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="dealcontent" name="entity.dealcontent" value="${ entity.dealcontent}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>处理人0：买家 1：卖家：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="dealperson" name="entity.dealperson" value="${ entity.dealperson}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>操作经度 格式：小数点后2位：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="lonvalue" name="entity.lonvalue" value="${ entity.lonvalue}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>操作纬度：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="latvalue" name="entity.latvalue" value="${ entity.latvalue}"/></td>
					
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

