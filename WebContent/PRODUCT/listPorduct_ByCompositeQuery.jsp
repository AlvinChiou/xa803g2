<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:useBean id="listPorduct_ByCompositeQuery" scope="request" type="java.util.List"/>
<jsp:useBean id="proSvc" scope="page" class="com.product.model.ProductService"/>
<html>
<head>
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
			     <input type="submit" value="編輯"> 
			     <input type="hidden" name="empno" value="${productVO.prono}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM></td>
		<td><FORM METHOD="post" ACTION="<%=request.getContextPath()%>/PRODUCT/product.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="empno" value="${productVO.prono}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM></td>
	</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2_ByCompositeQuery.file" %>
</body>
</html>