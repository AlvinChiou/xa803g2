<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="listPorduct_ByCompositeQuery" scope="request" type="java.util.List"/>
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
<html>
<head>
<title>�ƦX����d�߰ӫ~</title>
</head>
<body>
<b><h1>�ƦX����d�߰ӫ~</h1></b>
<table>
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
		<th>�u���v��</th>
		<th>�馩</th>
		<th>�ӫ~�n��</th>
		<th>�s��</th>
		<th>�R��</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="productVO" items="${listPorduct_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr align='center' valign='middle' ${(empVO.empno==param.empno) ? 'bgcolor=#CCCCFF':''}>
		<td>${productVO.prono}</td>
		<td>${productVO.productname}</td>
		<td>${productVO.category}</td>
		<td>${productVO.price}</td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></div></td>
		<td><div><img alt="image2" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=2"></div></td>
		<td><div><img alt="image3" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=3"></div></td>
		<td>${productVO.quantity}</td>
		<td>${productVO.minimumquantity}</td>
		<td>${productVO.status}</td>
		<td>${productVO.keyword}</td>
		<td>${productVO.description}</td>
		<td>${productVO.priority}</td>
		<td>${productVO.discount}</td>
		<td>${productVO.score}</td>
		<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PRODUCT/product.do">
			     <input type="submit" value="�s��"> 
			     <input type="hidden" name="empno" value="${productVO.prono}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM></td>
		<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PRODUCT/product.do">
			    <input type="submit" value="�R��">
			    <input type="hidden" name="empno" value="${productVO.prono}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			    <input type="hidden" name="action"value="delete"></FORM></td>
	</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>
</body>
</html>