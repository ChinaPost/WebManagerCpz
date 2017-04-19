<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家购物车表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var shopcarid='${shopcarid}';
	$("#shopcarid").val(shopcarid);
	
});
	function doSave() {
	  
		if ($("#shopcarid").val() == "") {
			alert("请输入购物车id！");
			return false;
		}
		var shopcarid=$('#shopcarid').val();
		if(shopcarid=="" || !/^\d+$/.test(shopcarid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#userid").val() == "") {
			alert("请输入会员号！");
			return false;
		}
		var userid=$('#userid').val();
		if(userid=="" || !/^\d+$/.test(userid)){  
	        alert("必须是正整数!"); 
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
		
		if ($("#shopid").val() == "") {
			alert("请输入卖家店铺代号！");
			return false;
		}
		var shopid=$('#shopid').val();
		if(shopid=="" || !/^\d+$/.test(shopid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#marketid").val() == "") {
			alert("请输入卖家市场代号！");
			return false;
		}
		var marketid=$('#marketid').val();
		if(marketid=="" || !/^\d+$/.test(marketid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#normsid").val() == "") {
			alert("请输入购买规格！");
			return false;
		}
		var normsid=$('#normsid').val();
		if(normsid=="" || !/^\d+$/.test(normsid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#buynum").val() == "") {
			alert("请输入购买数量！");
			return false;
		}
		var buynum=$('#buynum').val();
		if(buynum=="" || !/^\d+$/.test(buynum)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#normsprice").val() == "") {
			alert("请输入购买价格！");
			return false;
		}
		if ($("#createtime").val() == "") {
			alert("请输入加入购物车时间格式：yyyymmdd hh24miss！");
			return false;
		}
		if ($("#remark").val() == "") {
			alert("请输入备注！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzBuyerShoppingAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">买家购物车表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="shopcarid" name="entity.shopcarid" value="${shopcarid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>会员号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="userid" name="entity.userid" value="${ entity.userid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>卖家商品代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopproductid" name="entity.shopproductid" value="${ entity.shopproductid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>卖家店铺代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopid" name="entity.shopid" value="${ entity.shopid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>卖家市场代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="marketid" name="entity.marketid" value="${ entity.marketid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>购买规格：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="normsid" name="entity.normsid" value="${ entity.normsid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>购买数量：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="buynum" name="entity.buynum" value="${ entity.buynum}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>购买价格：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="normsprice" name="entity.normsprice" value="${ entity.normsprice}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>加入购物车时间格式：yyyymmdd hh24miss：</td>
					<td><input id="createtime" name="entity.createtime" value="${ entity.createtime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>备注：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="remark" name="entity.remark" value="${ entity.remark}"/></td>
					
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

