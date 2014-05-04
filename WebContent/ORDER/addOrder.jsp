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
<title>�s�W�q��</title>
</head>
<body class="metro">
	<c:if test="${not empty errorMsgs}">
		<font color='red'>�Эץ��H�U���~:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
	<form action="order.do" method="post" name="from1">
		<div class="input-control text">
			<label>�t�e�a�} <input name="ordaddr">
			</label> <label>�s���q�� <input name="ordtel">
			</label>
		</div>
		<div class="input-control select">
			
			<label>�q�檬�A 
			
			<select name="ordstate">
					<option value="0">���X�f</option>
					<option value="1">�w�X�f</option>
			</select>
			
			</label>
		</div>
		<div class="input-control text">
			<label>�|���b�� <input name="memno">
			</label> <label>���z���u�s�� <input name="empno">
			</label>
		</div>
		<input name="action" value=insert type="hidden">
		<input name="submit" type="submit" value="�e�X�q��"> 

	</form>

</body>
</html>