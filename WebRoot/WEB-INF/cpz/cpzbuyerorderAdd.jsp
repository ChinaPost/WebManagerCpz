<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家订单表</title>
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
			alert("请输入订单号格式：日期+10位流水号！");
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
		
		if ($("#username").val() == "") {
			alert("请输入会员姓名！");
			return false;
		}
		if ($("#userphone").val() == "") {
			alert("请输入会员联系手机！");
			return false;
		}
		if ($("#orderstatus").val() == "") {
			alert("请输入订单状态01：待付款02：已付款03：等待发货04：已备货05：已确认收货06：已完成07：已取消08：退款中09：已退款10：退款拒绝！");
			return false;
		}
		if ($("#totalmoney").val() == "") {
			alert("请输入订单总金额！");
			return false;
		}
		if ($("#paymoney").val() == "") {
			alert("请输入订单已支付金额！");
			return false;
		}
		if ($("#paystatus").val() == "") {
			alert("请输入订单支付状态0：未支付1：支付中2：已支付！");
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
			alert("请输入所在市场代号！");
			return false;
		}
		var marketid=$('#marketid').val();
		if(marketid=="" || !/^\d+$/.test(marketid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#shiptype").val() == "") {
			alert("请输入配送方式预留 01：自提02：寄递！");
			return false;
		}
		if ($("#provcode").val() == "") {
			alert("请输入省份代号预留：目前阶段接口送空值！");
			return false;
		}
		if ($("#citycode").val() == "") {
			alert("请输入市局代号预留：目前阶段接口送空值！");
			return false;
		}
		if ($("#countycode").val() == "") {
			alert("请输入区县代号预留：目前阶段接口送空值！");
			return false;
		}
		if ($("#detailaddr").val() == "") {
			alert("请输入详细地址代号！");
			return false;
		}
		if ($("#invoicetype").val() == "") {
			alert("请输入发票类型预留发票模块：目前阶段接口送0：不开发票 1：个人发票 2：单位发票！");
			return false;
		}
		if ($("#invoicetitle").val() == "") {
			alert("请输入发票抬头！");
			return false;
		}
		if ($("#orderremark").val() == "") {
			alert("请输入给卖家留言！");
			return false;
		}
		if ($("#getproducttime").val() == "") {
			alert("请输入提货时间！");
			return false;
		}
		if ($("#booktime").val() == "") {
			alert("请输入订单下单时间格式：yyyymmdd hh24miss！");
			return false;
		}
		if ($("#paytime").val() == "") {
			alert("请输入订单支付时间格式：yyyymmdd hh24miss！");
			return false;
		}
		if ($("#channelno").val() == "") {
			alert("请输入订单受理渠道01：微信02：安卓03：IOS！");
			return false;
		}
		if ($("#remark1").val() == "") {
			alert("请输入备注1！");
			return false;
		}
		if ($("#remark2").val() == "") {
			alert("请输入备注2！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzBuyerOrderAction!doAdd" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">买家订单表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="orderno" name="entity.orderno" value="${orderno}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>会员号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="userid" name="entity.userid" value="${ entity.userid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>会员姓名：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="username" name="entity.username" value="${ entity.username}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>会员联系手机：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="userphone" name="entity.userphone" value="${ entity.userphone}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">订单状态01：待付款02：已付款03：等待发货04：已备货05：已确认收货06：已完成07：已取消08：退款中09：已退款10：退款拒绝：
						</td>
						<td>
<select id="orderstatus" name="entity.orderstatus" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${orderstatusSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>订单总金额：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="totalmoney" name="entity.totalmoney" value="${ entity.totalmoney}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>订单已支付金额：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="paymoney" name="entity.paymoney" value="${ entity.paymoney}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">订单支付状态0：未支付1：支付中2：已支付：
						</td>
						<td>
<select id="paystatus" name="entity.paystatus" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${paystatusSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>店铺代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="shopid" name="entity.shopid" value="${ entity.shopid}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>所在市场代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="marketid" name="entity.marketid" value="${ entity.marketid}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">配送方式预留 01：自提02：寄递：
						</td>
						<td>
<select id="shiptype" name="entity.shiptype" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${shiptypeSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>省份代号预留：目前阶段接口送空值：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="provcode" name="entity.provcode" value="${ entity.provcode}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>市局代号预留：目前阶段接口送空值：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="citycode" name="entity.citycode" value="${ entity.citycode}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>区县代号预留：目前阶段接口送空值：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="countycode" name="entity.countycode" value="${ entity.countycode}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>详细地址代号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="detailaddr" name="entity.detailaddr" value="${ entity.detailaddr}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">发票类型预留发票模块：目前阶段接口送0：不开发票 1：个人发票 2：单位发票：
						</td>
						<td>
<select id="invoicetype" name="entity.invoicetype" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${invoicetypeSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>发票抬头：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="invoicetitle" name="entity.invoicetitle" value="${ entity.invoicetitle}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>给卖家留言：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="orderremark" name="entity.orderremark" value="${ entity.orderremark}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>提货时间：</td>
					<td><input id="getproducttime" name="entity.getproducttime" value="${ entity.getproducttime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>订单下单时间格式：yyyymmdd hh24miss：</td>
					<td><input id="booktime" name="entity.booktime" value="${ entity.booktime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>订单支付时间格式：yyyymmdd hh24miss：</td>
					<td><input id="paytime" name="entity.paytime" value="${ entity.paytime}" style="margin-right:10px;width: 150px" class="Wdate" 
					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/></td>
				</tr>
					<tr >
						<td align="right" style="width: 120px">订单受理渠道01：微信02：安卓03：IOS：
						</td>
						<td>
<select id="channelno" name="entity.channelno" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${channelnoSelectList}">	
							<option value='${fn:substringBefore(item,"-")}'>${fn:substringAfter(item,"-")}</option>
					</c:forEach>
					
</select>
						</td>
						
					</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>备注1：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="remark1" name="entity.remark1" value="${ entity.remark1}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>备注2：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="remark2" name="entity.remark2" value="${ entity.remark2}"/></td>
					
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

