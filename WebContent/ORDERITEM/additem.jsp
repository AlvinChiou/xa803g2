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
		<b>�ӫ~����:</b>
		<table>
			<tr align="center">
				<td>�ӫ~�s��</td>
				<td>�ӫ~�W��</td>
				<td>�ӫ~�ƶq</td>
				<td>���</td>
				<td>�p�p</td>
				<td>�i�R�q</td>
				<td>�Ƶ�</td>
				<td>�R������</td>
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
			
		</table>�`���B:<br>
		<input type="submit" value="�U�@�B�T�{�q�ʤH���">
		<input type="hidden" name="action" value="checkbuyer">
	</form>
</body>
</html>