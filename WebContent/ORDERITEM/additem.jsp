<%@ page contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.* , com.productitem.model.ProdItemVO, com.product.model.*" %>
<jsp:useBean id="shoppingcart" scope="session" type="java.util.List"/>
<jsp:useBean id="prodItemSvc" scope="session" class="com.productitem.model.ProdItemService"/>

<%
	ProductService proSvc = new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>

<%Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session.getAttribute("shoppingcart");%>
<%
	System.out.println("buylist="+((ProdItemVO)(buylist.get(0))).getProno());
 %>
<%if (buylist != null && (buylist.size() > 0)) {%>
	<b>您選購的商品</b>	
	<table>
		<tr bgcolor="#999999">
		<th width="200">商品名稱</th><th width="100">數量</th><th width="100">單價</th><th width="100">小計</th>
		<th width="120"></th>
	</tr>
	<% 
		for(int index = 0;index<buylist.size();index++){
		ProdItemVO order = buylist.get(index);	
		pageContext.setAttribute("order", order);
	%>
	<tr>
		<td width="200" align="left">	
			<c:forEach var="productVO" items="${list}">
				<c:if test="${order.prono==productVO.prono}">
					${productVO.productname}							
				</c:if>
			</c:forEach>
		</td>
		<td width="100" align="center"><%=order.getItemqty()%></td>
		<td width="100" align="right"><%=order.getPrice()%></td>
		<td width="100" align="right"><%=(order.getItemqty()*order.getPrice())%></td>
		<td width="100" align="center">
			<form name="deleteForm" method="post" action="<%=request.getContextPath()%>/ORDERITEM/Shopping.do">
				<input type="hidden" name="action" value="DELETE">
				<input type="hidden" name="del" value="<%=index%>">
				<input type="submit" value="取消">
			</form>
		</td>
	</tr>
	<% } %>
	</table>
	<p>
		<form name="checkoutForm" method="post" action="<%=request.getContextPath()%>/ORDERITEM/Shopping.do">
			<input type="hidden" name="action" value="CHECKOUT">
			<div align="right"><input type="submit" value="付款結帳"></div>
		</form>
<%}%>	
</body>
</html>