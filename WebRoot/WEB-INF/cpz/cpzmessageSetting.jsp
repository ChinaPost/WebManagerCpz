<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>消息表</title>
<script src="${ctx}/kindeditor/kindeditor.js" type="text/javascript"></script>
<script src="${ctx}/kindeditor/lang/zh_CN.js" type="text/javascript"></script>
<script type="text/javascript" language="javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
$(document).on('ready', function() {
	
	var messageid='${messageid}';
	$("#messageid").val(messageid);
	var messagedetail='${entity.messagedetail}';
	$("#messagedetail").val(messagedetail);
	var messagetype='${entity.messagetype}';
	$("#messagetype").val(messagetype);
	var readmessageflag='${entity.readmessageflag}';
	$("#readmessageflag").val(readmessageflag);
	var systemno='${entity.systemno}';
	$("#systemno").val(systemno);
	var messagechannel='${entity.messagechannel}';
	$("#messagechannel").val(messagechannel);
	var messagelinktype='${entity.messagelinktype}';
	$("#messagelinktype").val(messagelinktype);
	var messagelinkpara='${entity.messagelinkpara}';
	$("#messagelinkpara").val(messagelinkpara);
	
});
	function doSave() {
	  
		if ($("#messageid").val() == "") {
			alert("请输入主键！");
			return false;
		}
		var messageid=$('#messageid').val();
		if(messageid=="" || !/^\d+$/.test(messageid)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#messagedetail").val() == "") {
			alert("请输入内容！");
			return false;
		}
		if ($("#messagetype").val() == "") {
			alert("请输入01：业务通知02：系统变更通知03：业务进展通知04：其它通知！");
			return false;
		}
		if ($("#readmessageflag").val() == "") {
			alert("请输入0：未读 1：已读！");
			return false;
		}
		var readmessageflag=$('#readmessageflag').val();
		if(readmessageflag=="" || !/^\d+$/.test(readmessageflag)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#systemno").val() == "") {
			alert("请输入系统用户！");
			return false;
		}
		var systemno=$('#systemno').val();
		if(systemno=="" || !/^\d+$/.test(systemno)){  
	        alert("必须是正整数!"); 
	        return false;
	    }  
		
		if ($("#messagechannel").val() == "") {
			alert("请输入消息渠道01：微信02：安卓03：IOS！");
			return false;
		}
		if ($("#messagelinktype").val() == "") {
			alert("请输入消息链接类型！");
			return false;
		}
		if ($("#messagelinkpara").val() == "") {
			alert("请输入消息链接业务参数！");
			return false;
		}
		 myForm.submit();
	}
	 
</script>
</head>
<body>
 <form action="CpzMessageAction!doUpdate" method="post" enctype="multipart/form-data" name="myForm">
	<div style="margin-left: 20px;">消息表</div>
	<div class="table_form lr10">
		<table width="100%" cellspacing="0" cellpadding="0">
			<tbody>
<input type="hidden" id="messageid" name="entity.messageid" value="${messageid}" />
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>内容：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="messagedetail" name="entity.messagedetail" value="${ entity.messagedetail}"/></td>
					
				</tr>
					<tr >
						<td align="right" style="width: 120px">01：业务通知02：系统变更通知03：业务进展通知04：其它通知：
						</td>
						<td>
<select id="messagetype" name="entity.messagetype" class="form-control" style="width: 187px;height:28px;margin-bottom:10px;">
				<option value="">请选择</option>
					<c:forEach var="item" items="${messagetypeSelectList}">	
						<c:choose>
							<c:when test="${item==entity.messagetype}">								
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
					<td align="right" style="width: 120px"><font color="red">*</font>0：未读 1：已读：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="readmessageflag" name="entity.readmessageflag" value="${ entity.readmessageflag}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>系统用户：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="systemno" name="entity.systemno" value="${ entity.systemno}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>消息渠道01：微信02：安卓03：IOS：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="messagechannel" name="entity.messagechannel" value="${ entity.messagechannel}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>消息链接类型：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="messagelinktype" name="entity.messagelinktype" value="${ entity.messagelinktype}"/></td>
					
				</tr>
				<tr>
					<td align="right" style="width: 120px"><font color="red">*</font>消息链接业务参数：</td>
					<td><input type="text" class="input-text wid400 bg"
						id="messagelinkpara" name="entity.messagelinkpara" value="${ entity.messagelinkpara}"/></td>
					
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

