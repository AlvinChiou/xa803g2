<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.order.model.*"%>
<%
	OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<link rel="stylesheet" href="MetroUI/css/metro-bootstrap.css">
<script src="MetroUI/js/jquery/jquery.min.js"></script>
<script src="MetroUI/js/jquery/jquery.widget.min.js"></script>
<script src="MetroUI/js/metro/metro.min.js"></script>
<title>新增訂單</title>
</head>
<body class="metro">
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
	<form action="order.do" method="post" name="from1">
		<div class="input-control text">
			<label>配送地址 <input name="orderaddress">
			</label> <label>連絡電話 <input name="ordertel">
			</label>
		</div>
		<div class="input-control select">
			
			<label>訂單狀態 
			
			<select name="orderstate">
					<option value="0">未出貨</option>
					<option value="1">已出貨</option>
			</select>
			
			</label>
		</div>
		<div class="input-control text">
			<label>會員帳號 <input name="ordermemno">
			</label> <label>受理員工編號 <input name="empno">
			</label>
		</div>
		<input name="action" value=insert type="hidden">
		<input name="submit" type="submit" value="送出訂單"> 

	</form>

</body>
</html>