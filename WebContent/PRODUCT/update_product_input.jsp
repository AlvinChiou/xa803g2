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
<title>編輯商品資料</title>
</head>
<body>
<b>編輯商品資料</b>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
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
			<td>商品編號:</td>
			<td>
				<%=productVO.getProno()%>
				<input type="hidden" name="prono">
			</td>
		</tr>
		<tr>
			<td>商品名稱:</td>
			<td>
				<input type="text" name="productname" size="45" value="<%=productVO.getProductname()%>">
			</td>
		</tr>
		<tr>
			<td>商品分類:</td>
			<td>
				<select name="category">
					<option value="">--請選擇商品分類--
					<option value="狗飼料" ${(productVO.category=="狗飼料")?selected="selected":''}>狗飼料
					<option value="狗玩具" ${(productVO.category=="狗玩具")?selected="selected":''}>狗玩具
					<option value="狗衣服" ${(productVO.category=="狗衣服")?selected="selected":''}>狗衣服
					<option value="狗藥品" ${(productVO.category=="狗藥品")?selected="selected":''}>狗藥品
					<option value="貓飼料" ${(productVO.category=="貓飼料")?selected="selected":''}>貓飼料
					<option value="貓玩具" ${(productVO.category=="貓玩具")?selected="selected":''}>貓玩具
					<option value="貓衣服" ${(productVO.category=="貓衣服")?selected="selected":''}>貓衣服
					<option value="貓藥品" ${(productVO.category=="貓藥品")?selected="selected":''}>貓藥品
					<option value="其他清潔用品" ${(productVO.category=="其他清潔用品")?selected="selected":''}>其他清潔用品
				</select>
			</td>
		</tr>
		<tr>
			<td>售價:</td>
			<td><input type="text" name="price" size="45" value="<%=productVO.getPrice()%>"></td>
		</tr>
		<tr>
			<td>主要圖片:</td>
			<td>
				<div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=1"></div><br>
				<input type="file" name="image1">
			</td>
		</tr>
		<tr>
			<td>次要圖片:</td>
			<td>
			<div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=2"></div><br>
			<input type="file" name="image2"></td>
		</tr>
		<tr>
			<td>次要圖片:</td>
			<td>
			<div><img alt="image1" src="<%=request.getContextPath()%>/PRODUCT/ProductShowImage.do?prono=<%=productVO.getProno()%>&&image=3"></div><br>
			<input type="file" name="image3"></td>
		</tr>
		<tr>
			<td>商品數量:</td>
			<td><input type="text" name="quantity" size="45" value="<%=productVO.getQuantity()%>"></td>
		</tr>
		<tr>
			<td>安全庫存量:</td>
			<td><input type="text" name="minimumquantity" size="45" value="<%productVO.getMinimumquantity()%>"></td>
		</tr>
		<tr>
			<td>商品狀態:</td>
			<td>
				<select name="status">				
					<option value="0" ${(productVO.status==0)?selected="selected":''}>未上架
					<option value="1" ${(productVO.status==1)?selected="selected":''}>上架中
					<option value="2" ${(productVO.status==2)?selected="selected":''}>已下架
				</select>
			</td>
		</tr>
		<tr>
			<td>商品關鍵字:</td>
			<td><textarea cols="40" name="keyword" rows="3" value="<%=productVO.getKeyword()%>"></textarea></td>
		</tr>
		<tr>
			<td>商品簡介:</td>
			<td><textarea cols="40" name="description" rows="10" value="<%=productVO.getDescription()%>"></textarea></td>
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
			<td><input type="text" name="priority" size="45" value="<%=productVO.getPriority()%>"></td>
		</tr>
		<tr>
			<td>折扣數:<input name="score" value="0" type="hidden"></td>
			<td><input type="text" name="discount" size="45" value="<%=productVO.getPriority()%>">(1.0表示不打折)</td>
		</tr>
	</table>
		<input type="hidden" name="prono" value="<%=productVO.getProno()%>">
		<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
		<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--用於:istAllProduct.jsp 與 複合查詢 listProduct_ByCompositeQuery.jsp-->
		<input name="action" value="update" type="hidden">
		<input name="submit" value="編輯商品" type="submit">
	</form>
</body>
</html>