<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	<form>
		<b>商品明細:</b>
		<table>
			<tr align="center">
				<td>商品編號</td>
				<td>商品名稱</td>
				<td>商品數量</td>
				<td>單價</td>
				<td>小計</td>
				<td>可買量</td>
				<td>備註</td>
				<td>刪除項目</td>
			</tr>
			<tr align="center">
				<td><input type="hidden" name="prono"></td>
				<td><input type="hidden" name="productname"></td>
				<td><input type="hidden" name="itemqty"></td>
				<td><input type="hidden" name="price"></td>
				<td>price*qty</td>
				<td>10</td>
				<td><input type="text" name="itemmemo"></td>
				<td>Delete</td>
			</tr>
			
		</table>總金額:<br>
		<input type="submit" value="下一步確認訂購人資料">
		<input type="hidden" name="action" value="checkbuyer">
	</form>
</body>
</html>