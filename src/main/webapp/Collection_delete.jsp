<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : Collection_delete
    Created on : 12/05/2024, 9:48:37 AM
    Author     : jogun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.isd.iotbay.Product" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Product</title>
    </head>
    <body>
        
        <% 
            Product product = (Product) session.getAttribute("product"); 
            String deleted = (String) session.getAttribute("deleted");
        %>
        
        <h1>Delete Product from Collections</h1>
        
        <% if (product != null ) { %>
        <form action ="DeleteProductServlet" method ="post">
            <table>
                <input type ="hidden" id="oldProduct" name="oldProduct" value ="${Product.getProduct_Name()}">
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
                <tr>
                    <p>Are you sure you want to delete this item? </p>
                </tr>
                <tr>
                    <input type='submit' value ='Delete'>
                </tr>
            </table>
        </form>
                
        <%} else {%>
        <span><%=(deleted != null ? deleted: "")%></span>
        <% } %>
        <br>
        <a href='Collection_search.jsp'>Back to Search </a>
        
        
    </body>
</html>
