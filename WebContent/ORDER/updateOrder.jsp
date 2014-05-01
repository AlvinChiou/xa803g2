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
<title>編輯訂單</title>
</head>
<body class="metro">
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
		<ul>
			<c:forEach var="message" items="${reeoeMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</font>
</c:if>
	<form name="from1" method="post" action="order.do">
		<label>
		訂單編號
		<input name="orderno" disabled="disabled" value="<%=orderVO.getOrd_No()%>"/>
		</label>
		<label>
		訂購時間
		<input name="ordertime" disabled="disabled" value="<%=orderVO.getOrd_Time()%>"/>
		</label>
		<label>
		配送地址
		<input name="orderaddress" disabled="disabled" value="<%=orderVO.getOrd_Addr()%>"/>
		</label>
		<label>
		會員電話
		<input name="ordertel" disabled="disabled" value="<%=orderVO.getOrd_Tel()%>"/>
		</label>
		<label>
		出貨時間
		<input name="ordergotime" value="<%=orderVO.getOrd_GOTime()%>"/>
		</label>
		<label>
		送達時間
		<input name="orderarrtime" value="<%=(orderVO.getOrd_ArrTime()==null)?"未登錄時間":orderVO.getOrd_ArrTime()%>"/>
		</label>
		<label>
		銷單時間
		<input name="orderdeltime"value="<%=(orderVO.getOrd_DelTime()==null)?"未登錄時間":orderVO.getOrd_DelTime()%>"/>
		</label>
		<label>
		訂單狀態
		<select name="orderstate">
			<option value="0">未出貨</option>
			<option value="1">已出貨</option>
		</select>
		</label>
		<label>
		會員編號
		<input name="memno" disabled="disabled" value="<%=orderVO.getMem_No()%>">
		</label>
		<label>
		承辦人編號
		<input name="empno" value="<%=(orderVO.getEmpNo()==null)?"":orderVO.getEmpNo()%>">
		</label>
		<input name="action" type="hidden" value="update"> 
		<input name="submit" value="更新訂單" type="submit">
	</form>
</body>
</html>