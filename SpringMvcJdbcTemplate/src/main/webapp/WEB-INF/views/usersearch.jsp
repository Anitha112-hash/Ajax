<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<!-- <meta name="viewport" content="width=device-width, initial-scale=1"> -->
</head>
<!-- <link rel="stylesheet" href="resources/style.css"> -->
<body>

<h3>User Search</h3>

<div class="container">
<%--  <form:form id="userSearch" action="userSearch" method="post" modelAttribute="contact"> --%>
<label for="contactid">Contact-Id</label>
    <input type="text" id="fname" name="firstname" >
    <label for="fname">First Name</label>
    <input type="text" id="fname" name="firstname" >

    <label for="lname">Last Name</label>
    <input type="text" id="lname" name="lastname" >
    <button class="open-button" onclick="openForm()" >Add NewUser</button>
    <br>

<!-- <div id="search">
  <ul>
  <li>contact</li>
  <li>link</li>
  </ul>
  </div> -->
 <!--  <input id="search" align ="middle" type="submit" value="Search" onclick="location.href='/SpringMvcJdbcTemplate/listContact';"> -->

<input id="search" align ="middle" type="submit" value="Search">


<div class="form-popup" id="saveAdminContact">
<form:form id="saveAdminContact" action="saveAdminContact" method="post" modelAttribute="contact" class="form-container">
 <link rel="stylesheet" href="resources/home.css"> 
 <form:hidden path="contact_id"/>

    <label for="Fname"><b>Firstname</b></label>
    <input type="text" id="firstName" name="Fname">
      <label for="lname"><b>lastname</b></label>
    <input type="text" id="lastName" name="lname"><br>
  
    <label for="email"><b>Email</b></label>
    <input type="text" name="email"><br>

    <label for="password"><b>Password</b></label>
    <input type="password"id="password" name="password">
    <!-- <label for="psw"><b>Confirm Password</b></label>
    <input type="password" id="password"  name="psw"><br> -->

    <button type="submit" class="btn">save</button>
    <button type="button" class="btn cancel" onclick="closeForm()">Cancel</button>
 
 </form:form>
</div>


<script>
 
function openForm() {
  document.getElementById("saveAdminContact").style.display = "block";
}

function closeForm() {
  document.getElementById("saveAdminContact").style.display = "none";
}
 /*  function new_element(){
	$("#search ul").append('<li>java</li>');
	} */  
</script> 

</div>

</body>
<!-- <script
src="https://code.jquery.com/jquery-git.js"></script>
 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 

    <script type="text/javascript">
   /*  function new_element(){ */
	  
    $(document).ready(function(){
    $("#search").click(function(){
    	console.log("fetched list");
    	$.ajax({
    		url: "http://localhost:8080/SpringMvcJdbcTemplate/listContact",
    		type : "GET",
    		dataType : 'json',
    		/* contentType : "application/json", */
    		accept : "application/json",
    		
    		success : function(data) {
    			alert(this.getResponseHeader("Content-Type"));
    			console.log("SUCCESS: ", data);
    		  display(data);
    	  },
    	  error : function(e) {
              console.log("ERROR: ", e);
              display(e);
          }
    	
    	});
    	});
    });
    function display(data) {
    	console.log("inside func list");
    	var json = "<h4>Ajax Response</h4>&lt;pre&gt;"
   
                + JSON.stringify(data, null, 4) + "&lt;/pre&gt;";
        $('#feedback').html(json);
    }
    
    </script>
    
</html>


