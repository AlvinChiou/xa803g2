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
<title>�Ҧ��q����</title>
</head>
<body>
<div bgcolor="yellow" align="center">�Ҧ��q����</div>
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
<table border='0' bordercolor='#CCCCFF'>
	<tr>
		<th>�q��s��</th>
		<th>�U��ɶ�</th>
		<th>�t�e�a�}</th>
		<th>�R��s���q��</th>
		<th>�X�f�ɶ�</th>
		<th>�e�F�ɶ�</th>
		<th>�P��ɶ�</th>
		<th>�q�檬�A</th>
		<th>�|���s��</th>
		<th>�ӿ�H���u�s��</th>
		<th>�ק�</th>
		<th>�R��</th>
	</tr>
	<%@ include file="page1.file" %>
	<c:forEach var="orderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign="middle">
			<td>${orderVO.ordno}</td>
			<td>${orderVO.ordtime}</td>
			<td>${orderVO.ordaddr}</td>
			<td>${orderVO.ordtel}</td>
			<td>${orderVO.ordgotime}</td>
			<td>${orderVO.ordarrtime}</td>
			<td>${orderVO.orddeltime}</td>
			<td>${(orderVO.ordstate)==0?"���X�f":"�w�X�f"}</td>
			<td>${orderVO.memno}</td>
			<td>${orderVO.empno}</td>
			<td>
				<FORM method="post" action="<%=request.getContextPath()%>/order/order.do">
					<input type="submit" value="�ק�q��"/>
					<input type="hidden" name="ordNo" value="${orderVO.ord_No}"/>
					<input type="hidden" name="action" value="getone_For_Update"/>
				</FORM>
			</td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/order/order.do">
					<input type="submit" value="�R���q��"/>
					<input type="hidden" name="ordNo" value="${orderVO.ordno}"/>
					<input type="hidden" name="action" value="delete"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>
</body>
</html>