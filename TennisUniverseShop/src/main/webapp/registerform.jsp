<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Page</title>
    <link rel="stylesheet" href="styleLogin.css" type="text/css">
    
    <script src="js/formvalidation.js"></script>
</head>

<div class="login-page">
    <div class="form">
<form class="login-form" action="user" name="registration" method="post" onSubmit="return Validation();">
<input type="hidden" name="action" value="register"> 

     
     <input id="email" type="email" name="email" placeholder="enter email"> 
        
     
     <input id="nome" type="text" name="nome" placeholder="enter name">
     
     
     <input id="cognome" type="text" name="cognome" placeholder="enter surname">
     
     
     <input id="username" type="text" name="username" placeholder="enter username">
     
     
     <input id="nascita" type="date" name="nascita" placeholder="enter date of birth">
     
     
     <input id="password" type="password" name="password" placeholder="enter password"> 
     
     <button type="submit" value="Register"> Create </button>
	 <p class="message">Already registered? <a href="login-form.jsp">Sign In</a></p>
</form>

</div>
</div>
</html>