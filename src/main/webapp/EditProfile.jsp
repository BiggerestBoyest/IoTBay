<link rel="stylesheet" href="css/edit_profile.css">
<%@page import="com.isd.iotbay.Customer" %>
<%@page import="com.isd.iotbay.Staff" %>
<%@page import="com.isd.iotbay.dao.DBManager" %>
<%@page import="com.isd.iotbay.AccessLog" %>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/edit_profile.css?version=1">
           <%
                Customer customer = (Customer)session.getAttribute("customer");
                Staff staff = (Staff)session.getAttribute("staff");
                AccessLog currentLog = (AccessLog)session.getAttribute("currentLog");
                String emailError = (String)session.getAttribute("emailError") ;
                String passwordError = (String)session.getAttribute("passwordError");
                String loginError = (String)session.getAttribute("loginError");
                String editStatus = (String)session.getAttribute("editStatus");
                ArrayList<AccessLog> allLogs = (ArrayList<AccessLog>)session.getAttribute("allLogs");
                String logError = (String) session.getAttribute("logError");
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
                <tr>
                    <td><label class ="formText" for="fname">First Name</label><p><input type="text" value="<%=customer != null ? customer.GetFirstName() : staff.GetFirstName()%>" name="fname" required="false"></p></td>
                    <td><label class ="formText" for="lname">Last Name</label><p><input type="text" value="<%=customer != null ? customer.GetLastName() : staff.GetLastName()%>" name="lname" required="false"></p></td>
                </tr>
                <tr>
                    <td><label class ="formText" for="dob">Date of Birth</label><p><input type="date" value="<%= customer != null ? customer.GetDOB() : staff.GetDOB()%>" name="dob" required="false"></p></td>
                    <td><label class ="formText" for="email">Email</label><p><input style="<%=emailError != null ? "border:3.5px solid #FF0000" : "border:3px solid #cccccc"%>" type="email" value="<%=customer != null ? customer.GetEmail() : staff.GetEmail()%>" name="email" required="false"></p></td>
                </tr>
                <tr>
                    <td><label class ="formText" for="phone">Phone</label><p><input type="number" value="<%=customer != null ? customer.GetPhone() : staff.GetPhone()%>" name="phone" required="false"></p></td>
                    <td><label class ="formText" for="password">Password</label><p><input type="password" style="<%=passwordError != null ? "border:3.5px solid #FF0000" : "border:3px solid #cccccc"%>" value="<%= customer != null ? customer.GetPassword() : staff.GetPassword()%>" name="password" required="false" id="pass"></p></td>
                </tr>
                <tr>
                        <td><label class ="formText" for="streetNumber">Street Number</label><p><input type="number" value="<%=customer != null ? customer.GetStreetNumber() : staff.GetStreetNumber()%>" name="streetNumber" required="false"></p></td>    
                        <td><label class ="formText" for="streetAddress">Street Address)</label><p><input type="text" value="<%= customer != null ? customer.GetStreet() : staff.GetStreet() %>" name="streetAddress" required="false"></p></td>
                        <td><label class ="formText" for="streetType">Street Type</label><p><input type="text" value="<%=customer != null ? customer.GetStreetType() : staff.GetStreetType() %>" name="streetType" required="false"></p></td>
                        <td><label class ="formText" for="state">State</label><p><input type="text" value="<%=customer != null ? customer.GetState() : staff.GetState() %>" name="state" required="false"></p></td>
                        <td><label class ="formText" for="postcode">Postcode</label><p><input type="number" value="<%=customer != null ? customer.GetPostcode() : staff.GetPostcode() %>" name="postcode" required="false"></p></td>
                </tr>
                <tr>
                    <td><label class ="formText" for="CardNumber">Card Number</label><p><input type="text" value="<%= customer != null ? customer.GetCardNumber() : staff.GetCardNumber()%>" name="cardNumber" required="false"></p></td>
                    <td><label class ="formText" for="ExpiryDate">Expiry Date</label><p><input type="text" value="<%=customer != null ? customer.GetExpiryDate() : staff.GetExpiryDate()%>" name="expiryDate" required="false"></p></td>
                    <td><label class ="formText" for="CVV">CVV</label><p><input type="text" value="<%=customer != null ? customer.GetCVV() : staff.GetCVV()%>" name="cvv" required="false"></p></td>
                </tr>
            </table>
                 <div align="center" class="buttons">
                <table>
                    <tr><td><input type="submit" formnovalidate  value ="Update Details"></td></tr>
                </table>
                <h1><span class = "message"><%=(emailError != null ? emailError : "")%></span></h1>
                <h1><span class = "message"><%=(passwordError!= null ? passwordError : "")%></span></h1>
                <h1><span class = "message"><%=(loginError != null ? loginError : "")%></span></h1>
                <h1><span class = "message"><%=(editStatus != null ? editStatus : "")%></span></h1>
            </div>
        </form>
                <div style="padding:10px;">
                    <button  data-modal-target="#modal" id="cancelBtn">CANCEL REGISTRATION</button>
                    <div class ="modal" id="modal">
                        <div class="modal-header">
                            <div "class="title">Cancel Registration</div>
                                <button data-close-button class="close-button">&times;</button>
                        </div>
                        <div class="modal-body">
                            Cancelling your registration will result in<br><br>
                            • Unable to order/purchase products off the IotBay website.<br>
                            • IoTBay will retain your access logs into the website<br>
                            • Information regarding your profile will be removed. <br>
                        </div>
                          <div class="modal-buttons" style="padding-top: 10px;">
                                <a href="RemoveCustomerServlet"><button type="button" data-modal-button class="cancel-registration-button">Cancel Registration</button></a>
                        </div>
                    </div>
                </div>
                <div id="overlay"></div>
           
        <div align="center"" style="margin:auto;width:50%;padding-bottom: 20px ">
        <button id="logBtn" onClick="HideTable()"; >Hide Access Logs</button>
        </div>
            <form action="AccessLogServlet" method="post">
                <table  align="center" id="accessTableSearch">
                    <tr><td><input type="date" placeholder="Search by date " name="logFilter" required="false"></td>
                    <td><input type="submit" formnovalidate value="Search by date"></td></tr>
                </table>
            </form>
            <h3><span style="align:center;" class = "message"><%=(logError != null ? logError : "")%></span></h3>
        <table>
            <tbody class="accessTable" id="accessTable" style="width: 100%">
                <tr style="padding-top:12px;padding-bottom: 12px;background-color: #ff914c;color:white;">
                    <th>Log ID</th>
                    <th>Login Date</th>
                    <th>Login Time</th>
                    <th>Logout Date</th>
                    <th>Logout Time</th>
                </tr>
             <%
                if (allLogs != null)
                {
                    for (int i = 0; i < allLogs.size(); i++)
                    {
            %>
                <tr style="padding:8px;background-color: #cccccc">
                    <td><p><%=allLogs.get(i).GetLogID()%></p></td>
                    <td><p><%=allLogs.get(i).GetLoginDate()%></p></td>
                    <td><p><%=allLogs.get(i).GetLoginTime()%></p></td>
                    <td><p><%=allLogs.get(i).GetLogoutDate()%></p></td>
                    <td><p><%=allLogs.get(i).GetLogoutTime()%></p></td>
                </tr>
                    <%} 
                }%>
            </tbody>  
        </table>
    </body>

<script>
const openModalButtons = document.querySelectorAll('[data-modal-target]')
const closeModalButtons = document.querySelectorAll('[data-close-button]');
const overlay = document.getElementById('overlay');

openModalButtons.forEach(btn => 
{
    btn.addEventListener('click',() =>
    {
                console.log('here');

        const modal = document.querySelector(btn.dataset.modalTarget);
        openModal(modal);
    });
});

closeModalButtons.forEach(btn => 
{
    btn.addEventListener('click',() =>
    {
        console.log('here2');
        const modal = btn.closest('.modal');
        closeModal(modal);
    });
});
    

  

 function openModal(modal)
 {
     if (modal === null) return;
     
     modal.classList.add('active');
     overlay.classList.add('active');
 }  
 
 
 
 function closeModal(modal)
 {
      if (modal === null) return;
     
     modal.classList.remove('active');
     overlay.classList.remove('active');
 }
</script>
<script>
function HideTable() {
  var x = document.getElementById("accessTable");
  var y = document.getElementById("accessTableSearch");
  var logBtn = document.getElementById("logBtn");

  if (x.style.display === "none") {
    x.style.display = "block";
    y.style.display ="ruby-text";
    x.style.width = "100%";
    y.style.align ="center";
    logBtn.innerText = 'Hide Access Logs'

  } else {
    x.style.display = "none";
    y.style.display ="none";
    logBtn.innerText = 'Show Access Logs'
  }
}
    
    
 </script>
</html>

