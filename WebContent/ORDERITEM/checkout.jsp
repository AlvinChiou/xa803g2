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
<b><h1>�����ө�-���b</h1></b>
<form method="post" action="<%=request.getContextPath()%>/ORDERITEM/Shopping.do">
	<table>
		<tr bgcolor="#999999">
		<th width="200">�ӫ~�W��</th><th width="100">�ƶq</th><th width="100">���</th><th width="100">�p�p</th>
		<th width="120">�ӫ~�Ƶ�</th>
	</tr>
	<%Vector<ProdItemVO> buylist = (Vector<ProdItemVO>) session.getAttribute("shoppingcart");
		String amount = (String) request.getAttribute("amount");
	%>
	<%
		for(int i = 0; i<buylist.size() ; i++){
	 		ProdItemVO order = buylist.get(i);
	 		Integer quantity = order.getItemqty();
	 		Integer price = order.getPrice();
	 		pageContext.setAttribute("order", order);
	 %>	
		<tr>
			<td>
				<c:forEach	var="productVO" items="${list}">
					<c:if test="${order.prono == productVO.prono}">
						${productVO.productname}
					</c:if>
				</c:forEach>
			</td>
			<td><%=quantity%></td>
			<td><%=price%></td>
			<td><%=quantity * price%></td>
			<td><input type="text" name="itemmemo"></td>
		</tr>
	<% } %>		
		<tr>
			<td></td>
			<td></td>
			<td align="center">�`���B:</td>
			<td align="center"><%=amount%></td>
			<td></td>
		</tr>	
	</table>
	
	<b>�нT�{���f�H��T</b>
	<table>
		<tr>
			<th>�b��</th>
			<th>�H��a�}</th>
			<th>�s���q��</th>
		</tr>
		<tr>
			<td><input type="text" name="memno">
				<!--<input type="hidden" name="memno">-->
			</td>
			<td><input type="text" name="ordaddr"></td>
			<td><input type="text" name="ordtel"></td>
		</tr>
	</table>
	</form>
</body>
</html>