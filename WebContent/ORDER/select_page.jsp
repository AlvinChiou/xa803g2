<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�d�߭q��</title>
</head>
<body>
<div bgcolor="yellow" align="center">�d�߭q��</div>
<h3>��Ƭd��:</h3>
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</font>
</c:if>
<ul>
	<li><a href="listAllOrder.jsp">List</a>all Orders.</li><br><br>
	<li>
		<form method="post" action="order.do">
			<b>�п�J�q��s��(�Ҧp:201404011001):</b>
			<input type="text" name="ordno">
			<input type="submit" value="�e�X�d��">
			<input type="hidden" name="action" value="getOne_for_Display">
		</form>
	</li>
	<jsp:useBean id="orderService" scope="page" class="com.order.model.OrderService"/>
	<li>
		<form method="post" action="order.do">
			<b>��ܭq��s��:</b>
			<select name="ordno">
				<c:forEach var="orderVO" items="${orderService.all}">
				<option  value="${orderVO.ordno}">${orderVO.ordno}
				</c:forEach>
			</select>
			<input type="submit" value="�e�X�d��">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
</ul>
</body>
</html>