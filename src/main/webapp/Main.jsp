<%-- 
    Document   : Welcome
    Created on : 06/04/2024, 5:14:24 PM
    Author     : Owner
--%>
<link rel="stylesheet" href="css/Welcome.css">
<%@page import="com.isd.assignment1.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            %>
        <title>Welcome Page </title>
      <div class="header">
             <img src="css/IOTBAY Logo.png">
         </div>
    </head>
    <body>
        <div id="content"
            <h1>Welcome <%= firstName%> <%= lastName%> </h1>
        <input type="button" onclick=""
        </div>
        <%
            Customer customer = new Customer(firstName,lastName,email,password,dob);
            session.setAttribute("customer",customer);
        %>
    </body>
</html>
