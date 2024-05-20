<%-- 
    Document   : Product.jsp
    Created on : May 16, 2024, 10:06:21 AM
    Author     : Brandon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.isd.iotbay.Shipment,com.isd.iotbay.dao.DBConnector,com.isd.iotbay.dao.DBManager" %>
<!DOCTYPE html>
<html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add Shipment</title>
</head>
<body>
    <% String added = (String) session.getAttribute("added"); %>
    
    <h1>Add Shipment</h1>
    <form action="CreateShipmentServlet" method="post">
        <table>
            <tr>
                <td><label for="shipmentMethod">Shipment Method:</label></td>
                <td><input type="text" id="shipmentMethod" name="shipmentMethod"></td>
            </tr>
            <tr>
                <td><label for="shipmentDate">Shipment Date:</label></td>
                <td><input type="date" id="shipmentDate" name="shipmentDate"></td>
            </tr>
            <tr>
                <td><label for="shipmentAddress">Shipment Address:</label></td>
                <td><input type="text" id="shipmentAddress" name="shipmentAddress"></td>
            </tr>
        </table>
        <input type="submit" value="Add Shipment">
    </form>
    
    <a href="ViewShipmentServlet">Back to Shipment Management</a><br>
    <span><%= (added != null ? added : "") %></span>
</body>
</html>
