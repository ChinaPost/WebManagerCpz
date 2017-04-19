<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>卖家商品信息表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var shopproductid='${shopproductid}';
	$("#shopproductid").val(shopproductid);
	
});
	function doSave() {
	  
		if ($("#shopproductid").val() == "") {
			alert("请输入卖家商品代号！");
			return false;
		}
		var shopproductid=$('#shopproductid').val();
		if(shopproductid=="" || !/^\d+$/.test(shopproductid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#productname").val() == "") {
			alert("请输入商品自定义名称！");
			return false;
		}
		if ($("#productpic").val() == "") {
			alert("请输入商品自定义图片！");
			return false;
		}
		if ($("#merchintro").val() == "") {
			alert("请输入商品简介！");
			return false;
		}
		if ($("#description").val() == "") {
			alert("请输入商品描述！");
			return false;
		}
		if ($("#productstatus").val() == "") {
			alert("请输入商品状态！");
			return false;
		}
		if ($("#productid").val() == "") {
			alert("请输入关联的平台商品！");
			return false;
		}
		var productid=$('#productid').val();
		if(productid=="" || !/^\d+$/.test(productid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#shopid").val() == "") {
			alert("请输入关联店铺代号！");
			return false;
		}
		var shopid=$('#shopid').val();
		if(shopid=="" || !/^\d+$/.test(shopid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#orderno").val() == "") {
			alert("请输入订单号格式：日期+10位流水号！");
			return false;
		}
		if ($("#starttime").val() == "") {
			alert("请输入商品在线开始时间格式：yyyymmdd hh24miss！");
			return false;
		}
		if($("#end").val()!="" && $("#starttime").val() > $("#end").val()){
				alert("开始时间不能大于结束时间");
				return ;
		}
		if ($("#endtime").val() == "") {
			alert("请输入商品在线结束时间格式：yyyymmdd hh24miss！");
			return false;
		}
		if ($("#remakr1").val() == "") {
			alert("请输入备注！");
			return false;
		}
		if ($("#levelid").val() == "") {
			alert("请输入关联平台分类！");
			return false;
		}
		var levelid=$('#levelid').val();
		if(levelid=="" || !/^\d+$/.test(levelid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzSellerProductAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">卖家商品信息表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="shopproductid" name="entity.shopproductid" value="${shopproductid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品自定义名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productname" name="entity.productname" value="${ entity.productname}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品自定义图片：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productpic" name="entity.productpic" value="${ entity.productpic}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品简介：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="merchintro" name="entity.merchintro" value="${ entity.merchintro}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品描述：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="description" name="entity.description" value="${ entity.description}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品状态：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productstatus" name="entity.productstatus" value="${ entity.productstatus}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>关联的平台商品：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productid" name="entity.productid" value="${ entity.productid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>关联店铺代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopid" name="entity.shopid" value="${ entity.shopid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>订单号格式：日期+10位流水号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="orderno" name="entity.orderno" value="${ entity.orderno}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品在线开始时间格式：yyyymmdd hh24miss：</td>
					<td><input id="starttime" name="entity.starttime" value="${ entity.starttime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品在线结束时间格式：yyyymmdd hh24miss：</td>
					<td><input id="endtime" name="entity.endtime" value="${ entity.endtime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>备注：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="remakr1" name="entity.remakr1" value="${ entity.remakr1}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>关联平台分类：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="levelid" name="entity.levelid" value="${ entity.levelid}"/></td>
					
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

