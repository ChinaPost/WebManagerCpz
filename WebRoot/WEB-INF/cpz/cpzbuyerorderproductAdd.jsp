<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>订单关联商品表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var orderno='${orderno}';
	$("#orderno").val(orderno);
	var shopproductid='${shopproductid}';
	$("#shopproductid").val(shopproductid);
	
});
	function doSave() {
	  
		if ($("#orderno").val() == "") {
			alert("请输入订单号！");
			return false;
		}
		if ($("#shopproductid").val() == "") {
			alert("请输入卖家商品代号！");
			return false;
		}
		var shopproductid=$('#shopproductid').val();
		if(shopproductid=="" || !/^\d+$/.test(shopproductid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#shopproductname").val() == "") {
			alert("请输入商品名称！");
			return false;
		}
		if ($("#levelid").val() == "") {
			alert("请输入商品所属分类代号！");
			return false;
		}
		var levelid=$('#levelid').val();
		if(levelid=="" || !/^\d+$/.test(levelid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#producttype").val() == "") {
			alert("请输入商品类型0：单个商品:1：套餐！");
			return false;
		}
		if ($("#shopnormsid").val() == "") {
			alert("请输入购买规格！");
			return false;
		}
		if ($("#shopnormsnum").val() == "") {
			alert("请输入购买数量！");
			return false;
		}
		var shopnormsnum=$('#shopnormsnum').val();
		if(shopnormsnum=="" || !/^\d+$/.test(shopnormsnum)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#shopnormsprice").val() == "") {
			alert("请输入购买价格！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzBuyerOrderProductAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">订单关联商品表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="orderno" name="entity.orderno" value="${orderno}" />
<input type="hidden" id="shopproductid" name="entity.shopproductid" value="${shopproductid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopproductname" name="entity.shopproductname" value="${ entity.shopproductname}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品所属分类代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="levelid" name="entity.levelid" value="${ entity.levelid}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">商品类型0：单个商品:1：套餐：
						</td>
						<td>
<select id="producttype" name="entity.producttype" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${producttypeSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>购买规格：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopnormsid" name="entity.shopnormsid" value="${ entity.shopnormsid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>购买数量：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopnormsnum" name="entity.shopnormsnum" value="${ entity.shopnormsnum}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>购买价格：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopnormsprice" name="entity.shopnormsprice" value="${ entity.shopnormsprice}"/></td>
					
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

