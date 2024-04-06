<%-- 
    Document   : Welcome
    Created on : 06/04/2024, 5:14:24 PM
    Author     : Owner
--%>

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
    
    </head>
    <body>
        <h1>Welcome <%= firstName%> <%= lastName%> </h1>
        <p> Your Email is <%= email %> </p>
        
        <%
            Customer customer = new Customer(firstName,lastName,email,passowrd,dob)
            session.setAttribute("currentCustomer",customer);
        %>
    </body>
</html>
