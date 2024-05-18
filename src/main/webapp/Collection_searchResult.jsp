<%-- 
    Document   : Collection_searchResult
    Created on : 12/05/2024, 10:08:13 AM
    Author     : jogun
--%>

<%@page import="com.isd.iotbay.Staff"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='com.isd.iotbay.Product' %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results</title>
    </head>
    <body>
        <% 
            Product product = (Product) session.getAttribute("product");
            String found = (String) session.getAttribute("found"); 
            Staff staff = (Staff)session.getAttribute("staff");
        %>
        
        <h1>Search Results</h1>
        
        
        <% if (product != null) {%>
        <table>
            <tr>
                <td>Product ID:</td>
                <td><p><%=product.getProduct_ID()%></p></td>
            </tr>
            <tr>
                <td>Product Name:</td>
                <td><p><%=product.getProduct_name()%></p></td>
            </tr>
            <tr>
                <td>Product Price:</td> 
                <td><p><%=product.getCost()%></p></td>
            </tr>
            <tr>
                <td>Product Stock:</td>
                <td><p><%=product.getProduct_stock()%></p></td> 
            </tr>
            <tr>
                <td>Product Delivery Date:</td>
                <td><p><%=product.getProduct_deliveryDate()%></p></td>                    
            </tr>
        </table>
        <% if(staff != null) { %>
            <a href='Collection_update.jsp'>Update Product</a>
            <a href="Collection_delete.jsp">Delete Product</a>
            <br>
            <%}%>
                        <a href="Collection_search.jsp">Return to Search</a>

        <%} else {%>
        <span><%=(found != null ? found: "")%></span>
        <% } %>    
    </body>
</html>
