<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=5"> 
<head>
<link rel="stylesheet" href="resources/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Change Password</title>
 <script type="text/javascript">
function test(){
	var url = window.location.href.replace(/\/$/,'');
var lastSeg = url.substr(url.lastIndexOf('=') + 1);
document.getElementById("contact_id").value=lastSeg;
}
</script>

</head>
<body>
	<div align="center">
	
		<h1>Change Password</h1>
		
		<script>
    	function myFunction() {

    		var validationFailed = false;
        var pass1 = document.getElementById("password").value;
        var pass2 = document.getElementById("retpass").value;
        if (pass1 != pass2) {
            alert("Passwords Do not match");
            document.getElementById("password").style.borderColor = "#E34234";
            document.getElementById("retpass").style.borderColor = "#E34234";
        }
        else {
        	validationFailed = true;
            alert("Passwords Match!!!");
            document.getElementById("regForm").submit();
       		 }
        if (!validationFailed){
        	return false;
        	
        }
        var url = window.location.href.replace(/\/$/,'');
        var lastSeg = url.substr(url.lastIndexOf('=') + 1);
        alert(lastseg);
        document.getElementById("contact_id").value=lastSeg;
        }
		</script>
		
		<form:form id="changePassword" action="updatepassword" onsubmit="return myFunction()" method="get" modelAttribute="contact">
                  
              
      
      
		<table>
		<form:hidden path="contact_id" value=""/>
	
			<tr>
				<td>New Password:</td>
			 	<td><form:input  type="password" path="password" value="" 
			 	pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" /></td>
			 	<td class="tooltip"><i class="fa fa-question-circle" style="font-size:24px"></i>
			 	<span class="tooltiptext">Password must contain <br> 
				1.Atleast one number <br> 
				2.Atleast one uppercase and lowercase letter <br> 
				3.Atleast 8 or more characters </span></td>
			</tr>
			<tr>
				<td>Retype Password:</td>
 		  		<td><form:input  type="password" path="retpass" value="" 
 		  		pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" /> </td> 
 		  	</tr>  
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save" onclick="test()"></td>
			</tr>
		</table>
 	</form:form> 
	</div>
</body>
</html>