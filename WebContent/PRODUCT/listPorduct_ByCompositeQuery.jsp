<%@ page contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="listPorduct_ByCompositeQuery" scope="request" type="java.util.List"/>
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>複合條件查詢商品</title>
</head>
<body>
<b><h1>複合條件查詢商品</h1></b>
<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品分類</th>
		<th>商品價格</th>
		<th>主要圖片</th>
		<th>次要圖片2</th>
		<th>次要圖片3</th>
		<th>現存數量</th>
		<th>安全存量</th>
		<th>商品狀態</th>
		<th>關鍵字</th>
		<th>商品簡介</th>
		<th>優先權值</th>
		<th>折扣</th>
		<th>商品積分</th>
		<th>編輯</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1_ByCompositeQuery.file" %>
	<c:forEach var="productVO" items="${listPorduct_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品分類</th>
		<th>商品價格</th>
		<th>主要圖片</th>
		<th>次要圖片2</th>
		<th>次要圖片3</th>
		<th>現存數量</th>
		<th>安全存量</th>
		<th>商品狀態</th>
		<th>關鍵字</th>
		<th>商品簡介</th>
		<th>優先權值</th>
		<th>折扣</th>
		<th>商品積分</th>
		<th>編輯</th>
		<th>刪除</th>
	</tr>
	</c:forEach>
</table>
</body>
</html>