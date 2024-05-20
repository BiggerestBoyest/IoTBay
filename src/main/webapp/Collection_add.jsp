<%-- 
    Document   : Collection_add
    Created on : 11/05/2024, 5:15:21 PM
    Author     : jogun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.isd.iotbay.Product"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Collection Item</title>
    </head>
    <body>
        
        <% String added = (String)session.getAttribute("added"); %>
        
        <h1>Add Product</h1>
        <form action="AddProductServlet" method="post">
            <table>
                <tr>
                    <td>
                        <label for name="Product_Name">Product Name:</label>
                    </td>
                    <td>
                        <input type='text' id='Product_Name' name='Product_Name'>
                    </td>   
                </tr>
                
                <tr>
                    <td>
                        <label for name="Cost">Price:</label>
                    </td>
                    <td>
                        <input type='text' id='Cost' name='Product_Cost'>
                    </td>   
                </tr>
                
                <tr>
                    <td>
                        <label for name="Product_Stock">Stock:</label>
                    </td>
                    <td>
                        <input type='text' id='Product_Stock' name='Product_Stock'>
                    </td>   
                </tr>
                <tr>
                    <td>
                        <label for name="Product_DeliveryDate">Delivery Date:</label>
                    </td>
                    <td>
                        <input type='text' id='Product_DeliveryDate' name='Product_DeliveryDate'>
                    </td>   
                </tr>
            </table>
            <input type='submit' value='Add Product'>
        </form>
        
        <a href='Index.jsp'>Back to Main Page</a><br>
        <a href='collection_update.jsp'>Edit Product</a><br>

        <span><%= (added!= null ? added : "")%></span>
    </body>
</html>
