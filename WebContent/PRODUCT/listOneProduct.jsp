<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ page import="com.product.model.*" %>
<%@ page import="com.productitem.model.*" %>    
<%--Controller已預先將查詢完畢的資料放入 ProductVO並存入request--%>
<%ProductVO productVO = (ProductVO) request.getAttribute("productVO"); %>

<%--根據ProductItemVO取出對應的productVO物件--%>
<% 
	ProdItemService prodItemSvc = new ProdItemService();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>商品資料</title>
</head>
<body>
<div>商品資料</div>
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
		<th>相關商品</th>
		<th>優先權值</th>
		<th>折扣</th>
		<th>商品積分</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=productVO.getProno()%></td>
		<td><%=productVO.getProductname()%></td>
		<td><%=productVO.getCategory()%></td>
		<td><%=productVO.getPrice()%></td>
		<td><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=1"></td>
		<td><img alt="image2" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=2"></td>
		<td><img alt="image3" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=3"></td>
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