<%@page import="java.sql.ResultSet"%>
<%@page import="com.sun.org.apache.xerces.internal.impl.dv.util.Base64"%>
<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.product.model.*" %>
<%@ page import="com.productitem.model.*" %>    
<%--Controller�w�w���N�d�ߧ�������Ʃ�J ProductVO�æs�Jrequest--%>
<%ProductVO productVO = (ProductVO) request.getAttribute("productVO"); %>

<%--�ھ�ProductItemVO���X������productVO����--%>
<% 
	ProdItemService prodItemSvc = new ProdItemService();
	ProdItemVO prodItemVO = prodItemSvc.getOnProdItemVO(productVO.getProno());
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�ӫ~���</title>
</head>
<body>
<div bgcolor="yellow">�ӫ~���</div>
<table border='1'>
	<tr>
		<th>�ӫ~�s��</th>
		<th>�ӫ~�W��</th>
		<th>�ӫ~����</th>
		<th>�ӫ~����</th>
		<th>�D�n�Ϥ�</th>
		<th>���n�Ϥ�2</th>
		<th>���n�Ϥ�3</th>
		<th>�{�s�ƶq</th>
		<th>�w���s�q</th>
		<th>�ӫ~���A</th>
		<th>����r</th>
		<th>�ӫ~²��</th>
		<th>�����ӫ~</th>
		<th>�u���v��</th>
		<th>�馩</th>
		<th>�ӫ~�n��</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=productVO.getProno()%></td>
		<td><%=productVO.getProductname()%></td>
		<td><%=productVO.getCategory()%></td>
		<td><%=productVO.getPrice()%></td>
		<td><img alt="image1" src="data:image/jpg;base64,<%=productVO.getImage1()%>"></td>
		<td><img alt="image2" src="data:image/jpg;base64,<%=productVO.getImage2()%>"></td>
		<td><img alt="image3" src="data:image/jpg;base64,<%=productVO.getImage3()%>"></td>
		<td><%=productVO.getQuantity()%></td>
		<td><%=productVO.getMinimumquantity()%></td>
		<td><%=productVO.getStatus()%></td>
		<td><%=productVO.getKeyword()%></td>
		<td><%=productVO.getDescription()%></td>
		<td><%=productVO.getRelatedProducts()%></td>
		<td><%=productVO.getPriority()%></td>
		<td><%=productVO.getDiscount()%></td>
		<td><%=productVO.getScore()%></td>
	</tr>
</table>
</body>
</html>