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
		<input name="orderno" disabled="disabled" value="<%=orderVO.getOrd_No()%>"/>
		</label>
		<label>
		�q�ʮɶ�
		<input name="ordertime" disabled="disabled" value="<%=orderVO.getOrd_Time()%>"/>
		</label>
		<label>
		�t�e�a�}
		<input name="orderaddress" disabled="disabled" value="<%=orderVO.getOrd_Addr()%>"/>
		</label>
		<label>
		�|���q��
		<input name="ordertel" disabled="disabled" value="<%=orderVO.getOrd_Tel()%>"/>
		</label>
		<label>
		�X�f�ɶ�
		<input name="ordergotime" value="<%=orderVO.getOrd_GOTime()%>"/>
		</label>
		<label>
		�e�F�ɶ�
		<input name="orderarrtime" value="<%=(orderVO.getOrd_ArrTime()==null)?"���n���ɶ�":orderVO.getOrd_ArrTime()%>"/>
		</label>
		<label>
		�P��ɶ�
		<input name="orderdeltime"value="<%=(orderVO.getOrd_DelTime()==null)?"���n���ɶ�":orderVO.getOrd_DelTime()%>"/>
		</label>
		<label>
		�q�檬�A
		<select name="orderstate">
			<option value="0">���X�f</option>
			<option value="1">�w�X�f</option>
		</select>
		</label>
		<label>
		�|���s��
		<input name="memno" disabled="disabled" value="<%=orderVO.getMem_No()%>">
		</label>
		<label>
		�ӿ�H�s��
		<input name="empno" value="<%=(orderVO.getEmpNo()==null)?"":orderVO.getEmpNo()%>">
		</label>
		<input name="action" type="hidden" value="update"> 
		<input name="submit" value="��s�q��" type="submit">
	</form>
</body>
</html>