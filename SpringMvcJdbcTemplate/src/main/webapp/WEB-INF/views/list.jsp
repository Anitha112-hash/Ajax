
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contact Manager Home</title>
    </head>
    <body>
    	<div align="center">
	        <h1>Contact List</h1>
	        <h3><a href="newContact">New Contact</a></h3>
	        <table border="1">
	        	<th>No</th>
	        	<th>Name</th>
	        	<th>Email</th>
	        	<th>Address</th>
	        	<th>Telephone</th>
	        	<th>Action</th>
	        	
				<c:forEach var="contact" items="${listContact}" varStatus="status">
	        	<tr>
	        	<% 
	        	 out.println("inside loop");
	        	%>
	        		 <td>${status.index + 1}</td> 
					<td>${contact.fname}</td>
					<td>${contact.lname}</td>
					<td>${contact.email}</td>
				
					 <td>
						<a href="editContact?id=${contact.contact_id}">Edit</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteContact?id=${contact.contact_id}">Delete</a>
					</td> 
							
	        	</tr>
				</c:forEach>	        	
			</table>
    	</div>
    </body>
     
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>$(document).ready(function(){
    console.log("listready")
    });
    </script>  
</html>


