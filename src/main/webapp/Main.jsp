<%-- 
    Document   : Welcome
    Created on : 06/04/2024, 5:14:24 PM
    Author     : Owner
--%>
<link rel="stylesheet" href="css/main.css">
<%@page import="com.isd.assignment1.Customer" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/register.css">
           <%
                Customer customer = (Customer)session.getAttribute("customer");
            %>
        <title>Welcome Page </title>
        <div class="header">
             <img src="css/IOTBAY Logo.png">
             <div class="dropdown" style="float:right" >
                <button class="dropbtn"></button> 
                <div class="dropdown-content">
                    <a href ="#">Show Orders</a>
                    <a href ="#">Edit Profile</a>
                    <a href ="Logout.jsp">Logout</a>
                </div>
             </div>
        </div>
    </head>
    <body>
        <div id="content">
            <table>
            <thead><th>Name</th><th>Email</th><th>Password</th><th>Date of Birth</th></thead>
            <tr><td>${customer.GetName()}</td><td>${customer.GetEmail()}</td><td>${customer.GetPassword()}</td><td>${customer.GetDOB()}</td></tr>
            </table>
        </div>
      
    </body>
</html>
