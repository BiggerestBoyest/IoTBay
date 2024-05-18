<%-- 
    Document   : Product
    Created on : 11/05/2024, 2:43:24 PM
    Author     : jogun
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.isd.iotbay.Product"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
    </head>
    <body>
        
        <%
        ArrayList<Product> inventory = (ArrayList<Product>) session.getAttribute("inventory"); 
        
        %>
        <h1>Product</h1>
        
        
        
        <form method="post" action="CartServlet">
            <table>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th> 
                    <th>Cost</th>
                    <th>Product Stock</th>
                    <th>Product Delivery Date</th>
                </tr>
                <%
                    if (inventory != null) {
                    for (Product product : inventory){
                    }
                %>    
                <tr>
                    <td><p><%=product.getProduct_ID()%></p></td>
                    <td><p><%=product.getProduct_name()%></p></td>
                    <td><p><%=product.getCost()%></p></td>
                    <td><p><%=product.getProduct_stock()%></p></td>
                    <td><p><%=product.getProduct_deliveryDate()%></p></td>
                </tr>
               
        
                        <input type="hidden" name="productName" value="<%=product.getProduct_name()%>">
                        <input type="hidden" name="productID" value="<%=product.getProduct_ID()%>">
                        <input type="hidden" name="price" value="<%=product.getCost()%>">
                        <td><input type="submit" value="Add to Cart"></td>
                    </tr>
                    <%}%>
            </table>    
                
                
            </table>
        </form>     
    </body>
</html>
