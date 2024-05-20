<%-- 
    Document   : PaymentMethod
    Created on : 11/05/2024, 3:55:09 PM
    Author     : thean
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.isd.iotbay.PaymentMethod" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/PaymentMethod.css">
    <style>
        h1 {
            font-family: "Oswald", sans-serif;
            font-size: 40px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        .error {
            color: red;
        }
    </style>
    <title>Payment Method</title>
</head>
<body>
    <div>
        <img class="logo" src="css/IOTBAY Logo.png" alt="IOTBAY Logo">
    </div>
    <div class="cardtitle">
        <h1>Add Credit Card</h1>
    </div>
    <div class="error">
        <%
            String error = (String) request.getAttribute("error");
            if (error != null) {
                out.print(error);
            }
        %>
    </div>
    <form id="cardForm" action="PaymentMethodServlet" method="post">
        <div>
            <label for="cardname">Name on Card</label>
            <input type="text" id="cardname" name="cardname">
        </div>
        <div>
            <label for="cardnumber">Card Number</label>
            <input type="text" id="cardnumber" name="cardnumber">
        </div>
        <div>
            <label for="expirydate">Expiry Date</label>
            <input type="date" id="expirydate" name="expirydate">
        </div>
        <div>
            <label for="cvs">CVS</label>
            <input type="text" id="cvs" name="cvs">
        </div>
        <div>
            <input type="hidden" id="editIndex" name="editIndex" value="">
            <button type="submit">Add</button>
        </div>
    </form>
    <div id="table">
        <table>
            <thead>
                <tr>
                    <th>CNo.</th>
                    <th>Name</th>
                    <th>Card Number</th>
                    <th>Expiry Date</th>
                    <th>CVS</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<PaymentMethod> paymentMethods = (List<PaymentMethod>) session.getAttribute("paymentMethods");
                    PaymentMethod methodForOrder = (PaymentMethod)session.getAttribute("currentMethodToOrder");
                    if (paymentMethods != null && !paymentMethods.isEmpty()) {
                        int cno = 1;
                        for (int i = 0; i < paymentMethods.size(); i++) {
                            PaymentMethod paymentMethod = paymentMethods.get(i);
                %>
                <tr>
                    <form id="EditDetails" action="EditPaymentMethodServlet" method="get">

                    <td><%= cno++ %></td>
                    <td><input type="text" value="<%= paymentMethod.getNameOnCard() %>" name="name<%=paymentMethod.GetPaymentID()%>"></td>
                    <td>
                        <input type="text" value="**********<%= paymentMethod.getCardNum().substring(paymentMethod.getCardNum().length() - 4) %>" name="cardNumber<%=paymentMethod.GetPaymentID()%>">
                        <button type="button" onclick="unhideCardNum(this, '<%= paymentMethod.getCardNum() %>')">Unhide</button>
                    </td>
                    <td><input type="date" value="<%= paymentMethod.getExpiryDate() %>" name="expiryDate<%=paymentMethod.GetPaymentID()%>"></td>
                    <td>
                        <input type="text" value="***" name="cvs<%=paymentMethod.GetPaymentID()%>">
                        <button type="button" onclick="unhideCvs(this, '<%= paymentMethod.getCvs() %>')">Unhide</button>
                    </td>
                    <td>
                                <input type="submit" name="<%=paymentMethod.GetPaymentID()%>"  value="Update Details">
                        </form>

                        <form action="PaymentMethodServlet" method="post" style="display:inline;">
                            <input type="hidden" name="deleteIndex" value="<%= i %>">
                            <button type="submit">Delete</button>
                        </form>
                        <form action="AddPaymentMethodToOrderServlet" method="post" style="display:inline;">
                            <% if (methodForOrder != null){
                                if (paymentMethod.GetPaymentID() != methodForOrder.GetPaymentID()){
                            %>
                            <input type="submit" name="<%=paymentMethod.GetPaymentID()%>"value="Add to order"></button>
                        <%}}%>
                        </form>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="6">No credit card information added yet.</td>
                </tr>
                <%
                    }
                %>
                <a href="ShowOrderServlet">Back to previous page</a>

            </tbody>
        </table>
    </div>
    <script>
        function editCard(button, index) {
                
            var row = button.closest('tr');
            var inputs = row.querySelectorAll('input[type=text], input[type=date]');
            var editMode = button.textContent === 'Edit';

            inputs.forEach(input => {
                input.readOnly = !editMode;
            });

            if (editMode) {
                button.textContent = 'Save';
            } else {
                var nameOnCard = inputs[0].value;
                var cardNum = inputs[1].value.replace(/[^0-9]/g, '');
                var expiryDate = inputs[2].value;
                var cvs = inputs[3].value.replace(/[^0-9]/g, '');

                //if (!nameOnCard || !cardNum || !expiryDate || !cvs) {
                //    alert('Please fill in all fields.');
               //     return;
               // }
                inputs[0].value = inputs[0].value;
                inputs[1].value = inputs[1].value;
                inputs[1].value.replace(/[^0-9]/g, '');
                inputs[2].value = inputs[2].value;
                 inputs[3].value = inputs[3].value;
                 inputs[3].value.replace(/[^0-9]/g, '');
                 
              //  nameOnCard = document.getElementById('cardname').value;
              //  cardNum = document.getElementById('cardnumber').value ;
               /// expiryDate = document.getElementById('expirydate').value ;
               // cvs = document.getElementById('cvs').value = cvs;
               // index = document.getElementById('editIndex').value;
                button.textContent = 'Edit';
                document.getElementById("EditDetails").submit();

            }
        }

        function unhideCardNum(button, cardNum) {
            var input = button.previousElementSibling;
            if (button.textContent === 'Unhide') {
                input.value = cardNum;
                button.textContent = 'Hide';
            } else {
                input.value = '**********' + cardNum.substring(cardNum.length - 4);
                button.textContent = 'Unhide';
            }
        }

        function unhideCvs(button, cvs) {
            var input = button.previousElementSibling;
            if (button.textContent === 'Unhide') {
                input.value = cvs;
                button.textContent = 'Hide';
            } else {
                input.value = '***';
                button.textContent = 'Unhide';
            }
        }
    </script>
</body>
</html>