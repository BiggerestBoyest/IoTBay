<%-- 
    Document   : searchShipment
    Created on : May 19, 2024, 12:48:37 AM
    Author     : Brandon
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" import="java.util.*, com.isd.iotbay.Shipment" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Shipments</title>
</head>
<body>
    <% String found = (String) session.getAttribute("found"); %>
    
    <h1>Search for Shipment</h1>
    
    <form action="SearchShipmentServlet" method="post">
        <label for="shipmentID">Shipment ID:</label>
        <input type="text" id="shipmentID" name="shipmentID">
        <br>
        <label for="shipmentDate">Shipment Date:</label>
        <input type="date" id="shipmentDate" name="shipmentDate">
        <br>
        <input type="submit" value="Search">
    </form>

    <span><%= (found != null ? found : "") %></span>
</body>
</html>