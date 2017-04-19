<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家退款表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var refundid='${refundid}';
	$("#refundid").val(refundid);
	
});
	function doSave() {
	  
		if ($("#refundid").val() == "") {
			alert("请输入退款单号 顺序号！");
			return false;
		}
		var refundid=$('#refundid').val();
		if(refundid=="" || !/^\d+$/.test(refundid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#userid").val() == "") {
			alert("请输入会员号！");
			return false;
		}
		if ($("#orderno").val() == "") {
			alert("请输入订单号！");
			return false;
		}
		if ($("#refundreason").val() == "") {
			alert("请输入退款原因！");
			return false;
		}
		if ($("#isconsultseller").val() == "") {
			alert("请输入是否与卖家已协商0：是1：否！");
			return false;
		}
		if ($("#refundstyle").val() == "") {
			alert("请输入退款方式0：系统退款1：人工退款！");
			return false;
		}
		if ($("#ordermoney").val() == "") {
			alert("请输入订单原金额！");
			return false;
		}
		if ($("#applymoney").val() == "") {
			alert("请输入退款申请金额！");
			return false;
		}
		if ($("#applytime").val() == "") {
			alert("请输入申请时 间 格式：yyyymmdd hh24miss！");
			return false;
		}
		if ($("#refundtime").val() == "") {
			alert("请输入最后退款时间 格式：yyyymmdd hh24miss！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzBuyerRefundAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">买家退款表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="refundid" name="entity.refundid" value="${refundid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>会员号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="userid" name="entity.userid" value="${ entity.userid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>订单号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="orderno" name="entity.orderno" value="${ entity.orderno}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>退款原因：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="refundreason" name="entity.refundreason" value="${ entity.refundreason}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">是否与卖家已协商0：是1：否：
						</td>
						<td>
<select id="isconsultseller" name="entity.isconsultseller" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${isconsultsellerSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
					<tr >
						<td align="right" style="width: 120px">退款方式0：系统退款1：人工退款：
						</td>
						<td>
<select id="refundstyle" name="entity.refundstyle" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${refundstyleSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>订单原金额：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="ordermoney" name="entity.ordermoney" value="${ entity.ordermoney}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>退款申请金额：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="applymoney" name="entity.applymoney" value="${ entity.applymoney}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>申请时 间 格式：yyyymmdd hh24miss：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="applytime" name="entity.applytime" value="${ entity.applytime}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>最后退款时间 格式：yyyymmdd hh24miss：</td>
					<td><input id="refundtime" name="entity.refundtime" value="${ entity.refundtime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
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

