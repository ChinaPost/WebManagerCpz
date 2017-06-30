<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../include.jsp"%>

<%
String activity_class_m = (String)session.getAttribute("activity_class_m");

%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>奖状</title>

	
	   <script language="JavaScript" type="text/javascript"> 
	   
	    //第一种方法  
        function method1(tableid) {  
  var searchInputp1 = $("#searchInput1").val();
   var searchInputp2 = $("#searchInput2").val();
    var searchInputp3 = $("#searchInput3").val();
         	var url="/chinapost/ToExcelAction!exportExcel.do?searchInput1="+searchInputp1+"&searchInput2="+searchInputp2+"&searchInput3="+searchInputp3;  
         	window.open(url,'_self');
  
        }  
        
        
    </script>  
	<script>
		
		
		$(document).on('ready', function() {
	
	
			getAll('/chinapost/ToExcelAction!list.do');
		});
		
		function search(){
			getAll('/chinapost/ToExcelAction!list.do');
		}
		
	
		
		
		
	
		function getAll(tzurl){
		var searchInputp1 = $("#searchInput1").val();
		var searchInputp2 = $("#searchInput2").val();
		var searchInputp3 = $("#searchInput3").val();
	
				$.ajax({
					type:'POST',
					dataType:'json',
					url:tzurl,
					data:{
searchInput1:searchInputp1,
searchInput2:searchInputp2,
searchInput3:searchInputp3
},
					success:function(result){
	
							var divtext = '';
							var headdivtext='';
							var listcolumn =result.listcolumn;
							var listresult =result.listresult;
							var pagenational = result.pageString;
							
							
						
				headdivtext += '<tr style=" text-align:center">';
				for(var i=0;i<listcolumn.length;i++){				
	 headdivtext += '<th   style="text-align:center;width:50px;word-break:break-all">' + listcolumn[i] + '</th>';
							}
								headdivtext += '</tr>';
						
						
						
						
							for(var i=0;i<listresult.length;i++){
								divtext += '<tr style=" text-align:center">';
								
								if(listresult[i] instanceof Array)
								{
								for(var j=0;j<listresult[i].length;j++){
	 divtext += '<td width="50px"  style="word-break:break-all">' + listresult[i][j] + '</td>';
	}
	}else
	{
	 divtext += '<td width="50px"  style="word-break:break-all">' + listresult[i] + '</td>';
	}
	
	
								divtext += '</tr>';
							}
							//divtext += pagenational;
							$("#tblHeader").html(headdivtext);
							$("#newtable tbody").html(divtext);
							$("#pageContent").html(pagenational);
					}
				});
		}
	</script>
	
	 <style type="text/css">  
.mytable {  
    table-layout: fixed;  
    width: 98% border:0px;  
    margin: 0px;  
}  

.mytable tr td {  
    text-overflow: ellipsis; /* for IE */  
    -moz-text-overflow: ellipsis; /* for Firefox,mozilla */  
    overflow: hidden;  
    white-space: nowrap;  
    border: 1px solid;  
    text-align: left  
}  
</style>
</head>
<body style="overflow:auto">



	<div style="padding-left:20px;margin-bottom:10px;" >
	
	
	  活动管理通用报表查询及导出
	<table >
	<tr style=" text-align:center">
	
	<td>select:<textarea id="searchInput1" name="searchInput1" style="width:700px;height:20px;"></textarea></td>
	</tr>
	<tr style=" text-align:center">
	<td>   from : <textarea id="searchInput2" name="searchInput2" style="width:700px;height:20px;"></textarea></td>
	</tr>
	<tr style=" text-align:center">
	<td>where: <textarea id="searchInput3" name="searchInput3" style="width:700px;height:20px;"></textarea></td>
	</tr>
	<tr style=" text-align:center">
	<td>
	<input type="button" value="查询" name = "btn_search" onmouseover="this.style.cursor='hand'" style="width:50px;height:20px;font-size:12px;" class="subBtn" onclick="search()"/>

	<input type="button" value="导出Excel" name = "btn_search" onmouseover="this.style.cursor='hand'" style="width:150px;height:20px;font-size:12px;" class="subBtn" onclick="method1('newtable')"/>
	</td>
	</tr>
	</table>
	
	</div>
	<div id="signContent">
	  <div class="table-list lr10">
	    
	        <table id="newtable" width="100%" >
	          <thead  id="tblHeader">
	            
				
				</thead>
				<tbody id="records">
			    </tbody>
		</table>
	      
	</div>
	<div id="pageContent"></div>
  </div>
	
</body>
</html>

