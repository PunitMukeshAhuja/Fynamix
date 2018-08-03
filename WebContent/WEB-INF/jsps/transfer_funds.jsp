<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
    <%@ taglib prefix="sv" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="customer_header.jsp"></c:import>
<h1>Transfer Funds</h1>
<font color="red"><c:out value="${msg }"></c:out></font><BR><BR>
<!-- s1: create connection -->
<sql:setDataSource user="root" password="" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/fyna" var="ds"/>
<!-- s2:fetch the data -->
<sql:query var="result" dataSource="${ds }">
select * from beneficiary
</sql:query>
<c:set var="y" value="yes"></c:set>
<sv:form action="${pageContext.request.contextPath }/Transfer-details" method="post">
<b>Transfer Type:</b> &nbsp;&nbsp;&nbsp;
<input TYPE="radio" name="type" value="neft" />NEFT&nbsp;&nbsp;&nbsp;
<input TYPE="radio" NAME="type" VALUE="rtgs"/> RTGS&nbsp;&nbsp;&nbsp;
<input TYPE="radio" name="type" value="imps"/>IMPS<br><br>
<input type="hidden" value="${cid }" name="uid">
<b>Your Account no:</b> <%=session.getAttribute("cid") %>&nbsp;&nbsp;&nbsp;
<b>Beneficiary :</b> <select name="bid">
<c:forEach  var="cat" items="${result.rows }" >
<c:if test="${cat.enabled == y }">
<c:if test="${cat.uid == uid }">
<option value="<c:out value="${cat.bid}"></c:out>" >
<c:out value="${cat.bid}"/>---<c:out value="${cat.bname}"/></option>
</c:if>
</c:if>
</c:forEach>

</select><br><br>
<b>Your Balance:</b> <%=session.getAttribute("cbal") %>
<b>Amount to be transfered :</b> <input type="text" name="amount"/><br><br>
<b>Remarks: </b><input type="text" name="details"><br><br>
<input type="submit" value="NEXT"><br><br>
</sv:form>
<c:import url="footer.jsp"></c:import>
</body>
</html>