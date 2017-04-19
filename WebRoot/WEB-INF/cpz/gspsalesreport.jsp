<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>销量统计表</title>
	<script>
		function toAdd(){
			window.location.href="GspSalesReportAction!toAdd?salesreportid=${salesreportid}";
		}
		
		
		$(document).on('ready', function() {
			var salesreportid="${salesreportid}";
			$("#salesreportid").val(salesreportid);
			getAll('GspSalesReportAction!list');
		});
		
		
		function search(){
			getAll('GspSalesReportAction!list');
		}
		
		function dodel(msalesreportid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'GspSalesReportAction!doDelete',
					data:{
salesreportid:msalesreportid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "GspSalesReportAction!index?salesreportid=${salesreportid}";
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
salesreportid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].datestr + '</td>';
	 divtext += '<td>' + data[i].merchname + '</td>';
	 divtext += '<td>' + data[i].weight + '</td>';
	 divtext += '<td>' + data[i].settsumprice + '</td>';
								divtext += '<td ><a href="GspSalesReportAction!toUpdate?salesreportid='+data[i].salesreportid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].salesreportid+')"> [删除] </a></td>';
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
	<input type="hidden" id="salesreportid" name="salesreportid" value="" />
	销量统计表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">时间（以天为单位）</th>
				<th  style="text-align:center;">商品名称</th>
				<th  style="text-align:center;">重量</th>
				<th  style="text-align:center;">结算费用</th>
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

