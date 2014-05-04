<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.order.model.*;" %>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>單筆訂單資料</title>
</head>
<body>
<div bgcolor="yellow" align="center">單筆訂單資料</div>
<table border='0' bordercolor='#CCCCFF'>
		<tr>
		<th>訂單編號</th>
		<th>下單時間</th>
		<th>配送地址</th>
		<th>買方連絡電話</th>
		<th>出貨時間</th>
		<th>送達時間</th>
		<th>銷單時間</th>
		<th>訂單狀態</th>
		<th>會員編號</th>
		<th>承辦人員工編號</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=orderVO.getOrdno()%></td>
		<td><%=orderVO.getOrdtime()%></td>
		<td><%=orderVO.getOrdaddr()%></td>
		<td><%=orderVO.getOrdtel()%></td>
		<td><%=(orderVO.getOrdgotime()==null?"未出貨":orderVO.getOrdgotime())%></td>
		<td><%=(orderVO.getOrdarrtime()==null?"未送達":orderVO.getOrdarrtime())%></td>
		<td><%=(orderVO.getOrddeltime()==null?"未銷單":orderVO.getOrddeltime())%></td>
		<td><%=orderVO.getOrdstate()%></td>
		<td><%=orderVO.getMemno()%></td>
		<td><%=orderVO.getEmpno()%></td>
	</tr>
</table>

</body>
</html>