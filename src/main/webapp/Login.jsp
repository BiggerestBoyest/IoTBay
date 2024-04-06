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
        <h1 align="center">Login</h1>
        <form action="Welcome.jsp" method="post">
            <table>
                <tr><td><label class ="formText" for="email">Email</label><p><input type="email" placeholder="Enter email" name="email" required="true"></p></td></tr>
                <tr><td><label class ="formText" for="password">Password</label><p><input type="password" placeholder="Enter password" name="password" required="true"></p></td></tr>
            </table>
            <div align="center" class="buttons">
                <table>
                    <tr><td><input type="submit"  value ="Login"></td></tr>
                    <tr><td id="btnLogin">Don't already have an account. Register <a href="Register.jsp">here</a></td></tr>
                </table>
            </div>
        </form>
        </div>
    </body>
</html>

