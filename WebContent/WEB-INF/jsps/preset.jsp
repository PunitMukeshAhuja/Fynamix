<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body onload='document.f.username.focus();'>
<c:import url="base_visitor.jsp"></c:import>
<div class="container-fluid">

    <div class="row">
        <div class="col-sm-12 col-md-6">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3>Password Reset</h3>
                                        
                        
                    <form class="form-horizontal"  action="${pageContext.request.contextPath}/sign-up-success" method="post" >
                        
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="id_username">
                                Enter new password : 
                            </label>
                            <div class="col-sm-8">
                               <input type='password' name='password' value=''> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-4" for="id_password">
                                Enter new transaction password : 
                            </label>
                            <div class="col-sm-8">
                                <input type='password' name='transpass'/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-success">Activate</button>
                            </div>
                        </div>
                    </form>
                </div>
               
            </div>
        </div>
    </div>

</div>

<c:import url="footer.jsp"></c:import>
</body>


</html>