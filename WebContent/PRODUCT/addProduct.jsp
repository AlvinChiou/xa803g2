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
<title>新增商品</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ckeditor/ckeditor.js"></script>
<script type="text/javascript">
	window.onload = function(){
		CKEDITOR.replace( 'description' ,{toolbar: 'Full' , skin :  'moono' });
	}
	function test() {  
	    var editor_data = CKEDITOR.instances.content.getData();  
	    if (editor_data== null  || editor_data== "" ){  
	        alert( "請填寫商品簡介內容" );  
	        return  false ;  
	    }  
	}  
</script>
</head>
<body>
<b><h1>新增商品</h1></b>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤
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
			<td>商品名稱:</td>
			<td>
				<input type="text" name="productname" size="45">
			</td>
		</tr>
		<tr>
			<td>商品分類:</td>
			<td>
				<select name="category">
					<option value="">--請選擇商品分類--
					<option value="狗飼料">狗飼料
					<option value="狗玩具">狗玩具
					<option value="狗衣服">狗衣服
					<option value="狗藥品">狗藥品
					<option value="貓飼料">貓飼料
					<option value="貓玩具">貓玩具
					<option value="貓衣服">貓衣服
					<option value="貓藥品">貓藥品
					<option value="其他清潔用品">其他清潔用品
				</select>
			</td>
		</tr>
		<tr>
			<td>售價:</td>
			<td><input type="text" name="price" size="45"></td>
		</tr>
		<tr>
			<td>主要圖片:</td>
			<td><input type="file" name="image1"><input type="file" name="image2"><input type="file" name="image3"></td>
		</tr>
		
		<tr>
			<td>商品數量:</td>
			<td><input type="text" name="quantity" size="45"></td>
		</tr>
		<tr>
			<td>安全庫存量:</td>
			<td><input type="text" name="minimumquantity" size="45"></td>
		</tr>
		<tr>
			<td>商品狀態:</td>
			<td>
				<select name="status">
					<option value="">--請選擇商品狀態--
					<option value="0">未上架
					<option value="1">上架中
				</select>
			</td>
		</tr>
		<tr>
			<td>商品關鍵字:</td>
			<td><textarea cols="80" name="keyword" rows="1"></textarea></td>
		</tr>
		<tr>
			<td>商品簡介:</td>
			<td><textarea class="ckeditor" id="description" cols="80" name="description" rows="10"></textarea></td>
		</tr>
		<tr>
			<td>相關商品</td>
			<td>
			<select name="relatedProducts">
				<option value="">請選擇
				<c:forEach var="row" items="${rs.rows}" varStatus="status">
					<option value='<c:out value="${row.prono}"/>'><c:out value="${row.productname}"/>
				</c:forEach>
			</select><br>
			<select	name="relatedProducts" >
				<option value="">請選擇
				<c:forEach var="row" items="${rs.rows}" varStatus="status">
					<option value='<c:out value="${row.prono}"/>'><c:out value="${row.productname}"/>
				</c:forEach>
			</select><br>
			<select	name="relatedProducts">
				<option value="">請選擇
				<c:forEach var="row" items="${rs.rows}" varStatus="status">
					<option value='<c:out value="${row.prono}"/>'><c:out value="${row.productname}"/>
				</c:forEach>
			</select>
			</td>
		</tr>
		<tr>
			<td>優先權值:</td>
			<td><input type="text" name="priority" size="45"></td>
		</tr>
		<tr>
			<td>折扣數:<input name="score" value="0" type="hidden"></td>
			<td><input type="text" name="discount" size="45" value="1.0">(1.0表示不打折)</td>
		</tr>
	</table>
		<input name="action" value="insert" type="hidden">
		<input name="submit" value="新增商品" type="submit" onclick="test()">
	</form>
</body>
</html>