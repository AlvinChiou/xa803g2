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
<script>	
		document.getElementsByClassName('lock').disabled = true;
		document.getElementsByClassName('unlock').disabled = false;	
</script>
<title>編輯訂單</title>
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
	<form name="from1" method="post" action="order.do">
		<label>
		訂單編號
		<input name="ordno" disabled="disabled" value="<%=orderVO.getOrdno()%>"/>
		</label>
		<label>
		訂購時間
		<input name="ordtime" disabled="disabled" value="<%=orderVO.getOrdtime()%>"/>
		</label>
		<label>
		配送地址
		<input name="ordaddr" disabled="disabled" value="<%=orderVO.getOrdaddr()%>"/>
		</label>
		<label>
		會員電話
		<input name="ordaddr" disabled="disabled" value="<%=orderVO.getOrdtel()%>"/>
		</label>
		<label>
		出貨時間
		<input name="ordgotime" value="<%=orderVO.getOrdgotime()%>"/>
		</label>
		<label>
		送達時間
		<input name="ordarrtime" value="<%=(orderVO.getOrdarrtime()==null)?"未登錄時間":orderVO.getOrdarrtime()%>" class="<%=(orderVO.getOrdgotime()==null?"lock":"unlock")%>"/>
		</label>
		<label>
		銷單時間
		<input name="orddeltime"value="<%=(orderVO.getOrddeltime()==null)?"未登錄時間":orderVO.getOrddeltime()%>" class="<%=(orderVO.getOrdarrtime()==null)?"lock":"unlock"%>"/>
		</label>
		<label>
		訂單狀態
		<jsp:useBean id="orderService" scope="page" class="com.order.model.OrderService"/>
		<select name="ordstate">
			<c:forEach var="orderVO" items="${orderService.all}">
			<option value="${orderVO.ordstate}" ${(orderVO.ordstate==0)?'selected':'selected'}>${(orderVO.ordstate==0)?"未出貨":"已出貨"}</option>
			</c:forEach>
		</select>
		</label>
		<label>
		會員編號
		<input name="memno" disabled="disabled" value="<%=orderVO.getMemno()%>">
		</label>
		<label>
		承辦人編號
		<input name="empno" value="<%=(orderVO.getEmpno()==null)?"":orderVO.getEmpno()%>">
		</label>
		<input name="action" type="hidden" value="update"> 
		<input name="submit" value="更新訂單" type="submit">
	</form>
</body>
</html>