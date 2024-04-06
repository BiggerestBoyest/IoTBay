<%-- 
    Document   : Register
    Created on : 06/04/2024, 4:03:13 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/register.css">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap" rel="stylesheet">
        <style>
        .formText {
          font-family: "Oswald", sans-serif;
          font-size: 20px;
          
        }
        
          h1 {
          font-family: "Oswald", sans-serif;
          font-size: 40px;
          
        }
        </style>
         <title>Registration Page</title>
         <div class="header">
             <img src="css/IOTBAY Logo.png">
         </div>
    </head>
    <body>
        <div class="Content" >
        <h1 align="center">Become a member</h1>
        <form action="Welcome.jsp" method="post" id="registerForm">
            <table>
                <tr><td><label class ="formText" for="fname">First Name</label><p><input type="text" placeholder="Enter first name" name="fname" required="true"></p></td></tr>
                <tr><td><label class ="formText" for="lname">Last Name</label><p><input type="text" placeholder="Enter last name" name="lname" required="true"></p></td></tr>
                <tr><td><label class ="formText" for="dob">Date of Birth</label><p><input type="date" placeholder="Date of Birth" name="dob" required="true"></p></td></tr>
                <tr><td><label class ="formText" for="email">Email</label><p><input type="email" placeholder="Enter email" name="email" required="true"></p></td></tr>
                <tr><td><label class ="formText" for="password">Password</label><p><input type="password" placeholder="Enter password" name="password" required="true" id="pass"></p></td></tr>
                <tr><td><label class ="formText" for="confirmPassword" >Confirm Password</label><p><input type="password" placeholder="Confirm Password" name="confirmPassword" required="true"  id="confirmPass"></p></td></tr>
            </table>
            <div align="center" class="buttons">
                <table>
                    <tr><td><input type="submit"  value ="Register"></td></tr>
                    <tr><td id="btnRegister">Already have an account. Sign in <a href="Login.jsp">here</a></td></tr>
                </table>
              
            </div>
        </form>
        </div>
    </body>
</html>

<script>
    
     document.getElementById('registerForm').addEventListener('submit', function(event) {
        let firstPass = document.getElementById("pass")
        let secondPass = document.getElementById("confirmPass")
        
         if(firstPass.value != secondPass.value)
         {
            firstPass.style.border = "3px solid red";
            secondPass.style.border = "3px solid red";
            event.preventDefault();
         }
  });
</script>
