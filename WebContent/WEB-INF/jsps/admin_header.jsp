<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Satisfy' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" type="text/css" href="{% static 'styl.css' %}"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<script src="bower_components/moment/moment.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.0/Chart.min.js"></script>
    
</head>
<body style="background-color:#eee">
<nav class="navbar navbar-inverse ">
    <div class="container-fluid">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand brand-image" href="#"><b><font color="yellow"><i>Fynamix Bank</i></font></b></a>
        </div>
        <!--as above part is not under collapse it will remain as it is even after reducing screen size-->

        <!--link the below section under collapse with id to the above defined button-->
        <div class="collapse navbar-collapse options" id="myNavbar">
            <ul class="nav navbar-nav">
			
			<li><a href="${pageContext.request.contextPath }/">Home</a></li>
				
							
				                                 
                <li><a href="${pageContext.request.contextPath }/logout">Logout</a></li>
            
            </ul>
            
        </div>


    </div>
</nav>





</body>
</html>