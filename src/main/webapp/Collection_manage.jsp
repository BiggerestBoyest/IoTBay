<%-- 
    Document   : Collection_manage
    Created on : 11/05/2024, 5:25:04 PM
    Author     : jogun
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.isd.iotbay.dao.*"%>
<!DOCTYPE html>

<%
    DBManager manager = (DBManager) session.getAttribute("manager");
 %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Collection Management</title>
    </head>
    <body>
        <h1>Collection Management</h1>
        <a href='Collection_add.jsp'>Add Products</a><!-- comment -->
        <br>
        <a href= 'ShowCollectionServlet' method = 'post'>Show Collection</a>
        <br>
        <a href='Collection_search.jsp'>Search Collection</a>
        <br>
        <a hre='admin_home.jsp'>Back to Admin Homepage</a>
    </body>
</html>
