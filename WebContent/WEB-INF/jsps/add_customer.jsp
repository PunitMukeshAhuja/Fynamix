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
<c:import url="admin_header.jsp"></c:import>
<div class="container-fluid">

    <div class="row">
        <div class="col-sm-12 col-md-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3>Enter Customer Details</h3>

<c:out value="${msg }"></c:out>
<form class="form-horizontal" action="${pageContext.request.contextPath }/add-customer-form" method="POST">
<div class="form-group">
                            <label class="control-label col-sm-2" for="id_username">
                                AccountID:
                            </label>
                            <div class="col-sm-10">
                               <input type='text' name='id' value=''> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="id_username">
                                UserID:
                            </label>
                            <div class="col-sm-10">
                               <input type='text' name='uid' value=''> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="id_username">
                                Balance:
                            </label>
                            <div class="col-sm-10">
                               <input type='text' name='balance' value=''> 
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="id_password">
                                Password:
                            </label>
                            <div class="col-sm-10">
                                <input type='password' name='password'/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-success">Add Customer</button>
                            </div>
                        </div>
</form>
</div>
</div>
</div>
</div>
</div>
<div class="space"></div>
<c:import url="footer.jsp"></c:import>
</body>
</html>
						