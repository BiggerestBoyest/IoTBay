<%-- 
    Document   : Welcome
    Created on : 06/04/2024, 5:14:24 PM
    Author     : Owner
--%>
<link rel="stylesheet" href="css/main.css">
<%@page import="com.isd.iotbay.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap" rel="stylesheet">
        <style>
          h1 {
          font-family: "Oswald", sans-serif;
          font-size: 30px;
          font-style: normal;
          }
          
          input[type="button"]{
            font-family: "Oswald", sans-serif;
            font-size: 50px;
          }
        
        </style>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/register.css">
           <%
                session.invalidate();
            %>
        <title>Welcome Page </title>
      <div class="header">
             <img src="css/IOTBAY Logo.png">
         </div>
    </head>
    <body>
        <div id="content">
            <h1>You have been logged out. Click <a href="Index.jsp">Here</a> to return to the home page</h1><p></p>
        </div>
      
    </body>
</html>
