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
<title>�s��q��</title>
</head>
<body class="metro">
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
		<ul>
			<c:forEach var="message" items="${reeoeMsgs}">
				<li>${message}</li>
			</c:forEach>
		</ul>
	</font>
</c:if>
	<form name="from1" method="post" action="order.do">
		<label>
		�q��s��
		<input name="orderno" disabled="disabled" value="<%=orderVO.getOrdno()%>"/>
		</label>
		<label>
		�q�ʮɶ�
		<input name="ordertime" disabled="disabled" value="<%=orderVO.getOrdtime()%>"/>
		</label>
		<label>
		�t�e�a�}
		<input name="orderaddress" disabled="disabled" value="<%=orderVO.getOrdaddr()%>"/>
		</label>
		<label>
		�|���q��
		<input name="ordertel" disabled="disabled" value="<%=orderVO.getOrdtel()%>"/>
		</label>
		<label>
		�X�f�ɶ�
		<input name="ordergotime" value="<%=orderVO.getOrdgotime()%>"/>
		</label>
		<label>
		�e�F�ɶ�
		<input name="orderarrtime" value="<%=(orderVO.getOrdarrtime()==null)?"���n���ɶ�":orderVO.getOrdarrtime()%>"/>
		</label>
		<label>
		�P��ɶ�
		<input name="orderdeltime"value="<%=(orderVO.getOrddeltime()==null)?"���n���ɶ�":orderVO.getOrddeltime()%>"/>
		</label>
		<label>
		�q�檬�A
		<jsp:useBean id="orderService" scope="page" class="com.order.model.OrderService"/>
		<select name="orderstate">
			<c:forEach var="orderVO" items="${orderService.all}">
			<option value="${orderVO.ordstate}" ${(orderVO.ordstate==0)?'selected':'selected'}>${(orderVO.ordstate==0)?"���X�f":"�w�X�f"}</option>
			</c:forEach>
		</select>
		</label>
		<label>
		�|���s��
		<input name="memno" disabled="disabled" value="<%=orderVO.getMemno()%>">
		</label>
		<label>
		�ӿ�H�s��
		<input name="empno" value="<%=(orderVO.getEmpno()==null)?"":orderVO.getEmpno()%>">
		</label>
		<input name="action" type="hidden" value="update"> 
		<input name="submit" value="��s�q��" type="submit">
	</form>
</body>
</html>