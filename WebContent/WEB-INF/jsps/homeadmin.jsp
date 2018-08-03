<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome home admin</h1><br><br>
<c:out value="${msg }"></c:out><br><br>
<security:authorize access="isAuthenticated()">
    authenticated as <security:authentication property="principal.username" var="uid" scope="session" /> 
</security:authorize><br><br>

<a href="${pageContext.request.contextPath }/add-customer">Add Customer</a><br><br>
<a href="${pageContext.request.contextPath }/logout">Logout</a><br><br>
</body>
</html>