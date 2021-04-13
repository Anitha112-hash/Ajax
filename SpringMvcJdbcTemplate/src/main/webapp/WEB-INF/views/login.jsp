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

   //Created / Generates the captcha function    
    function DrawCaptcha()
    {
        var a = Math.ceil(Math.random() * 10)+ '';
     //   var b = Math.ceil(Math.random() * 10)+ ''; 
     	var b = String.fromCharCode(64+Math.random()*10+1); 
        var c = Math.ceil(Math.random() * 10)+ '';  
        var d = Math.ceil(Math.random() * 10)+ '';  
        var e = String.fromCharCode(64+Math.random()*10+1);   
        var f = Math.ceil(Math.random() * 10)+ '';  
        var g = Math.ceil(Math.random() * 10)+ '';  
        var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' '+ f + ' ' + g;
        document.getElementById("txtCaptcha").value = code
    }

    // Validate the Entered input aganist the generated security code function   
    function ValidCaptcha(){
        var str1 = removeSpaces(document.getElementById('txtCaptcha').value);
        var str2 = removeSpaces(document.getElementById('txtInput').value);
        var emailId = document.getElementById('email').value;
        var password = document.getElementById('password').value;
        if(emailId =='' || emailId == null){
		 	alert("Please enter Email Id")
		 	return false;
		 	}
        else if(password =='' || emailId == null){
		 	alert("Please enter Password")
		 	return false;  
		 	}
        if (str1 == str2) {
        	return true;   
  
        }
        alert("Captcha did not match");
        return false;
        
    }

    // Remove the spaces from the entered and generated code
    function removeSpaces(string)
    {
        return string.split(' ').join('');
    }
    
 function resetPassCall(){
	 var emailId = document.getElementById('email').value;
	 
	 if(emailId =='' || emailId == null){
		 	alert("Please enter Email")
		 	return false;  
		 	}
		if(emailId!=null){
			 window.location.href="/SpringMvcJdbcTemplate/forgetpassword?emailId="+emailId;
		 		return true;
		 }
 }
 
    </script>
    
</head>



<body onload="DrawCaptcha();">
	<div align="center">
	<!-- <a href="#" onclick="resetPassCall()">I forgot my password</a> -->
		<h1>User Login</h1>
		
		<form:form id="loginpage" action="loginVerification" method="post" modelAttribute="contact" onsubmit="return ValidCaptcha()">
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
 	<td>Enter Captcha:</td>		
 	</tr>
 	
<tr>
    <td>
        <input type="text" id="txtCaptcha" 
            style="background-image:url(1.jpg); text-align:center; border:none;
            font-weight:bold; font-family:Modern" />
        <input type="button" id="btnrefresh" value="Refresh" onclick="DrawCaptcha();" />
    </td>
</tr>

<tr>
    <td>
        <input type="text" id="txtInput"/>    
    </td>
</tr>

<tr>
				<td colspan="2" align="center"><input type="submit" value="Login" ></td>
				
			</tr>



</table>
	
		</form:form><table>
		<tr>
 <td>
       <div align="center"> <a href="#"  onclick="resetPassCall()" >I forgot my password</a> </div>
    </td>
    
    </tr></table>
	</div>
</body>
</html>