<link rel="stylesheet" href="css/edit_profile.css">
<%@page import="com.isd.assignment1.Customer" %>
<%@page import="com.isd.iotbay.dao.DBManager" %>
<%@page import="com.isd.iotbay.AccessLog" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/edit_profile.css">
           <%
                Customer customer = (Customer)session.getAttribute("customer");
                AccessLog currentLog = (AccessLog)session.getAttribute("currentLog");
            String emailError = (String)session.getAttribute("emailError") ;
            String passwordError = (String)session.getAttribute("passwordError");
            String loginError = (String)session.getAttribute("loginError");
            %>
        <div class="header">
             <img src="css/IOTBAY Logo.png">
             <div class="dropdown" style="float:right" >
                <button class="dropbtn"></button> 
                <div class="dropdown-content">
                    <a href ="#">Show Orders</a>
                    <a href ="#">Edit Profile</a>
                    <a href ="LogoutServlet">Logout</a>
                </div>
             </div>
        </div>
    </head>
    <body>
        <h1>Current Details</h1>
        <form action="UpdateCustomerServlet" method="post" id="updateForm">
            <table class="customerForm" style="margin-right:auto;margin-left:0px;">
                <tr><td><label class ="formText" for="fname">First Name</label><p><input type="text" placeholder="<%=customer.GetFirstName()%>" name="fname" required="false"></p></td><td><label class ="formText" for="lname">Last Name</label><p><input type="text" placeholder="Enter last name" name="lname" required="false"></p></td></tr>
                <tr><td><label class ="formText" for="dob">Date of Birth</label><p><input type="date" placeholder="Date of Birth" name="dob" required="false"></p></td><td><label class ="formText" for="email">Email</label><p><input style="<%=emailError != null ? "border:3.5px solid #FF0000" : "border:3px solid #cccccc"%>" type="email" placeholder="Enter email" name="email" required="false"></p></td></tr>
                <tr><td><label class ="formText" for="phone">Phone</label><p><input type="number" placeholder="Enter phone" name="phone" required="false"></p></td><td><label class ="formText" for="password">Password</label><p><input type="password" style="<%=passwordError != null ? "border:3.5px solid #FF0000" : "border:3px solid #cccccc"%>" placeholder="Enter password" name="password" required="false" id="pass"></p></td></tr>
                <tr>
                        <td><label class ="formText" for="unit number">Unit Number (Optional)</label><p><input type="number" placeholder="" name="unitNumber" required="false"></p></td> 
                        <td><label class ="formText" for="unit number">Street Number</label><p><input type="number" placeholder="" name="streetNumber" required="false"></p></td>    
                        <td><label class ="formText" for="unit number">Street Address)</label><p><input type="text" placeholder="" name="streetAddress" required="false"></p></td>
                        <td><label class ="formText" for="unit number">Street Type</label><p><input type="text" placeholder="" name="streetType" required="false"></p></td>
                        <td><label class ="formText" for="unit number">State</label><p><input type="text" placeholder="" name="state" required="false"></p></td>
                        <td><label class ="formText" for="unit number">Postcode</label><p><input type="number" placeholder="" name="postcode" required="false"></p></td>
                </tr>
                <tr>
                    <td><label class ="formText" for="Card Number">Card Number</label><p><input type="text" placeholder="Enter last name" name="cardNumber" required="false"></p></td>
                    <td><label class ="formText" for="Expiry Date">Expiry Date</label><p><input type="text" placeholder="Enter last name" name="expiryDate" required="false"></p></td>
                    <td><label class ="formText" for="CVV">CVV</label><p><input type="text" placeholder="Enter last name" name="cvv" required="false"></p></td>
                </tr>
            </table>
            <div align="center" class="buttons">
                <table>
                    <tr><td><input type="submit"  value ="Update Details"></td></tr>
                    <tr><td><input type="submit"  value ="Show Access Logs"></td></tr>
                </table>
      
    </body>
</html>
