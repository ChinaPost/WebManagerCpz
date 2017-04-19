<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>市场表</title>
	<script>
		function toAdd(){
			window.location.href="CpzMarketAction!toAdd?marketid=${marketid}";
		}
		
		
		$(document).on('ready', function() {
			var marketid="${marketid}";
			$("#marketid").val(marketid);
			getAll('CpzMarketAction!list');
		});
		
		
		function search(){
			getAll('CpzMarketAction!list');
		}
		
		function dodel(mmarketid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzMarketAction!doDelete',
					data:{
marketid:mmarketid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzMarketAction!index?marketid=${marketid}";
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
marketid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].marketname + '</td>';
	 divtext += '<td>' + data[i].lonvalue + '</td>';
	 divtext += '<td>' + data[i].latvalue + '</td>';
	 divtext += '<td>' + data[i].provcode + '</td>';
	 divtext += '<td>' + data[i].citycode + '</td>';
	 divtext += '<td>' + data[i].countycode + '</td>';
	 divtext += '<td>' + data[i].marketarea + '</td>';
	 divtext += '<td>' + data[i].marketaddr + '</td>';
								divtext += '<td ><a href="CpzMarketAction!toUpdate?marketid='+data[i].marketid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].marketid+')"> [删除] </a></td>';
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
	<input type="hidden" id="marketid" name="marketid" value="" />
	市场表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">市场名字</th>
				<th  style="text-align:center;">市场经度</th>
				<th  style="text-align:center;">市场纬度</th>
				<th  style="text-align:center;">省份代号</th>
				<th  style="text-align:center;">市局代号</th>
				<th  style="text-align:center;">区县代号</th>
				<th  style="text-align:center;">市场所属片区</th>
				<th  style="text-align:center;">市场详细地址</th>
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

