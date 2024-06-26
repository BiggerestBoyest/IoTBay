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
        <div id="content">
            <div id="buttonContainer">
                <table>
                    <a href="Collection_search.jsp">Search product</a>
                    
                    <form action="AddProductToOrderServlet" method="post">
            <%
                if (products != null){
                    for (Product pro : products){
            %>
            
            <tr>
                <td><input type="submit" name="<%=pro.getProduct_ID()%>" value="<%=pro.getProduct_ID()%>"></td>
                <td><p><%=pro.getProduct_ID()%></p></td>
                <td><p><%=pro.getProduct_name()%></p></td>
                <td><p><%=pro.getCost()%></p></td>
                <td><p><%=Integer.toString(pro.getProduct_stock())%></p></td>
                <td><p><%=pro.getProduct_deliveryDate()%></p></td>
                        
            </tr>
                <%}}%>
                         </form>   
                </table>
        <span><%=(addedProduct != null ? addedProduct : "")%></span>
            </div>
            <h2> Your Current Order</h2>
            <table>
            <%
                if (order != null){
                    for (Product pro : order.GetProducts()){
            %>
            
            <tr>
                <td><h5><%=pro.getProduct_name()%></h5></td>
                        
            </tr>
                <%}}%>
                </table>
                <div>
                    <a href="SaveOrderServlet">Save Order</a>
                <span><%=(saveSubmitOrder != null ? saveSubmitOrder : "")%></span>
                </div>
        </div>
   </body>
</html>
