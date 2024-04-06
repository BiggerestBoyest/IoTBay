<%-- 
    Document   : Welcome
    Created on : 06/04/2024, 5:14:24 PM
    Author     : Owner
--%>
<link rel="stylesheet" href="css/main.css">
<%@page import="com.isd.assignment1.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap" rel="stylesheet">
        <style>
          h1 {
          font-family: "Oswald", sans-serif;
          font-size: 40px;
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
                String firstName = request.getParameter("fname");
                String lastName = request.getParameter("lname");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String dob = request.getParameter("dob");

               /*to delete just test case for a logged in user*/
               if(email.equals("example@gmail.com") && password.equals("example"))
               {
                   firstName = "James";
                   lastName = "Liam";
                   dob = "20-12-2001";
               }
            %>
        <title>Welcome Page </title>
      <div class="header">
             <img src="css/IOTBAY Logo.png">
         </div>
    </head>
    <body>
        <div id="content">
             <h1>Welcome <%= firstName%> <%= lastName%> </h1><p></p>
            <a href="Main.jsp"><input type="button" value="Continue"></a>
        </div>
        <%
            Customer customer = new Customer(firstName,lastName,email,password,dob);
            session.setAttribute("customer",customer);
        %>
    </body>
</html>
