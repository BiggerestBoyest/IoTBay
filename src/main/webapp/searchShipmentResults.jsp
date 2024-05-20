<%-- 
    Document   : searchShipmentResults
    Created on : May 19, 2024, 12:49:25 AM
    Author     : Brandon
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*, com.isd.iotbay.Shipment" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Search Results</title>
</head>
<body>
    <h1>Search Results</h1>
    <table border="1">
        <thead>
            <tr>
                <th>Shipment ID</th>
                <th>Order ID</th>
                <th>Shipment Method</th>
                <th>Shipment Date</th>
                <th>Shipment Address</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Shipment> shipments = (List<Shipment>) request.getAttribute("shipments");
                if (shipments != null && !shipments.isEmpty()) {
                    for (Shipment shipment : shipments) {
            %>
            <tr>
                <td><%= shipment.getShipmentID() %></td>
                <td><%= shipment.getOrderID() %></td>
                <td><%= shipment.getShipmentMethod() %></td>
                <td><%= shipment.getShipmentDate() %></td>
                <td><%= shipment.getShipmentAddress() %></td>
            </tr>
            <%  
                    }
                } else {
            %>
            <tr>
                <td colspan="5">No shipments found.</td>
            </tr>
            <% 
                }
            %>
        </tbody>
    </table>
    <br>
    <a href="viewshipments.jsp">Back to View Shipments</a>
</body>
</html>
