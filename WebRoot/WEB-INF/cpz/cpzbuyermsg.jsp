<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家基本信息表</title>
	<script>
		function toAdd(){
			window.location.href="CpzBuyerMsgAction!toAdd?userid=${userid}";
		}
		
		
		$(document).on('ready', function() {
			var userid="${userid}";
			$("#userid").val(userid);
			getAll('CpzBuyerMsgAction!list');
		});
		
		
		function search(){
			getAll('CpzBuyerMsgAction!list');
		}
		
		function dodel(muserid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzBuyerMsgAction!doDelete',
					data:{
userid:muserid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzBuyerMsgAction!index?userid=${userid}";
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
userid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].moileno + '</td>';
	 divtext += '<td>' + data[i].nickname + '</td>';
	 divtext += '<td>' + data[i].sex + '</td>';
	 divtext += '<td>' + data[i].headurl + '</td>';
	 divtext += '<td>' + data[i].cstmlevel + '</td>';
	 divtext += '<td>' + data[i].lonvalue + '</td>';
	 divtext += '<td>' + data[i].latvalue + '</td>';
	 divtext += '<td>' + data[i].createtime + '</td>';
								divtext += '<td ><a href="CpzBuyerMsgAction!toUpdate?userid='+data[i].userid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].userid+')"> [删除] </a></td>';
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
	<input type="hidden" id="userid" name="userid" value="" />
	买家基本信息表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">手机号码</th>
				<th  style="text-align:center;">会员呢称</th>
				<th  style="text-align:center;">性别0：女1：男</th>
				<th  style="text-align:center;">会员头像图片URL地址</th>
				<th  style="text-align:center;">会员等级预留01：铜牌会员02：银牌会员03：金牌会员</th>
				<th  style="text-align:center;">注册时所在经度格式：小数点后2位</th>
				<th  style="text-align:center;">注册时所在纬度格式：小数点后2位</th>
				<th  style="text-align:center;">注册时间格式：yyyymmdd hh24miss</th>
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

