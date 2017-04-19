<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>消息表</title>
	<script>
		function toAdd(){
			window.location.href="CpzMessageAction!toAdd?messageid=${messageid}";
		}
		
		
		$(document).on('ready', function() {
			var messageid="${messageid}";
			$("#messageid").val(messageid);
			getAll('CpzMessageAction!list');
		});
		
		
		function search(){
			getAll('CpzMessageAction!list');
		}
		
		function dodel(mmessageid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzMessageAction!doDelete',
					data:{
messageid:mmessageid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzMessageAction!index?messageid=${messageid}";
					},
					error : function() {
						alert("对不起，系统错误，请稍候重试！")
					}
				});
			}
			
			
		}
		function getAll(tzurl){
var searchInput = $("#searchInput").val();
				$.ajax({
					type:'POST',
					dataType:'json',
					url:tzurl,
					data:{
messageid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].messagedetail + '</td>';
	 divtext += '<td>' + data[i].messagetype + '</td>';
	 divtext += '<td>' + data[i].readmessageflag + '</td>';
	 divtext += '<td>' + data[i].systemno + '</td>';
	 divtext += '<td>' + data[i].messagechannel + '</td>';
	 divtext += '<td>' + data[i].messagelinktype + '</td>';
	 divtext += '<td>' + data[i].messagelinkpara + '</td>';
								divtext += '<td ><a href="CpzMessageAction!toUpdate?messageid='+data[i].messageid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].messageid+')"> [删除] </a></td>';
								divtext += '</tr>';
							}
							//divtext += pagenational;
							$("#newtable tbody").html(divtext);
							$("#pageContent").html(pagenational);
					}
				});
		}
	</script>
</head>
<body style="overflow:auto">
	<div style="padding-left:20px;margin-bottom:10px;" >
	<input type="hidden" id="messageid" name="messageid" value="" />
	消息表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
	<input type="button" value="查询" name = "btn_search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;" class="subBtn" onclick="search()">
	<input type="button" value="新增" name = "btn_search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;" class="subBtn" onclick="toAdd()">
	
	</div>
	<div id="signContent">
	  <div class="table-list lr10">
	      <table width="100%">
	      <tr>
	        <td style="vertical-align: top;">
	        <table id="newtable" width="100%">
	          <thead class=trhead id="tblHeader">
	            
				<tr> 				<th  style="text-align:center;">内容</th>
				<th  style="text-align:center;">01：业务通知02：系统变更通知03：业务进展通知04：其它通知</th>
				<th  style="text-align:center;">0：未读 1：已读</th>
				<th  style="text-align:center;">系统用户</th>
				<th  style="text-align:center;">消息渠道01：微信02：安卓03：IOS</th>
				<th  style="text-align:center;">消息链接类型</th>
				<th  style="text-align:center;">消息链接业务参数</th>
				<th  style="text-align:center;">操作</th></tr>
				</thead>
				<tbody id="records">
			    </tbody>
		</table>
	       </td>
	      </tr>
	      </table>
	</div>
	<div id="pageContent"></div>
  </div>
	
</body>
</html>

