<%@page import="com.isd.iotbay.Product"%>
<%@page import="com.isd.iotbay.Order"%>
<link rel="stylesheet" href="css/edit_profile.css">
<%@page import="com.isd.iotbay.Customer" %>
<%@page import="com.isd.iotbay.Staff" %>
<%@page import="com.isd.iotbay.dao.DBManager" %>
<%@page import="com.isd.iotbay.AccessLog" %>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/edit_profile.css?version=1">
           <%
                Customer customer = (Customer)session.getAttribute("customer");
                Staff staff = (Staff)session.getAttribute("staff");
                AccessLog currentLog = (AccessLog)session.getAttribute("currentLog");
                String updateStatus = (String)session.getAttribute("updateStatus");
                ArrayList<AccessLog> allLogs = (ArrayList<AccessLog>)session.getAttribute("allLogs");
                String logError = (String) session.getAttribute("logError");
                Order currentOrder = (Order)session.getAttribute("currentOrder");
                 //ArrayList<Product> products = (ArrayList<Product>)session.getAttribute("allProductsFromOrder");
            %>
        <div class="header">
        <a href="Index.jsp" style="background-color: transparent;"><img src="css/IOTBAY Logo.png"></a>
             <div class="dropdown" style="float:right" >
                <button class="dropbtn"></button> 
                <div class="dropdown-content">
                    <a href ="ShowOrderServlet">Show Orders</a>
                    <a href ="AccessLogServlet">Edit Profile</a>
                    <a href ="LogoutServlet">Logout</a>
                </div>
             </div>
        </div>
    </head>
    <body action="AccessLogServlet">
        <h1>Current Details</h1>
        <form action="EditOrderServlet" method="post" id="updateForm">
            
            <table class="customerForm" style="margin-right:auto;margin-left:0px;">
                <tr>
                    <td><label class ="formText" for="orderID">Order ID</label><p><input type="text" value="<%=currentOrder.GetID()%>" name="fname" required="false" readonly></p></td>
                </tr>
                <tr>
                        <td><label class ="formText" for="streetNumber">Street Number</label><p><input type="number" value="<%=currentOrder.GetStreetNumber() %>" name="streetNumber" required="false"></p></td>    
                        <td><label class ="formText" for="streetAddress">Street Address</label><p><input type="text" value="<%= currentOrder.GetStreet() %>" name="streetAddress" required="false"></p></td>
                        <td><label class ="formText" for="streetType">Street Type</label><p><input type="text" value="<%=currentOrder.GetStreetType() %>" name="streetType" required="false"></p></td>
                        <td><label class ="formText" for="suburb">Suburb</label><p><input type="text" value="<%= currentOrder.GetSuburb() %>" name="suburb" required="false"></p></td>
                        <td><label class ="formText" for="state">State</label><p><input type="text" value="<%= currentOrder.GetState() %>" name="state" required="false"></p></td>
                        <td><label class ="formText" for="postcode">Postcode</label><p><input type="number" value="<%= currentOrder.GetPostcode() %>" name="postcode" required="false"></p></td>
                </tr>
            </table>
                 <div align="center" class="buttons">
                <table>
                    <tr><td><input type="submit" formnovalidate  value ="Update Order" name="orderUpdate"></td></tr>
                </table>
                <h1><span class = "message"><%=(updateStatus != null ? updateStatus : "")%></span></h1>

           
            </div>
        </form>
                
                <%if(!currentOrder.IsSubmitted()) {%>
                <a href="RemoveProductFromOrderServlet">Update Products</a>
                <a href="SubmitOrderServlet">Submit Order</a>
                <a href="ExistingOrderServlet">Add Product to Order</a>
                 <%} else {%>
                 <h3> Order submitted! </h3>
                 <% } %>
    
    </body>

