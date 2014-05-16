<%@ page contentType="text/html; charset=Big5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>�s�W�ӫ~</title>
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
<b>�s�W�ӫ~</b>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
<sql:setDataSource dataSource="jdbc/TestDB" var="relatedProducts" scope="application"/>
	<sql:query var="rs" dataSource="${relatedProducts}">
		SELECT productname FROM product
	</sql:query>
<form method="post" action="product.do" enctype="multipart/form-data">
	<table>
		<tr>
			<td>�ӫ~�W��:</td>
			<td>
				<input type="text" name="productname" size="45">
			</td>
		</tr>
		<tr>
			<td>�ӫ~����:</td>
			<td>
				<select name="category">
					<option value="">--�п�ܰӫ~����--
					<option value="���}��">���}��
					<option value="������">������
					<option value="����A">����A
					<option value="���ī~">���ī~
					<option value="�߹}��">�߹}��
					<option value="�ߪ���">�ߪ���
					<option value="�ߦ�A">�ߦ�A
					<option value="���ī~">���ī~
					<option value="��L�M��Ϋ~">��L�M��Ϋ~
				</select>
			</td>
		</tr>
		<tr>
			<td>���:</td>
			<td><input type="text" name="price" size="45"></td>
		</tr>
		<tr>
			<td>�D�n�Ϥ�:</td>
			<td><input type="file" name="image1"></td>
		</tr>
		<tr>
			<td>���n�Ϥ�:</td>
			<td><input type="file" name="image2"></td>
		</tr>
		<tr>
			<td>���n�Ϥ�:</td>
			<td><input type="file" name="image3"></td>
		</tr>
		<tr>
			<td>�ӫ~�ƶq:</td>
			<td><input type="text" name="quantity" size="45"></td>
		</tr>
		<tr>
			<td>�w���w�s�q:</td>
			<td><input type="text" name="minimumquantity" size="45"></td>
		</tr>
		<tr>
			<td>�ӫ~���A:</td>
			<td>
				<select name="status">
					<option value="">--�п�ܰӫ~���A--
					<option value="0">���W�[
					<option value="1">�W�[��
				</select>
			</td>
		</tr>
		<tr>
			<td>�ӫ~����r:</td>
			<td><textarea cols="40" name="keyword" rows="3"></textarea></td>
		</tr>
		<tr>
			<td>�ӫ~²��:</td>
			<td><textarea class="ckeditor" id="description" cols="80" name="description" rows="10"></textarea></td>
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
			<td><input type="text" name="priority" size="45"></td>
		</tr>
		<tr>
			<td>�馩��:<input name="score" value="0" type="hidden"></td>
			<td><input type="text" name="discount" size="45" value="1.0">(1.0��ܤ�����)</td>
		</tr>
	</table>
		<input name="action" value="insert" type="hidden">
		<input name="submit" value="�s�W�ӫ~" type="submit">
	</form>
</body>
</html>