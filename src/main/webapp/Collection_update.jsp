<%-- 
    Document   : Collection_update
    Created on : 12/05/2024, 11:00:42 AM
    Author     : jogun
--%>

<%@page import="com.isd.iotbay.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Product</title>
    </head>
    <body>
        <h1>Update Product</h1>
        
        <%
            Product product = (Product) session.getAttribute("product");
            String updated = (String) session.getAttribute("updated"); 
        %>
        
        <% if (product != null) { %>
        <form action='UpdateProductServlet' method='post'> 
            <table>
                <input type='hidden' id='oldProduct' name='oldProduct' value='${product.getProduct_name()}'><!--  -->
                <tr>
                    <th></th>
                    <th>Current Details </th>
                    <th>New Details </th>
                </tr>
                <tr>
                    <td>
                        <label for='Product_ID'>Product ID</label>
                        
                    </td>
                    <td><p><%=product.getProduct_ID()%></td>
                </tr>
                <tr>
                    <td>
                        <label for name='Product_name'>Product Name</label>
                    </td>
                    <td> 
                        <p><%=product.getProduct_name()%></p>
                    </td>
                    <td>
                        <input type="text" id="Product_name" name="Product_name"><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for name='Product_ID'>Product ID</label>
                    </td>
                    <td> 
                        <p><%=product.getProduct_ID()%></p>
                    </td>
                    <td>
                        <input type="text" id="Product_ID" name="Product_ID"><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for name='Cost'>Product Price</label>
                    </td>
                    <td> 
                        <p><%=product.getCost()%></p>
                    </td>
                    <td>
                        <input type="text" id="Cost" name="Cost"><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for name='Product_stock'>Product Stock</label>
                    </td>
                    <td> 
                        <p><%=product.getProduct_stock()%></p>
                    </td>
                    <td>
                        <input type="text" id="Product_name" name="Product_stock"><br>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for name='Product_deliveryDate'>Product Delivery Date</label>
                    </td>
                    <td> 
                        <p><%=product.getProduct_deliveryDate()%></p>
                    </td>
                    <td>
                        <input type="text" id="Product_deliveryDate" name="Product_deliveryDate"><br>
                    </td>
                </tr>
                <tr>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                        <input type="submit" value="Update Product Details">
                    </td>
                </tr>
                </table>
                
        </form>
                    
        <% } else {%>
        <span><%=(updated != null ? updated : "")%></span>
        <% } %>
        <br>
        <a href='Collection_search.jsp'>Back to Search </a><br>
        <a href='Collection_manage.jsp'>Back to Collection Management</a>
                
        
        
    </body>
</html>
