<%-- 
    Document   : Welcome
    Created on : 06/04/2024, 5:14:24 PM
    Author     : Owner
--%>
<%@page import="com.isd.iotbay.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.isd.iotbay.Customer" %>
<%@page import="com.isd.iotbay.AccessLog" %>
<%@page import="com.isd.iotbay.Staff" %>
<%@page import="com.isd.iotbay.Product" %>



<%@page contentType="text/html" pageEncoding="UTF-8"%>


<html>
            <jsp:include page="/ConnServlet" flush="true" />
<link rel="stylesheet" href="css/Index.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Oswald:wght@300&display=swap" rel="stylesheet">
        <style>
          h1 {
          font-family: "Oswald", sans-serif;
          font-size: 40px;
          }
          
          input[type="button"]{
            font-family: "Oswald", sans-serif;
            font-size: 50px;
          }
        
        </style>


    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/main.css">
   <%
                Customer customer = (Customer)session.getAttribute("customer");
                AccessLog currentLog = (AccessLog)session.getAttribute("currentLog");
                Staff staff = (Staff)session.getAttribute("staff");
                ArrayList<Product> products = (ArrayList<Product>)session.getAttribute("allProducts");
                Order order = (Order) session.getAttribute("currentOrder");
                String addedProduct = (String)session.getAttribute("addedProduct");
                String saveSubmitOrder = (String)session.getAttribute("saveSubmitOrder");
                ArrayList<Order> orders = (ArrayList<Order>)session.getAttribute("allOrders");
                
    %>
    <div class="header">
        <a href="Index.jsp" style="background-color: transparent;"><img src="css/IOTBAY Logo.png"></a>
             <div class="dropdown" >
                <button class="dropbtn"></button> 
                <div class="dropdown-content">
                    <a href ="ShowOrderServlet">Show Orders</a>
                    <a href ="AccessLogServlet">Edit Profile</a>
                    <a href ="LogoutServlet">Logout</a>
                    <% if (staff != null){ %>
                    <a href ="Collection_add.jsp">Add Product</a>
                    <%}%>
                </div>
             </div>
                <div class ="sign">
                 <%
                     if(customer == null && staff == null){
                     %>
                <a href="Login.jsp"><label>Sign In</label></a>
                    <%} else {
                    %>
                    <label>Signed in as <%=customer == null ? staff.GetFirstName() : customer.GetFirstName()%></label></a>
                     <% }%>
             </div>
        </div>
    <body>

        <form action="ShowOrderByFilterServlet" method="get">
            <input type="text" name="targetOrderID" style="width:20%">
            <input type="submit" name="GetOrderByID" value="Search by ID">
            <input type="date" name="targetDate" style="width:20%">
            <input type="submit" name="GetOrderByDate" value="Search by Date">
        </form>
        <div id="content">
            <div id="">
                
                    
                    <form action="EditOrderServlet" method="get">
            <%
                if (orders != null){
                    for (Order ord : orders){
            %>
            
            <tr>
                <td><h4>Order #: <%=ord.GetID() %></h4></td>
                <td><input type="submit" name="<%=ord.GetID()%>" value="Edit Order"></td>
            </tr>
                <%}} else {%>
                <h3>No orders found!</h3>
                <%}%>
                         </form>   
         
        </div>
   </body>
</html>
