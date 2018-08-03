<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Enter customer details</h1>
<c:out value="${msg }"></c:out>
<form action="${pageContext.request.contextPath }/add-customer-form" method="POST">
<table>
<tr><td>AccountID:</td><td><input type='text' name='id' value=''></td></tr>
<tr><td>UserID:</td><td><input type='text' name='uid' value=''></td></tr>
<tr><td>Balance:</td><td><input type='text' name='balance' value=''></td></tr>
<tr><td>Password:</td><td><input type='password' name='password'/></td></tr>
	<tr><td colspan='2'><input name="submit" type="submit" value="Add customer"/></td></tr>	
</table>
</form>
</body>
</html>