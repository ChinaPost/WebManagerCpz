<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>平台商品信息表</title>
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
		
		if ($("#productname").val() == "") {
			alert("请输入商品名称！");
			return false;
		}
		if ($("#productcategoryid").val() == "") {
			alert("请输入商品所属分类代号！");
			return false;
		}
		var productcategoryid=$('#productcategoryid').val();
		if(productcategoryid=="" || !/^\d+$/.test(productcategoryid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#producttype").val() == "") {
			alert("请输入商品类型0：单个商品1：套餐！");
			return false;
		}
		if ($("#productstatus").val() == "") {
			alert("请输入商品状态0:上架1：下架！");
			return false;
		}
		if ($("#tradingunit").val() == "") {
			alert("请输入交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶！");
			return false;
		}
		if ($("#productcolor").val() == "") {
			alert("请输入颜色！");
			return false;
		}
		if ($("#isfresh").val() == "") {
			alert("请输入是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？）！");
			return false;
		}
		if ($("#sourcearea").val() == "") {
			alert("请输入原产地名称！");
			return false;
		}
		if ($("#iscanrefund").val() == "") {
			alert("请输入是否支持退货0：否 1：是！");
			return false;
		}
		if ($("#isneedspot").val() == "") {
			alert("请输入是否需要当场处理0：否1：是（是否支持当场处理？）！");
			return false;
		}
		if ($("#productdetail").val() == "") {
			alert("请输入详情URL地址！");
			return false;
		}
		if ($("#supportday").val() == "") {
			alert("请输入配菜支持天数！");
			return false;
		}
		var supportday=$('#supportday').val();
		if(supportday=="" || !/^\d+$/.test(supportday)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#channelno").val() == "") {
			alert("请输入商品发布渠道 多个渠道以“,”好隔开01：微信02：安卓03：IOS（应该没有渠道）！");
			return false;
		}
		if ($("#remakr1").val() == "") {
			alert("请输入备注1！");
			return false;
		}
		if ($("#remakr2").val() == "") {
			alert("请输入备注2！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzPlatProductAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">平台商品信息表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="productid" name="entity.productid" value="${productid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productname" name="entity.productname" value="${ entity.productname}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品所属分类代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productcategoryid" name="entity.productcategoryid" value="${ entity.productcategoryid}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">商品类型0：单个商品1：套餐：
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
					<tr >
						<td align="right" style="width: 120px">商品状态0:上架1：下架：
						</td>
						<td>
<select id="productstatus" name="entity.productstatus" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${productstatusSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
					<tr >
						<td align="right" style="width: 120px">交易单位0：按斤:1：按块2 : 按只3：按支4：按/瓶：
						</td>
						<td>
<select id="tradingunit" name="entity.tradingunit" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${tradingunitSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>颜色：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productcolor" name="entity.productcolor" value="${ entity.productcolor}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">是否鲜活0：否1：是 （此属性是否可以不要，归类于商品状态？）：
						</td>
						<td>
<select id="isfresh" name="entity.isfresh" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${isfreshSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>原产地名称：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="sourcearea" name="entity.sourcearea" value="${ entity.sourcearea}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">是否支持退货0：否 1：是：
						</td>
						<td>
<select id="iscanrefund" name="entity.iscanrefund" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${iscanrefundSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
					<tr >
						<td align="right" style="width: 120px">是否需要当场处理0：否1：是（是否支持当场处理？）：
						</td>
						<td>
<select id="isneedspot" name="entity.isneedspot" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${isneedspotSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>详情URL地址：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="productdetail" name="entity.productdetail" value="${ entity.productdetail}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>配菜支持天数：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="supportday" name="entity.supportday" value="${ entity.supportday}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>商品发布渠道 多个渠道以“,”好隔开01：微信02：安卓03：IOS（应该没有渠道）：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="channelno" name="entity.channelno" value="${ entity.channelno}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>备注1：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="remakr1" name="entity.remakr1" value="${ entity.remakr1}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>备注2：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="remakr2" name="entity.remakr2" value="${ entity.remakr2}"/></td>
					
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

