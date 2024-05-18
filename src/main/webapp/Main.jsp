<link rel="stylesheet" href="css/main.css">
<%@page import="com.isd.iotbay.Customer" %>
<%@page import="com.isd.iotbay.dao.DBManager" %>
<%@page import="com.isd.iotbay.AccessLog" %>
<%@page import="com.isd.iotbay.Staff" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
FF           <%
                Customer customer = (Customer)session.getAttribute("customer");
                AccessLog currentLog = (AccessLog)session.getAttribute("currentLog");
                Staff staff = (Staff)session.getAttribute("staff");

            %>
        <title>Welcome Page </title>
        <div class="header">
             <img src="css/IOTBAY Logo.png">
             <div class="dropdown" style="float:right" >
                <button class="dropbtn"></button> 
                <div class="dropdown-content">
                    <a href ="#">Show Orders</a>
                    <a href ="AccessLogServlet">Edit Profile</a>
                    <%if(staff != null){
                       %>
                       <a href ="AccessLogServlet">Edit Staff Profile</a>    
                        <%}%>
                    <a href ="LogoutServlet">Logout</a>
                </div>
             </div>
        </div>
    </head>
    <body>
        <div id="content">
            <table>
            <thead><th>Name</th><th>Email</th><th>Password</th><th>Date of Birth</th></thead>
            <tr><td>${staff.GetName()}</td><td>${staff.GetEmail()}</td><td>${staff.GetPassword()}</td><td>${staff.GetDOB()}</td></tr>
            <tr><td>${customer.GetName()}</td><td>${customer.GetEmail()}</td><td>${customer.GetPassword()}</td><td>${customer.GetDOB()}</td></tr>
            </table>
        </div>
      
    </body>
</html>
