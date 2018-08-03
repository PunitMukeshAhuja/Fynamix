<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="customer_header.jsp"></c:import>
<h1>Transaction Details:</h1>
<b>Your Account ID: </b> <c:out value="${transact.getUid() }"></c:out><br><br>
<b>Beneficiary Account ID: </b> <c:out value="${transact.getBid() }"></c:out><br><br>
<b>Your Current Balance: </b> <c:out value="${transact.getBalance() }"></c:out><br><br>
<b>Amount to be Transfered: </b> <c:out value="${transact.getDebit() }"></c:out><br><br>
<b>Extra Charges: </b> <c:out value="${transact.getExtra_charges() }"></c:out><br><br>
<c:choose>
   <c:when test="${time != 0}"><b>Transaction will be completed after <c:out value="${time }"></c:out> seconds of confirmation</b><br><br></c:when>
   <c:otherwise><b>Your funds will be transfered immediately</b><br><br></c:otherwise>
</c:choose>
<b>Your balance after funds transfer:</b> <c:out value="${transact.getBalance()-transact.getDebit()-transact.getExtra_charges() }"></c:out><br><br>
<a href="${pageContext.request.contextPath}/confirm-transaction" >Confirm</a>&nbsp;&nbsp;
<a href="${pageContext.request.contextPath}/funds-transfer-form" >Edit</a>&nbsp;&nbsp;
<c:import url="footer.jsp"></c:import>
</body>
</html>