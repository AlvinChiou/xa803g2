<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>商品查詢</title>
</head>
<body>
<b><h1>查詢商品</h1></b>
<%--錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
	<c:forEach var="message" items="${errorMsgs}">
		<li>${message}</li>
	</c:forEach>
	</ul>
	</font>
</c:if>
<ul>
	<li><a href='<%=request.getContextPath()%>/PRODUCT/listAllProduct.jsp'>顯示所有商品</a></li>
	<li>
		<form method="post" action="<%=request.getContextPath()%>/PRODUCT/product.do">
			<b>請輸入商品編號:</b>
			<input type="text" name="prono">
			<input type="submit" value="查詢商品">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	
	<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
	<li>
		<form method="post" action="<%=request.getContextPath()%>/PRODUCT/product.do">
			<b>請選擇商品編號:</b>
			<select size="1" name="prono">
				<c:forEach var="productVO" items="${proSvc.all}">
					<option value="${productVO.prono}">${productVO.prono}
				</c:forEach>
			</select>
			<input type="submit" value="查詢商品">
			<input type="hidden" name="action" value="getOne_For_Display">
		</form>
	</li>
	<li>
	<sql:setDataSource dataSource="jdbc/TestDB" var="productCategory" scope="application"/>
	<sql:query var="rs" dataSource="${productCategory}">
		SELECT DISTINCT category FROM product
	</sql:query>
		<form method="post" action="<%=request.getContextPath()%>/PRODUCT/product.do">
		<b><font color=blue>查詢條件:</b><br>
		<b>商品編號:</b>
		<input type="text" name="prono"><br>
		<b>商品名稱:</b>
		<input type="text" name="productname"><br>
		<b>商品分類:</b>
		<select name="category">
			<option value="">--請選擇商品分類--
			<c:forEach var="row" items="${rs.rows}" varStatus="status">
				<option value='<c:out value="${row.category}"/>'><c:out value="${row.category}"/>
			</c:forEach>
		</select><br>
		<b>商品狀態:</b>
		<select name="status">
			<option value="">--請選擇商品狀態--
			<option value="0">未上架
			<option value="1">上架中
			<option value="2">已下架
		</select><br>
		<b>商品關鍵字:</b>
		<input type="text" name="keyword">
		<input name="action" value="listPorduct_ByCompositeQuery" type="hidden"><br> 
		<input name="submit" value="查詢商品" type="submit">
		<input name="reset" value="重設條件" type="reset">
	</form>
	</li>
	<b>商品管理</b>
	<li><a href='<%=request.getContextPath()%>/PRODUCT/addProduct.jsp'>新增商品</a></li>
</ul>
</body>
</html>