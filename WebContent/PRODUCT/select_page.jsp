<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>商品查詢</title>
</head>
<body>
<div bgcolor="yellow">查詢商品</div>
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
	<li><a href='<%=request.getContextPath()%>/PRODUCT/listAllProduct.jsp'>List</a></li>
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
</ul>
</body>
</html>