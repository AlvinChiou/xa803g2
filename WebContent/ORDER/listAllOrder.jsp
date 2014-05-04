<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.order.model.*"%>

<%
	OrderService ordSvc = new OrderService();
	List<OrderVO> list = ordSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>所有訂單資料</title>
</head>
<body>
<div bgcolor="yellow" align="center">所有訂單資料</div>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
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
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign="middle">
			<td>${orderVO.ordno}</td>
			<td>${orderVO.ordtime}</td>
			<td>${orderVO.ordaddr}</td>
			<td>${orderVO.ordtel}</td>
			<td>${(orderVO.ordgotime)==null?"未出貨":orderVO.ordgotime}</td>
			<td>${(orderVO.ordarrtime)==null?"未送達":orderVO.ordarrtime}</td>
			<td>${(orderVO.orddeltime)==null?"未銷單":orderVO.orddeltime}</td>
			<td>${(orderVO.ordstate)==0?"未出貨":"已出貨"}</td>
			<td>${orderVO.memno}</td>
			<td>${orderVO.empno}</td>
			<td>
				<FORM method="post" action="<%=request.getContextPath()%>/ORDER/order.do">
					<input type="submit" value="修改訂單"/>
					<input type="hidden" name="ordno" value="${orderVO.ordno}"/>
					<input type="hidden" name="action" value="getone_For_Update"/>
				</FORM>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/ORDER/order.do">
					<input type="submit" value="刪除訂單"/>
					<input type="hidden" name="ordno" value="${orderVO.ordno}"/>
					<input type="hidden" name="action" value="delete"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>