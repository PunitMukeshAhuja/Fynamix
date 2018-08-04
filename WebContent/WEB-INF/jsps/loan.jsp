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
<c:import url="customer_header.jsp"></c:import>
<h3>Loan Procedure</h3>
<b>Status:<c:out value="${cibil.status}"></c:out></b>
<hr> 
<b>Score:<c:out value="${cibil.score}"></c:out></b>
<div class="space"></div>
<c:import url="footer.jsp"></c:import>
</body>
</html>