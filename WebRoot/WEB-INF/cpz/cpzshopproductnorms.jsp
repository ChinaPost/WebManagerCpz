<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>卖家商品规格表</title>
	<script>
		function toAdd(){
			window.location.href="CpzShopProductNormsAction!toAdd?shopnormsid=${shopnormsid}";
		}
		
		
		$(document).on('ready', function() {
			var shopnormsid="${shopnormsid}";
			$("#shopnormsid").val(shopnormsid);
			getAll('CpzShopProductNormsAction!list');
		});
		
		
		function search(){
			getAll('CpzShopProductNormsAction!list');
		}
		
		function dodel(mshopnormsid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzShopProductNormsAction!doDelete',
					data:{
shopnormsid:mshopnormsid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzShopProductNormsAction!index?shopnormsid=${shopnormsid}";
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
shopnormsid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].shopproductid + '</td>';
	 divtext += '<td>' + data[i].normsname + '</td>';
	 divtext += '<td>' + data[i].normsprice + '</td>';
	 divtext += '<td>' + data[i].remark1 + '</td>';
								divtext += '<td ><a href="CpzShopProductNormsAction!toUpdate?shopnormsid='+data[i].shopnormsid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].shopnormsid+')"> [删除] </a></td>';
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
	<input type="hidden" id="shopnormsid" name="shopnormsid" value="" />
	卖家商品规格表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
				<th  style="text-align:center;">规格名称</th>
				<th  style="text-align:center;">规格对应价格</th>
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

