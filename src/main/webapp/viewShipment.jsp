<%-- 
    Document   : shipment_list
    Created on : May 16, 2024, 11:10:39 AM
    Author     : Brandon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.isd.iotbay.Shipment" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>View Shipments</title>
</head>
<body>
    <% String deleted = (String) request.getAttribute("deleted"); %>
    
    <h1>View Shipments</h1>
    <form action="ViewShipmentsServlet" method="post">
        <table>
            <tr>
                <td><label for="shipmentID">Search by Shipment ID:</label></td>
                <td><input type="text" id="shipmentID" name="shipmentID"></td>
            </tr>
            <tr>
                <td><label for="shipmentDate">Search by Shipment Date:</label></td>
                <td><input type="date" id="shipmentDate" name="shipmentDate"></td>
            </tr>
        </table>
        <input type="submit" value="Search">
    </form>
    
    <table border="1">
        <thead>
            <tr>
                <th>Shipment ID</th>
                <th>Order ID</th>
                <th>Shipment Method</th>
                <th>Shipment Date</th>
                <th>Shipment Address</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Shipment> shipments = (List<Shipment>) request.getAttribute("shipments");
                String updatedShipment = (String)request.getAttribute("updatedShipment");
                if (shipments != null && !shipments.isEmpty()) {
                    for (Shipment shipment : shipments) {
            %>
            <tr>
                <td><%= shipment.getshipmentID() %></td>
                <td><%= shipment.getorderID() %></td>
                <td><%= shipment.getshipmentMethod() %></td>
                <td><%= shipment.getshipmentDate() %></td>
                <td><%= shipment.getshipmentAddress() %></td>
                <td>
                    <form action="UpdateShipmentServlet" method="get" style="display:inline;">
                        <input type="hidden" name="shipmentID" value="<%= shipment.getshipmentID() %>">
                        <input type="submit" value="Update">
                    </form>
                    <form action="DeleteShipmentServlet" method="post" style="display:inline;">
                        <input type="hidden" name="shipmentID" value="<%= shipment.getshipmentID() %>">
                        <input type="submit" value="Delete">
                    </form>
                         <form action="AddShipmentToOrderServlet" method="post" style="display:inline;">
                        <input type="hidden" name="shipmentID" value="<%= shipment.getshipmentID() %>">
                        <input type="submit" value="Add To Order">
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6">No shipment details available.</td>
            </tr>
            <%
                }
            %>
            
        <span><%=updatedShipment == null ? "" : updatedShipment%></span>
            
            
        </tbody>
    </table>
    
    <a href="addShipment.jsp">Add New Shipment</a><br>
    <span><%= (deleted != null ? deleted : "") %></span>
</body>
</html>
