<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>卖家表</title>
	<script>
		function toAdd(){
			window.location.href="CpzSellerAction!toAdd?sellerid=${sellerid}";
		}
		
		
		$(document).on('ready', function() {
			var sellerid="${sellerid}";
			$("#sellerid").val(sellerid);
			getAll('CpzSellerAction!list');
		});
		
		
		function search(){
			getAll('CpzSellerAction!list');
		}
		
		function dodel(msellerid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzSellerAction!doDelete',
					data:{
sellerid:msellerid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzSellerAction!index?sellerid=${sellerid}";
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
sellerid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].account + '</td>';
	 divtext += '<td>' + data[i].mobile + '</td>';
	 divtext += '<td>' + data[i].password + '</td>';
	 divtext += '<td>' + data[i].status + '</td>';
								divtext += '<td ><a href="CpzSellerAction!toUpdate?sellerid='+data[i].sellerid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].sellerid+')"> [删除] </a></td>';
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
	<input type="hidden" id="sellerid" name="sellerid" value="" />
	卖家表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">账号</th>
				<th  style="text-align:center;">手机号码</th>
				<th  style="text-align:center;">密码</th>
				<th  style="text-align:center;">状态</th>
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

