<%@ page contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�s��ӫ~���</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	window.onload = function(){
		CKEDITOR.replace( 'description' ,{toolbar: 'Full' , skin :  'moono' });
	}	
	function test() {  
	    var editor_data = CKEDITOR.instances.content.getData();  
	    if (editor_data== null  || editor_data== "" ){  
	        alert( "�ж�g�ӫ~²�����e" );  
	        return  false ;  
	    }  
	}  
</script>
</head>
<body>
<b>�s��ӫ~���</b>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<form method="post" action="product.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>�ӫ~�s��:</td>
			<td>
				<%=productVO.getProno()%>
				<input type="hidden" name="prono">
			</td>
		</tr>
		<tr>
			<td>�ӫ~�W��:</td>
			<td>
				<input type="text" name="productname" size="45" value="<%=productVO.getProductname()%>">
			</td>
		</tr>
		<tr>
			<td>�ӫ~����:</td>
			<td>
				<select name="category">
					<option value="">--�п�ܰӫ~����--
					<option value="���}��" ${(productVO.category=="���}��")?selected="selected":''}>���}��
					<option value="������" ${(productVO.category=="������")?selected="selected":''}>������
					<option value="����A" ${(productVO.category=="����A")?selected="selected":''}>����A
					<option value="���ī~" ${(productVO.category=="���ī~")?selected="selected":''}>���ī~
					<option value="�߹}��" ${(productVO.category=="�߹}��")?selected="selected":''}>�߹}��
					<option value="�ߪ���" ${(productVO.category=="�ߪ���")?selected="selected":''}>�ߪ���
					<option value="�ߦ�A" ${(productVO.category=="�ߦ�A")?selected="selected":''}>�ߦ�A
					<option value="���ī~" ${(productVO.category=="���ī~")?selected="selected":''}>���ī~
					<option value="��L�M��Ϋ~" ${(productVO.category=="��L�M��Ϋ~")?selected="selected":''}>��L�M��Ϋ~
				</select>
			</td>
		</tr>
		<tr>
			<td>���:</td>
			<td><input type="text" name="price" size="45" value="<%=productVO.getPrice()%>"></td>
		</tr>
		<tr>
			<td>�D�n�Ϥ�:</td>
			<td>
				<div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=1"></div><br>
				<input type="file" name="image1">
			</td>
		</tr>
		<tr>
			<td>���n�Ϥ�:</td>
			<td>
			<div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=2"></div><br>
			<input type="file" name="image2"></td>
		</tr>
		<tr>
			<td>���n�Ϥ�:</td>
			<td>
			<div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=3"></div><br>
			<input type="file" name="image3"></td>
		</tr>
		<tr>
			<td>�ӫ~�ƶq:</td>
			<td><input type="text" name="quantity" size="45" value="<%=productVO.getQuantity()%>"></td>
		</tr>
		<tr>
			<td>�w���w�s�q:</td>
			<td><input type="text" name="minimumquantity" size="45" value="<%productVO.getMinimumquantity()%>"></td>
		</tr>
		<tr>
			<td>�ӫ~���A:</td>
			<td>
				<select name="status">				
					<option value="0" ${(productVO.status==0)?selected="selected":''}>���W�[
					<option value="1" ${(productVO.status==1)?selected="selected":''}>�W�[��
					<option value="2" ${(productVO.status==2)?selected="selected":''}>�w�U�[
				</select>
			</td>
		</tr>
		<tr>
			<td>�ӫ~����r:</td>
			<td><textarea cols="40" name="keyword" rows="3" value="<%=productVO.getKeyword()%>"></textarea></td>
		</tr>
		<tr>
			<td>�ӫ~²��:</td>
			<td><textarea class="ckeditor" id="description" cols="80" name="description" rows="10" value="<%=productVO.getDescription()%>"></textarea></td>
		</tr>
		<tr>
			<td>�����ӫ~</td>
			<td>
			<select name="relatedProducts">				
				<option value="">�п��
				<c:forEach var="row" items="${rs.rows}" varStatus="status">
					<option value='<c:out value="${row.prono}"/>'><c:out value="${row.productname}"/>
				</c:forEach>
			</select><br>
			<select	name="relatedProducts" >				
				<option value="">�п��
				<c:forEach var="row" items="${rs.rows}" varStatus="status">
					<option value='<c:out value="${row.prono}"/>'><c:out value="${row.productname}"/>
				</c:forEach>
			</select><br>
			<select	name="relatedProducts">
				<option value="">�п��
				<c:forEach var="row" items="${rs.rows}" varStatus="status">
					<option value='<c:out value="${row.prono}"/>'><c:out value="${row.productname}"/>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td>�u���v��:</td>
			<td><input type="text" name="priority" size="45" value="<%=productVO.getPriority()%>"></td>
		</tr>
		<tr>
			<td>�馩��:<input name="score" value="0" type="hidden"></td>
			<td><input type="text" name="discount" size="45" value="<%=productVO.getPriority()%>">(1.0��ܤ�����)</td>
		</tr>
	</table>
		<input type="hidden" name="prono" value="<%=productVO.getProno()%>">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--������e�X�ק諸�ӷ��������|��,�A�e��Controller�ǳ���椧��-->
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--�Ω�:istAllProduct.jsp �P �ƦX�d�� listProduct_ByCompositeQuery.jsp-->
		<input name="action" value="update" type="hidden">
		<input name="submit" value="�s��ӫ~" type="submit" onclick="test()">
	</form>
</body>
</html>