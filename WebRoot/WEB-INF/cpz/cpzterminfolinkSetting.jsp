<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家或者卖家关联第三方信息表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var systemno='${systemno}';
	$("#systemno").val(systemno);
	var systemtyoe='${entity.systemtyoe}';
	$("#systemtyoe").val(systemtyoe);
	var linktype='${entity.linktype}';
	$("#linktype").val(linktype);
	var linkno='${entity.linkno}';
	$("#linkno").val(linkno);
	var linktime='${entity.linktime}';
	$("#linktime").val(linktime);
	
});
	function doSave() {
	  
		if ($("#systemno").val() == "") {
			alert("请输入系统用户号！");
			return false;
		}
		var systemno=$('#systemno').val();
		if(systemno=="" || !/^\d+$/.test(systemno)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#systemtyoe").val() == "") {
			alert("请输入系统类别0:买家1：卖家2：批发商！");
			return false;
		}
		if ($("#linktype").val() == "") {
			alert("请输入第三方代号类型0：微信OPENID1：安卓设备号2：IOS设备号！");
			return false;
		}
		if ($("#linkno").val() == "") {
			alert("请输入第三方代号 如微信公众号对应的OPENID或者系统设备号！");
			return false;
		}
		if ($("#linktime").val() == "") {
			alert("请输入关联时间格式：yyyymmdd hh24miss！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzTerminfoLinkAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">买家或者卖家关联第三方信息表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="systemno" name="entity.systemno" value="${systemno}" />
					<tr >
						<td align="right" style="width: 120px">系统类别0:买家1：卖家2：批发商：
						</td>
						<td>
<select id="systemtyoe" name="entity.systemtyoe" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${systemtyoeSelectList}">	
						<c:choose>
							<c:when test="${item==entity.systemtyoe}">								
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
					<tr >
						<td align="right" style="width: 120px">第三方代号类型0：微信OPENID1：安卓设备号2：IOS设备号：
						</td>
						<td>
<select id="linktype" name="entity.linktype" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${linktypeSelectList}">	
						<c:choose>
							<c:when test="${item==entity.linktype}">								
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
					<td align="right" style="width: 120px"><font color="red">*</font>第三方代号 如微信公众号对应的OPENID或者系统设备号：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="linkno" name="entity.linkno" value="${ entity.linkno}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>关联时间格式：yyyymmdd hh24miss：</td>
					<td><input id="linktime" name="entity.linktime" value="${ entity.linktime}" style="margin-right:10px;width: 150px" class="Wdate" 
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

