<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>平台商品推荐表</title>
	<script>
		function toAdd(){
			window.location.href="CpzPlatProductRecommendAction!toAdd?id=${id}";
		}
		
		
		$(document).on('ready', function() {
			var id="${id}";
			$("#id").val(id);
			getAll('CpzPlatProductRecommendAction!list');
		});
		
		
		function search(){
			getAll('CpzPlatProductRecommendAction!list');
		}
		
		function dodel(mid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzPlatProductRecommendAction!doDelete',
					data:{
id:mid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzPlatProductRecommendAction!index?id=${id}";
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
id:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].productid + '</td>';
	 divtext += '<td>' + data[i].channelno + '</td>';
	 divtext += '<td>' + data[i].saletype + '</td>';
	 divtext += '<td>' + data[i].starttime + '</td>';
	 divtext += '<td>' + data[i].endtime + '</td>';
	 divtext += '<td>' + data[i].createtime + '</td>';
	 divtext += '<td>' + data[i].remark1 + '</td>';
								divtext += '<td ><a href="CpzPlatProductRecommendAction!toUpdate?id='+data[i].id+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].id+')"> [删除] </a></td>';
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
	<input type="hidden" id="id" name="id" value="" />
	平台商品推荐表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">商品代号</th>
				<th  style="text-align:center;">推荐渠道</th>
				<th  style="text-align:center;">推荐标识0：推荐1：热门</th>
				<th  style="text-align:center;">有效开始时间格式：yyyymmdd hh24miss</th>
				<th  style="text-align:center;">有效结束时间格式：yyyymmdd hh24miss</th>
				<th  style="text-align:center;">记录创建时间格式：yyyymmdd hh24miss</th>
				<th  style="text-align:center;">备注</th>
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

