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
select * from beneficiary
</sql:query>
<div class="panel panel-primary">
      <div class="panel-heading"><h1>Your Beneficiaries</h1></div>     
<font color="blue"><c:out value="${msg }"></c:out></font><BR><BR>
<div class="panel-body">
<b><c:out value="Beneficiary ID"></c:out></b>&nbsp;&nbsp;&nbsp;
<b><c:out value="Beneficiary Name"/></b>&nbsp;&nbsp;&nbsp;
<b><c:out value="Activation"/></b>
<hr>
<c:forEach  var="cat" items="${result.rows }" >
<c:if test="${cat.uid == sessionScope.uid }">
<c:out value="${cat.bid}"></c:out>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<c:out value="${cat.bname}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<c:out value="${cat.enabled}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

<hr>
<br><br>
</c:if>


</c:forEach>
                                                
<a href="${pageContext.request.contextPath }/add-beneficiary-form">Add a beneficiary</a>&nbsp;&nbsp;&nbsp;
<a href="${pageContext.request.contextPath }/funds-transfer-form">Funds Transfer</a>&nbsp;&nbsp;&nbsp;

	  </div>
	  </div>

<c:import url="footer.jsp"></c:import></body>
</html>
