<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="com.order.model.*;" %>
<%
OrderVO orderVO = (OrderVO) request.getAttribute("orderVO");
 %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�浧�q����</title>
</head>
<body>
<div bgcolor="yellow" align="center">�浧�q����</div>
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
	</tr>
	<tr align='center' valign='middle'>
		<td><%=orderVO.getOrdno()%></td>
		<td><%=orderVO.getOrdtime()%></td>
		<td><%=orderVO.getOrdaddr()%></td>
		<td><%=orderVO.getOrdtel()%></td>
		<td><%=(orderVO.getOrdgotime()==null?"���X�f":orderVO.getOrdgotime())%></td>
		<td><%=(orderVO.getOrdarrtime()==null?"���e�F":orderVO.getOrdarrtime())%></td>
		<td><%=(orderVO.getOrddeltime()==null?"���P��":orderVO.getOrddeltime())%></td>
		<td><%=orderVO.getOrdstate()%></td>
		<td><%=orderVO.getMemno()%></td>
		<td><%=orderVO.getEmpno()%></td>
	</tr>
</table>

</body>
</html>