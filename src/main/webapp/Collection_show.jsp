<%-- 
    Document   : Collection_show
    Created on : 12/05/2024, 10:22:44 AM
    Author     : jogun
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.isd.iotbay.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Collections</title>
    </head>
    <body action="ShowCollectionSevlet">
        <%
            ArrayList<Product> collection = (ArrayList<Product>) session.getAttribute("inventory"); 
            String show = (String) session.getAttribute("show");
        %>
        
        <h1>IOTBay Collections</h1>
        <table>
            <tr>
                <th>Product ID </th>
                <th>Product Name</th>
                <th>Price </th>
                <th>Stock </th>
                <th>Delivery Date </th>
            </tr>
            
            <%
                if (collection != null){
                    for (Product products: collection){
            %>
            
            <tr>
                <td><p><%=products.getProduct_ID()%></p></td>
                <td><p><%=products.getProduct_name()%></p></td>
                <td><p><%=products.getCost()%></p></td>
                <td><p><%=products.getProduct_stock()%></p></td>
                <td><p><%=products.getProduct_deliveryDate()%></p></td>
                        
            </tr>
                <%}%>
        </table>
        <br>
        <a href='Collection_manage.jsp'>Back to Collections Management</a><br>
        <%} else { %>
        <span><%=(show != null ? show: "")%></span>
        <%}%>
        
        
        
    </body>
</html>
