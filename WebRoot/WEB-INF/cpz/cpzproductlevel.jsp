<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>商品分类表</title>
	<script>
		function toAdd(){
			window.location.href="CpzProductLevelAction!toAdd?productcategoryid=${productcategoryid}";
		}
		
		
		$(document).on('ready', function() {
			var productcategoryid="${productcategoryid}";
			$("#productcategoryid").val(productcategoryid);
			getAll('CpzProductLevelAction!list');
		});
		
		
		function search(){
			getAll('CpzProductLevelAction!list');
		}
		
		function dodel(mproductcategoryid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzProductLevelAction!doDelete',
					data:{
productcategoryid:mproductcategoryid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzProductLevelAction!index?productcategoryid=${productcategoryid}";
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
productcategoryid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].productcategoryname + '</td>';
	 divtext += '<td>' + data[i].parentid + '</td>';
	 divtext += '<td>' + data[i].parentname + '</td>';
	 divtext += '<td>' + data[i].productcategorygrade + '</td>';
	 divtext += '<td>' + data[i].path + '</td>';
								divtext += '<td ><a href="CpzProductLevelAction!toUpdate?productcategoryid='+data[i].productcategoryid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].productcategoryid+')"> [删除] </a></td>';
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
	<input type="hidden" id="productcategoryid" name="productcategoryid" value="" />
	商品分类表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">级别名称</th>
				<th  style="text-align:center;">父级别代号</th>
				<th  style="text-align:center;">父级别名称</th>
				<th  style="text-align:center;">级别等级0：根级别1：第1级2：第2级</th>
				<th  style="text-align:center;">树路径</th>
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

