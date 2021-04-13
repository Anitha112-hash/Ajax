<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Login</title>
<link rel="stylesheet" href="resources/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript">

    
 function resetPassCall(){
	 var emailId = document.getElementById('email').value;
	 
	 if(emailId =='' || emailId == null){
		 	alert("Please enter Email")
		 	return false;  
		 	}
	 if(emailId!=null && emailId =='admin@gmail.com'){
		 	
			/*  window.location.href="/SpringMvcJdbcTemplate/userSearch"; */ 
		 	return true;
		 } 
 }
 
    </script>
    
</head>

<body>
	<div align="center">
	<!-- <a href="#" onclick="resetPassCall()">I forgot my password</a> -->
		<h1>Admin Login</h1>
		
		<form:form id="userSearch" action="userSearch" method="get" modelAttribute="contact" onsubmit="return resetPasssCall()">
		<table>
		
			<tr>
				<td>Email Address:
				<form:input type="email" path="email" /></td>
			</tr>
			<tr>
				<td>Password:
 			 <form:input type="password" path ="password" /> </td>
 				<td class="tooltip"> <i class="fa fa-question-circle" style="font-size:24px"></i>
 					<span class="tooltiptext"> Password must contain <br>
				1.Atleast one number <br> 
				2.Atleast one uppercase and lowercase letter <br>
				3.Atleast 8 or more characters </span></td>
			</tr>
			
		 <tr>
				<td colspan="2" align="center"><input type="submit" value="Login" ></td>
			</tr>
			</table>
			</form:form>
     <!--   <div align="center"> <a href="#"  onclick="resetPassCall()" >Login</a> </div> -->
  
</div>
</body>
</html>