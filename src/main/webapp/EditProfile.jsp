<link rel="stylesheet" href="css/edit_profile.css">
<%@page import="com.isd.assignment1.Customer" %>
<%@page import="com.isd.iotbay.dao.DBManager" %>
<%@page import="com.isd.iotbay.AccessLog" %>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/edit_profile.css">
           <%
                Customer customer = (Customer)session.getAttribute("customer");
                AccessLog currentLog = (AccessLog)session.getAttribute("currentLog");
                String emailError = (String)session.getAttribute("emailError") ;
                String passwordError = (String)session.getAttribute("passwordError");
                String loginError = (String)session.getAttribute("loginError");
                String editStatus = (String)session.getAttribute("editStatus");
                ArrayList<AccessLog> allLogs = (ArrayList<AccessLog>)session.getAttribute("allLogs");
                
            %>
        <div class="header">
             <img src="css/IOTBAY Logo.png">
             <div class="dropdown" style="float:right" >
                <button class="dropbtn"></button> 
                <div class="dropdown-content">
                    <a href ="#">Show Orders</a>
                    <a href ="#">Edit Profile</a>
                    <a href ="LogoutServlet">Logout</a>
                </div>
             </div>
        </div>
    </head>
    <body action="AccessLogServlet">
        <h1>Current Details</h1>
        <form action="EditServlet" method="post" id="updateForm">
            <table class="customerForm" style="margin-right:auto;margin-left:0px;">
                <tr><td><label class ="formText" for="fname">First Name</label><p><input type="text" placeholder="<%=customer.GetFirstName()%>" name="fname" required="false"></p></td><td><label class ="formText" for="lname">Last Name</label><p><input type="text" placeholder="<%=customer.GetLastName()%>" name="lname" required="false"></p></td></tr>
                <tr><td><label class ="formText" for="dob">Date of Birth</label><p><input type="date" value="<%=customer.GetDOB()%>" name="dob" required="false"></p></td><td><label class ="formText" for="email">Email</label><p><input style="<%=emailError != null ? "border:3.5px solid #FF0000" : "border:3px solid #cccccc"%>" type="email" placeholder="<%=customer.GetEmail()%>" name="email" required="false"></p></td></tr>
                <tr><td><label class ="formText" for="phone">Phone</label><p><input type="number" placeholder="<%=customer.GetPhone()%>" name="phone" required="false"></p></td><td><label class ="formText" for="password">Password</label><p><input type="password" style="<%=passwordError != null ? "border:3.5px solid #FF0000" : "border:3px solid #cccccc"%>" placeholder="<%= customer.GetPassword()%>" name="password" required="false" id="pass"></p></td></tr>
                <tr>
                        <td><label class ="formText" for="streetNumber">Street Number</label><p><input type="number" placeholder="<%=customer.GetStreetNumber() %>" name="streetNumber" required="false"></p></td>    
                        <td><label class ="formText" for="streetAddress">Street Address)</label><p><input type="text" placeholder="<%=customer.GetStreet() %>" name="streetAddress" required="false"></p></td>
                        <td><label class ="formText" for="streetType">Street Type</label><p><input type="text" placeholder="<%=customer.GetStreetType() %>" name="streetType" required="false"></p></td>
                        <td><label class ="formText" for="state">State</label><p><input type="text" placeholder="<%=customer.GetState() %>" name="state" required="false"></p></td>
                        <td><label class ="formText" for="postcode">Postcode</label><p><input type="number" placeholder="<%=customer.GetPostcode() %>" name="postcode" required="false"></p></td>
                </tr>
                <tr>
                    <td><label class ="formText" for="CardNumber">Card Number</label><p><input type="text" placeholder="<%=customer.GetCardNumber()%>" name="cardNumber" required="false"></p></td>
                    <td><label class ="formText" for="ExpiryDate">Expiry Date</label><p><input type="text" placeholder="<%=customer.GetExpiryDate()%>" name="expiryDate" required="false"></p></td>
                    <td><label class ="formText" for="CVV">CVV</label><p><input type="text" placeholder="<%=customer.GetCVV()%>" name="cvv" required="false"></p></td>
                </tr>
            </table>
            <div align="center" class="buttons">
                <table>
                    <tr><td><input type="submit" formnovalidate  value ="Update Details"></td></tr>
                    <tr><td><button onClick="toggle(this)"; ><a href="AccessLogServlet">Show Access Logs</a></button></td></tr>
                </table>
        <h1><span class = "message"><%=(emailError != null ? emailError : "")%></span></h1>
        <h1><span class = "message"><%=(passwordError!= null ? passwordError : "")%></span></h1>
        <h1><span class = "message"><%=(loginError != null ? loginError : "")%></span></h1>
        <h1><span class = "message"><%=(editStatus != null ? editStatus : "")%></span></h1>
        
        <table  class="accessTable" id="accessTable" style="margin-right:auto;margin-left:0px;">
        <tr>
          <th>Log ID</th>
          <th>Login Date</th>
          <th>Login Time</th>
          <th>Logout Date</th>
          <th>Logout Time</th>
<
        </tr>
             <%
                if (allLogs != null){
                    for (int i = 0; i < allLogs.size(); i++){
            %>
            
            <tr>
                <td><p><%=allLogs.get(i).GetLogID()%></p></td>
                <td><p><%=allLogs.get(i).GetLoginDate()%></p></td>
                <td><p><%=allLogs.get(i).GetLoginTime()%></p></td>
                <td><p><%=allLogs.get(i).GetLogoutDate()%></p></td>
                <td><p><%=allLogs.get(i).GetLogoutTime()%></p></td>
                        
            </tr>
                <%}}%>
</tbody>  
        </table>
    </body>
</html>


