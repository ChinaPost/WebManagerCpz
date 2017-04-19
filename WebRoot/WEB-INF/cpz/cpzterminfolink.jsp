<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ include file="../include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>买家或者卖家关联第三方信息表</title>
	<script>
		function toAdd(){
			window.location.href="CpzTerminfoLinkAction!toAdd?systemno=${systemno}";
		}
		
		
		$(document).on('ready', function() {
			var systemno="${systemno}";
			$("#systemno").val(systemno);
			getAll('CpzTerminfoLinkAction!list');
		});
		
		
		function search(){
			getAll('CpzTerminfoLinkAction!list');
		}
		
		function dodel(msystemno){
			var r=confirm("确定要删除吗？");
			if(r){
				$.ajax({
					type:'POST',
					url:'CpzTerminfoLinkAction!doDelete',
					data:{
systemno:msystemno					
},
					success:function(k){
							alert("删除成功！")
							window.location.href = "CpzTerminfoLinkAction!index?systemno=${systemno}";
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
systemno:searchInput					},
					success:function(result){
	
							var divtext = '';
							var data =result.list;
							var pagenational = result.pageString;
						
							for(var i=0;i<data.length;i++){
								divtext += '<tr class="even" style="white-space:nowrap; overflow:hidden; text-align:center">';
	 divtext += '<td>' + data[i].systemtyoe + '</td>';
	 divtext += '<td>' + data[i].linktype + '</td>';
	 divtext += '<td>' + data[i].linkno + '</td>';
	 divtext += '<td>' + data[i].linktime + '</td>';
								divtext += '<td ><a href="CpzTerminfoLinkAction!toUpdate?systemno='+data[i].systemno+'"> [修改] </a>'
								divtext +='|<a href="javascript:void(0);" onclick="dodel('+data[i].systemno+')"> [删除] </a></td>';
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
	<input type="hidden" id="systemno" name="systemno" value="" />
	买家或者卖家关联第三方信息表：<input type="text" id="searchInput" style="margin-left:10px;width:100px;height:20px; "/>
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
	            
				<tr> 				<th  style="text-align:center;">系统类别0:买家1：卖家2：批发商</th>
				<th  style="text-align:center;">第三方代号类型0：微信OPENID1：安卓设备号2：IOS设备号</th>
				<th  style="text-align:center;">第三方代号 如微信公众号对应的OPENID或者系统设备号</th>
				<th  style="text-align:center;">关联时间格式：yyyymmdd hh24miss</th>
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

