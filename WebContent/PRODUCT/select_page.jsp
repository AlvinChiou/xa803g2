<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�ӫ~�d��</title>
</head>
<body>
<b><h1>�d�߰ӫ~</h1></b>
<%--���~��C --%>
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
	<b>�ӫ~�޲z</b>
	<li><a href='<%=request.getContextPath()%>/PRODUCT/addProduct.jsp'>�s�W�ӫ~</a></li>
	<li><a href='<%=request.getContextPath()%>/PRODUCT/listAllProduct.jsp'>�˵��Ҧ��ӫ~</a></li>
	<li>
		<form method="post" action="<%=request.getContextPath()%>/PRODUCT/product.do">
			<b>�п�J�ӫ~�s��:</b>
			<input type="text" name="prono">
			<input type="submit" value="�d�߰ӫ~">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	
	<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
	<li>
		<form method="post" action="<%=request.getContextPath()%>/PRODUCT/product.do">
			<b>�п�ܰӫ~�s��:</b>
			<select size="1" name="prono">
				<c:forEach var="productVO" items="${proSvc.all}">
					<option value="${productVO.prono}">${productVO.prono}
				</c:forEach>
			</select>
			<input type="submit" value="�d�߰ӫ~">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	<li>
	<sql:setDataSource dataSource="jdbc/TestDB" var="productCategory" scope="application"/>
	<sql:query var="rs" dataSource="${productCategory}">
		SELECT DISTINCT category FROM product
	</sql:query>
		<form method="post" action="product.do">
		<b><font color=blue>�г]�w�d�߱���</font></b><br>
		<b>�ӫ~�s��:</b>
		<input type="text" name="prono" value=""><br>
		<b>�ӫ~�W��:</b>
		<input type="text" name="productname" value=""><br>
		<b>�ӫ~����:</b>
		<select name="category">
			<option value="">--�п�ܰӫ~����--
			<c:forEach var="row" items="${rs.rows}" varStatus="status">
				<option value='<c:out value="${row.category}"/>'><c:out value="${row.category}"/>
			</c:forEach>
		</select><br>
		<b>�ӫ~���A:</b>
		<select name="status">
			<option value="">--�п�ܰӫ~���A--
			<option value="0">���W�[
			<option value="1">�W�[��
			<option value="2">�w�U�[
		</select><br>
		<b>�ӫ~����r:</b>
		<input type="text" name="keyword" value="">
		<input name="action" value="listPorduct_ByCompositeQuery" type="hidden"><br> 
		<input name="submit" value="�d�߰ӫ~" type="submit">
		<input name="reset" value="���]����" type="reset">
	</form>
	</li>
	
</ul>
</body>
</html>