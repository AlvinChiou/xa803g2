<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>  
<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<div bgcolor="yellow">
<b><h1>檢視所有商品</h1></b>
</div>
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
	<li><a href="<%=request.getContextPath()%>/PRODUCT/select_page.jsp">回首頁</a></li>
</ul>
<table border='1'>
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
	<%@ include file="pages/page1.file" %>
	<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	<tr align='center' valign='middle' ${(productVO.prono==param.prono)?'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
		<td>${productVO.prono}</td>
		<td>${productVO.productname}</td>
		<td>${productVO.category}</td>
		<td>${(productVO.price)*(productVO.discount)}</td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></div></td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=2"></div></td>
		<td><div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=3"></div></td>
		<td>${productVO.quantity}</td>
		<td>${productVO.minimumquantity}</td>
		<td>${(productVO.status==0)?"未上架":''}${(productVO.status==1)?"已上架":''}${(productVO.status==2)?"已下架":''}</td>
		<td>${productVO.keyword}</td>
		<td>${productVO.description}</td>
		<td>${productVO.priority}</td>
		<td>${(productVO.discount<1.0)?"優惠中":"無折扣"}</td>
		<td>${productVO.score}</td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/PRODUCT/product.do">
				<input type="submit" value="編輯">
				<input type="hidden" name="prono" value="${productVO.prono}">
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<input type="hidden" name="whichPage" value="<%=whichPage%>">
				<input type="hidden" name="action" value="getOne_For_Update">
			</form>
		</td>
		<td>
			<form method="post" action="<%=request.getContextPath()%>/PRODUCT/product.do">
				<input type="submit" value="刪除">
				<input type="hidden" name="prono" value="${productVO.prono}">
				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
				<input type="hidden" name="whichPage" value="<%=whichPage%>">
				<input type="hidden" name="action" value="delete">
			</form>
		</td>
	</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
</body>
</html>