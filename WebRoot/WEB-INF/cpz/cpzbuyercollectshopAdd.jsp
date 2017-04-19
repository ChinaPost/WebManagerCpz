<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家店铺收藏信息表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var userid='${userid}';
	$("#userid").val(userid);
	var shopid='${shopid}';
	$("#shopid").val(shopid);
	
});
	function doSave() {
	  
		if ($("#userid").val() == "") {
			alert("请输入用户id！");
			return false;
		}
		var userid=$('#userid').val();
		if(userid=="" || !/^\d+$/.test(userid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#shopid").val() == "") {
			alert("请输入店铺代号！");
			return false;
		}
		var shopid=$('#shopid').val();
		if(shopid=="" || !/^\d+$/.test(shopid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#marketid").val() == "") {
			alert("请输入店铺所在市场代号！");
			return false;
		}
		var marketid=$('#marketid').val();
		if(marketid=="" || !/^\d+$/.test(marketid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#shopbusineerangeid").val() == "") {
			alert("请输入店铺经营范围表id！");
			return false;
		}
		var shopbusineerangeid=$('#shopbusineerangeid').val();
		if(shopbusineerangeid=="" || !/^\d+$/.test(shopbusineerangeid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#isdefaultshop").val() == "") {
			alert("请输入是否默认店铺0：否1：是！");
			return false;
		}
		if ($("#colletctime").val() == "") {
			alert("请输入收藏时间！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzBuyerCollectShopAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">买家店铺收藏信息表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="userid" name="entity.userid" value="${userid}" />
<input type="hidden" id="shopid" name="entity.shopid" value="${shopid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>店铺所在市场代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="marketid" name="entity.marketid" value="${ entity.marketid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>店铺经营范围表id：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopbusineerangeid" name="entity.shopbusineerangeid" value="${ entity.shopbusineerangeid}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">是否默认店铺0：否1：是：
						</td>
						<td>
<select id="isdefaultshop" name="entity.isdefaultshop" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${isdefaultshopSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>收藏时间：</td>
					<td><input id="colletctime" name="entity.colletctime" value="${ entity.colletctime}" style="margin-right:10px;width: 150px" class="Wdate" 
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

