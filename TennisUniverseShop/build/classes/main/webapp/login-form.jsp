<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login form</title>
<link rel="stylesheet" href="styleLogin.css" type="text/css">
</head>
<body>



<div class="login-page">
    <div class="form">
<% String control = (String) request.getAttribute("control");

if(control != null && control.equalsIgnoreCase("Vero")){
%> <p style="color: green">La registrazione è avvenuta correttamente</p>
<%} else if(control != null && control.equalsIgnoreCase("Falso")){
%> <p style="color: red"> email già presente</p>
<% }else if(control != null && (control.equalsIgnoreCase("errorEmail") || control.equalsIgnoreCase("errorPsw") )){ %>

<p style="color: red">Email o password errati.</p>
<% } %>
<form action="user" method="post" class = "login-form"> 
<input type="hidden" name="action" value="login"> 

     
     
     <input id="username" type="text" name="username" placeholder="enter email"> 
        
     <input id="password" type="password" name="password" placeholder="enter password"> 
     
     <button type="submit" value="login">login</button>
     <p class="message">Not registered? <a href="registerform.jsp">Create an account</a></p>

</form> 

</div>
</div>
</body>
</html>
