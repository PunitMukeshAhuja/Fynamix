<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:import url="customer_header.jsp"></c:import>
<!-- fetch list of beneficiaries for customer from database -->
  
<sql:setDataSource user="root" password="" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/fyna" var="ds"/>
 
<sql:query var="result" dataSource="${ds }">
select * from customers
</sql:query>
<div class="panel panel-primary">
      <div class="panel-heading"><h1>Your Portfolio</h1></div>     
<font color="blue"><c:out value="${msg }"></c:out></font><BR><BR>
<div class="panel-body">
<c:forEach  var="cust" items="${result.rows }" >
<c:if test="${cust.uid == sessionScope.uid }">
<b>User ID : </b><c:out value="${cust.uid}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b>Account ID : </b><c:out value="${cust.id}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b>Balance : </b> <c:out value="${cust.balance}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b>Username : </b> <c:out value="${cust.username}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<b>Email : </b> <c:out value="${cust.email}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<hr>
</c:if>
<br><br>

</c:forEach>
                                                

	  </div>
	  </div>

<c:import url="footer.jsp"></c:import></body>


</body>
</html>