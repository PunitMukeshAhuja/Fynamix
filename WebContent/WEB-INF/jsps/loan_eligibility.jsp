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
<c:import url="base_visitor.jsp"></c:import>
<div class="container-fluid">

    <div class="row">
        <div class="col-sm-12 col-md-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3>Loan eligibility calculator</h3>
                      <c:out value="${msg }"></c:out>
<h3><c:out value="Enter the details: "></c:out></h3>
                                        
                        
                    <form class="form-horizontal"  action="${pageContext.request.contextPath}/calc-loan" method="post" >
                        
                        <div class="form-group">
                            <label class="control-label col-sm-6" for="id_username">
                                Age:
                            </label>
                            <div class="col-sm-6">
                               <input type='text' name='age' value=''> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6" for="id_password">
                                Monthly income(net income):
                            </label>
                            <div class="col-sm-6">
                                <input type='text' name='income'/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6" for="id_password">
                                Ongoing loan E.M.I. :
                            </label>
                            <div class="col-sm-6">
                                <input type='text' name='on_emi'/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-6" for="id_password">
                                Rate of interest :
                            </label>
                            <div class="col-sm-6">
                                8.5%
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <div class="col-sm-offset-6 col-sm-10">
                                <button type="submit" class="btn btn-success">Calculate</button>
                            </div>
                        </div>
                    </form>
                </div>
                </div>
        </div>
    </div>

</div>
<c:if test="${result == 'show' }">
<div class="panel panel-primary">
      <div class="panel-heading"><h1>Loan Eligibility Report</h1></div>
	  <div class="panel-body">
<ul>
	<li>Loan amount: <c:out value="${loan_amount }"></c:out></li>
	<li>Tenure:<c:out value="${ tenure}"></c:out></li>
	<li>E.M.I. :<c:out value="${emi }"></c:out></li> 
	<li>Rate of interest : 8.5%</li> 
	
	
</ul>
</div>
</div>
</c:if>

<c:import url="footer.jsp"></c:import>

</body>
</html>