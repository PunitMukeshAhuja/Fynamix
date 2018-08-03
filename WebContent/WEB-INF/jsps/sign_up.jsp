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
                    <h3>Account Activation</h3>
                                        
                        
                    <form class="form-horizontal"  action="${pageContext.request.contextPath}/sign-up" method="post" >
                        
                        <div class="form-group">
                        
                            <label class="control-label col-sm-2" >
                                AccountID:
                            </label>
                            <div class="col-sm-10">
                               <input type='text' name='id' value=''> 
                            </div>
                        </div>
                        <div class="form-group">
                        
                            <label class="control-label col-sm-2" >
                                UserID:
                            </label>
                            <div class="col-sm-10">
                               <input type='text' name='uid' value=''> 
                            </div>
                        </div>
                        <div class="form-group">
                        
                            <label class="control-label col-sm-2" >
                                Username:
                            </label>
                            <div class="col-sm-10">
                               <input type='text' name='username' value=''> 
                            </div>
                        </div>
                        <div class="form-group">
                        
                            <label class="control-label col-sm-2" >
                                Email:
                            </label>
                            <div class="col-sm-10">
                               <input type='text' name='email' value=''> 
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" >
                                Password:
                            </label>
                            <div class="col-sm-10">
                                <input type='password' name='password'/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-success">Activate Account</button>
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