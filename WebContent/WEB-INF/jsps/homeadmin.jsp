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
<c:import url="admin_header.jsp"></c:import>
<h1>Welcome admin</h1><br><br>
<c:out value="${msg }"></c:out><br><br>
<div class="card" style="width: 18rem;">
  <ul class="list-group list-group-flush">
    <li class="list-group-item"><a href="${pageContext.request.contextPath }/add-customer">Add Customer</a></li>
   </ul>
</div>
<div class="space"></div>
<c:import url="footer.jsp"></c:import>

</body>
</html>