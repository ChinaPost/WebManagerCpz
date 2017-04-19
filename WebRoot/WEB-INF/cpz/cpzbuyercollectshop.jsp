<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家店铺收藏信息表</title>
	<script>
		function toAdd(){
			window.location.href="CpzBuyerCollectShopAction!toAdd?userid=${userid}%26shopid=${shopid}";
		}
		
		
		$(document).on('ready', function() {
			var userid="${userid}";
			$("#userid").val(userid);
			var shopid="${shopid}";
			$("#shopid").val(shopid);
			getAll('CpzBuyerCollectShopAction!list');
		});
		
		
		function search(){
			getAll('CpzBuyerCollectShopAction!list');
		}
		
		function dodel(muserid,mshopid){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzBuyerCollectShopAction!doDelete',
					data:{
userid:muserid,
shopid:mshopid					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzBuyerCollectShopAction!index?userid=${userid}%26shopid=${shopid}";
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
userid:searchInput,
shopid:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].marketid + '</td>';
	 divtext += '<td>' + data[i].shopbusineerangeid + '</td>';
	 divtext += '<td>' + data[i].isdefaultshop + '</td>';
	 divtext += '<td>' + data[i].colletctime + '</td>';
								divtext += '<td ><a href="CpzBuyerCollectShopAction!toUpdate?userid='+data[i].userid+'%26shopid='+data[i].shopid+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].userid+','+data[i].shopid+')"> [删除] </a></td>';
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
	<input type="hidden" id="shopid" name="shopid" value="" />
	买家店铺收藏信息表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">店铺所在市场代号</th>
				<th  style="text-align:center;">店铺经营范围表id</th>
				<th  style="text-align:center;">是否默认店铺0：否1：是</th>
				<th  style="text-align:center;">收藏时间</th>
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

