<%-- 
    Document   : Collection_search
    Created on : 11/05/2024, 5:31:49 PM
    Author     : jogun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.isd.iotbay.Product"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Collection</title>
    </head>
    <body>
        
        <% String found = (String) session.getAttribute("found"); %>
        
        <h1>Search for Product</h1>
        
        <form action='SearchProductServlet' method='post'>
            <label for='Product_Name'>Product Name:</label>
            <input type='text' name='Product_Name'>
            <input type='submit' value='Search'><!-- comment -->
        </form><!-- comment -->
            
        <br>    
        <a href='Index.jsp'>Back to Main Page</a><!-- comment -->
        <br>
        
       
        <span><%=(found != null ? found : "")%></span>
        
    </body>
</html>
