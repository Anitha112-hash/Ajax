<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta name="viewport" content="width=device-width, initial-scale=0.95"> 

<head>
<script type="text/javascript">
function resetPassCall(){
	 var fname = document.getElementById('fname').value;
	 var lname = document.getElementById('lname').value;
	 var emailId = document.getElementById('email').value;
	 var password = document.getElementById('password').value;
	 if(fname =='' || emailId == null){
		 	alert("Please enter First name")
		 	return false;  
		 	/* document.getElementById("saveContact").reset(); */
		 	}
	 else if(lname =='' || emailId == null){
		 	alert("Please enter Last name")
		 	return false;  
		 	}
	 else if(emailId =='' || emailId == null){
		 	alert("Please enter Email Id")
		 	return false;  
		 	}
	 else if(password =='' || emailId == null){
		 	alert("Please enter Password")
		 	return false;  
		 	}
	 else
		 
	 return true; 
		 
}

</script>


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>user registration</title>
<link rel="stylesheet" href="resources/style.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>



<body>



	<div align="center">
	
		<h1>User Registration Form</h1>
		
		<form:form id="saveContact" action="saveContact" method="post" modelAttribute="contact" onsubmit="return resetPassCall()">
		<table>
			<form:hidden path="contact_id"/>

			
			<tr>
				<td>First Name:</td>
				<td><form:input type="text" path="fname"/></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><form:input type="text" path="lname" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input type="email" path="email"  pattern="[^ @]*@[^ @]*" placeholder="Enter your email"/></td>
			</tr>
			<tr >
				<td>Password:</td>
 		 		<td><form:input type="password" path ="password" placeholder="Enter your password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}" oninvalid="setCustomValidity('Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters ' )" onchange="try{setCustomValidity('')}catch(e){}" /> </td>
 				<td></td>
 				<td class="tooltip"><i class="fa fa-question-circle" style="font-size:24px"></i>
 					<span class="tooltiptext">Password must contain <br>
						1.Atleast one number <br> 
						2.Atleast one uppercase and lowercase letter <br>
						3.Atleast 8 or more characters</span></td>

 		 </tr>
			
		<tr>
				<td colspan="2" align="center"><input type="submit" value="Signup" ></td>
			
		</tr>
    	</table>
    </form:form>
    			
    	 <form:form id="loginpage" action="checkLogin" method="post" modelAttribute="contact"> 
			<table>
			<tr>
				  <td align="center"><input type="submit" value="  Login        "></td> 
		 </tr>
	
	 	</table>
	</form:form> 
	

	
	</div>
</body>
</html>
