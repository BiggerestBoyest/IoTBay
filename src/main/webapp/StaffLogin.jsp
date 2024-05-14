<%-- 
    Document   : Register
    Created on : 06/04/2024, 4:03:13 PM
    Author     : Owner
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.isd.iotbay.dao.DBManager" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/staff_login.css">
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
         <title>Login Page</title>
         <div class="header">
             <img src="css/IOTBAY Logo.png">
         </div>
    </head>
    <body>
        <div><span>
                
        <div class="Content" >
        <%
            
            String emailError = (String)session.getAttribute("emailError") ;
            String passwordError = (String)session.getAttribute("passwordError");
            String loginError = (String)session.getAttribute("loginError");
        %>
        <h1 align="center">Staff Login</h1>
        <form action="StaffLoginServlet" method="post">
            <table>
                <tr><td><label class ="formText" for="email">Email</label><p><input type="email" placeholder="Enter email" name="email" required="true"></p></td></tr>
                <tr><td><label class ="formText" for="password">Password</label><p><input type="password" placeholder="Enter password" name="password" required="true"></p></td></tr>
            </table>
            <div align="center" class="buttons">
                <table>
                    <tr><td><input type="submit"  value ="Login"></td></tr>
                    <tr><td id="btnLogin">Not a staff? Login <a href="Login.jsp">here</a></td></tr>
                </table>
            </div>
        </form>
        <h1><span class = "message"><%=(emailError != null ? emailError : "")%></span></h1>
        <h1><span class = "message"><%=(passwordError!= null ? passwordError : "")%></span></h1>
        <h1><span class = "message"><%=(loginError != null ? loginError : "")%></span></h1>
        </div>
    </body>
</html>

