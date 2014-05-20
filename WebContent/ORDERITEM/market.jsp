<%@ page contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%> 
<% 
	ProductService proSvc =  new ProductService();
	List<ProductVO> list = proSvc.getAll();
	pageContext.setAttribute("list", list);
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�����ө�</title>
</head>
<body>
<c:if test="${not empty ststusMsgs}">
	<font color='blue'>�s�W�ӫ~���A:
	<ul>
		<c:forEach var="message" items="${ststusMsgs}">
			<li>${message} - <a href="<%=request.getContextPath()%>/ORDERITEM/additem.jsp">�˵��ʪ���</a></li>
		</c:forEach>
	</ul>
	</font>
</c:if>
	<table>
		<c:forEach var="productVO" items="${list}">
<form name="shoppingForm" method="post" action="<%=request.getContextPath()%>/ORDERITEM/Shopping.do">
		<tr>
			<td>${productVO.productname}</td>
			<td><div><img alt="�ӫ~�Ӥ�" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=${productVO.prono}&&image=1"></div></td>
			<td>${productVO.description}</td>
			<td>NTD${productVO.price}</td>
			<td>�Ѿl${productVO.quantity}��</td>
			<td>${(productVO.discount)<1?'�S����':''}</td>
			<td><select name="itemqty" ${(productVO.quantity<=0)?'disabled':''}>
				<option value="1" selected>${(productVO.quantity<=0)?'�ݸɳf':'1'}
				<%for(int i = 2 ;i<=5;i++){%>
					<option value="<%=i%>"><%=i%>
				<%}%>				
			</select></td>
			<td>
			<input type="submit" value="�[�J�ʪ���" ${(productVO.quantity<=0)?'disabled':''}>
			<input type="hidden" name="productname" value="${productVO.productname}">
			<input type="hidden" name="price" value="${productVO.price}">
			<input type="hidden" name="prono" value="${productVO.prono}">
			<input type="hidden" name="action" value="ADD">			
			</td>		
		</tr>
</form>
		</c:forEach>
	</table>

</body>
</html>