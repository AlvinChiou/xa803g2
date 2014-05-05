<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%
	OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
%>
<html>
<head>
<title>編輯訂單</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div bgcolor="yellow" align="center">編輯訂單</div>
	<h3>資料修改:</h3>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${messages}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<form method="post" action="order.do">
		<table border='0' cellpadding='5'>
			<tr>
				<td>訂單編號:</td>
				<td><%=orderVO.getOrdno()%><input type="hidden" name="ordno"
					size="45" value="<%=orderVO.getOrdno()%>"></td>
			</tr>
			<tr>
				<td>訂購時間:</td>
				<td><%=orderVO.getOrdtime()%><input type="hidden"
					name="ordtime" size="45" value="<%=orderVO.getOrdtime()%>"></td>
			</tr>
			<tr>
				<td>配送地址:</td>
				<td><%=orderVO.getOrdaddr()%><input type="hidden"
					name="ordaddr" size="45" value="<%=orderVO.getOrdaddr()%>"></td>
			</tr>
			<tr>
				<td>買方電話:</td>
				<td><%=orderVO.getOrdtel()%><input type="hidden" name="ordtel"
					size="45" value="<%=orderVO.getOrdtel()%>"></td>
			</tr>
			<tr>
				<td>發貨時間:</td>
				<td><input type="text" name="ordgotime" size="45"
					value="<%=orderVO.getOrdgotime()%>"></td>
			</tr>
			<tr>
				<td>到貨時間:</td>
				<td><input type="text" name="ordarrtime" size="45"
					value="<%=orderVO.getOrdarrtime()%>" ${(orderVO.ordgotime==null)?'disabled':''}></td>
			</tr>
			<tr>
				<td>銷單時間:</td>
				<td><input type="text" name="orddeltime" size="45"
					value="<%=orderVO.getOrddeltime()%>" ${(orderVO.ordarrtime==null)?'disabled':''}></td>
			</tr>	
			<tr>
				<td>訂單狀態:</td>
				<td><input type="radio" name="ordstate" value="0" ${(orderVO.ordgotime==null)?'checked':''}>未出貨
					<input type="radio" name="ordstate" value="1" ${(orderVO.ordgotime!=null)?'checked':''}>已出貨</td>
			</tr>
			<tr>
				<td>買方帳號:</td>
				<td><%=orderVO.getMemno()%><input type="hidden" name="memno"
					size="45" value="<%=orderVO.getMemno()%>"></td>
			</tr>
			<tr>
				<td>承辦人編號:</td>
				<td><input type="text" name="empno" size="45"
					value="<%=orderVO.getEmpno()%>" ${(orderVO.empno!=0)?'readonly':''}></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="update"> <input
			type="hidden" name="ordno" value="<%=orderVO.getOrdno()%>"> <input
			type="submit" value="更新訂單資料">
	</form>
</body>
</html>