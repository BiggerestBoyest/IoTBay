<%-- 
    Document   : updateShipment
    Created on : May 18, 2024, 4:23:08 AM
    Author     : Brandon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.isd.iotbay.Shipment" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Update Shipment</title>
</head>
<body>
    <% 
        Shipment shipment = (Shipment) request.getAttribute("shipment");
        if (shipment == null) {
            response.sendRedirect("ViewShipmentServlet");
            return;
        }
    %>
    
    <h1>Update Shipment</h1>
    <form action="UpdateShipmentServlet" method="post">
        <input type="hidden" name="shipmentID" value="<%= shipment.getshipmentID() %>">
        <table>
            <tr>
                <td><label for="shipmentMethod">Shipment Method:</label></td>
                <td><input type="text" id="shipmentMethod" name="shipmentMethod" value="<%= shipment.getshipmentMethod() %>"></td>
            </tr>
            <tr>
                <td><label for="shipmentDate">Shipment Date:</label></td>
                <td><input type="date" id="shipmentDate" name="shipmentDate" value="<%= shipment.getshipmentDate() %>"></td>
            </tr>
            <tr>
                <td><label for="shipmentAddress">Shipment Address:</label></td>
                <td><input type="text" id="shipmentAddress" name="shipmentAddress" value="<%= shipment.getshipmentAddress() %>"></td>
            </tr>
        </table>
        <input type="submit" value="Update Shipment">
    </form>
    
    <a href="ViewShipmentServlet">Back to Shipment Management</a>
</body>
</html>