package com.isd.iotbay.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.isd.iotbay.Customer;
import com.isd.iotbay.PaymentMethod;
import com.isd.iotbay.Staff;
import com.isd.iotbay.dao.DBManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentMethodServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         
        HttpSession session = request.getSession();
        //retrieve the product name that was searched for by the user
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        List<PaymentMethod> paymentMethods = (List<PaymentMethod>) session.getAttribute("paymentMethods");
        String currentDate = LocalDate.now().toString();
        PaymentMethod currentPaymentMethod = (PaymentMethod)session.getAttribute("currentMethodToOrder");
        
        if (paymentMethods == null) {
            paymentMethods = new ArrayList<>();
        }

        String deleteIndexStr = request.getParameter("deleteIndex");
        String editIndexStr = request.getParameter("editIndex");

        if (deleteIndexStr != null && !deleteIndexStr.isEmpty()) {
            int deleteIndex = Integer.parseInt(deleteIndexStr);
            
            if(paymentMethods.get(deleteIndex).GetPaymentID() == currentPaymentMethod.GetPaymentID())
            {
                try{
                manager.RemovePaymentMethod(currentPaymentMethod.GetPaymentID());
                currentPaymentMethod = null;
                session.setAttribute("currentMethodToOrder", currentPaymentMethod);
                } catch (SQLException ex) {System.out.println(ex);}
            }
            
            paymentMethods.remove(deleteIndex);
            
        } else if (editIndexStr != null && !editIndexStr.isEmpty()) {
            int editIndex = Integer.parseInt(editIndexStr);
            PaymentMethod paymentMethod = paymentMethods.get(editIndex);
            String nameOnCard = request.getParameter("cardname");
            String cardNum = request.getParameter("cardnumber");
            String expiryDate = request.getParameter("expirydate");
            String cvs = request.getParameter("cvs");
           

            String validationResult = CardValidator.validate(nameOnCard, cardNum, expiryDate, cvs);

            if (!validationResult.isEmpty()) {
                request.setAttribute("error", validationResult);
                request.getRequestDispatcher("PaymentMethod.jsp").forward(request, response);
                return;
            }

            paymentMethod.setNameOnCard(nameOnCard);
            paymentMethod.setCardNum(cardNum);
            paymentMethod.setExpiryDate(expiryDate);
            paymentMethod.setCvs(cvs);
        } else {
            String nameOnCard = request.getParameter("cardname");
            String cardNum = request.getParameter("cardnumber");
            String expiryDate = request.getParameter("expirydate");
            String cvs = request.getParameter("cvs");

            String validationResult = CardValidator.validate(nameOnCard, cardNum, expiryDate, cvs);

            if (!validationResult.isEmpty()) {
                request.setAttribute("error", validationResult);
                request.getRequestDispatcher("PaymentMethod.jsp").forward(request, response);
                return;
            }

            for (PaymentMethod pm : paymentMethods) {
                if (pm.getNameOnCard().equals(nameOnCard) && pm.getCardNum().equals(cardNum) &&
                    pm.getExpiryDate().equals(expiryDate) && pm.getCvs().equals(cvs)) {
                    request.setAttribute("error", "Duplicate credit card information.");
                    request.getRequestDispatcher("PaymentMethod.jsp").forward(request, response);
                    return;
                }
            }
            
            int id = -1;
            
            try
            {
                 id = manager.GeneratePaymentID();
                 
                 if(customer != null)
                 {
                    manager.AddPaymentMethodToCustomer(id, customer.GetID(), nameOnCard, cardNum, expiryDate, currentDate, Integer.parseInt(cvs));
                 } else if (staff != null)
                 {
                    manager.AddPaymentMethodToCustomer(id, customer.GetID(), nameOnCard, cardNum, expiryDate, currentDate, Integer.parseInt(cvs));
                 }
            } catch(SQLException ex) {System.out.print(ex);}
            
            PaymentMethod newPaymentMethod = new PaymentMethod(id,nameOnCard, cardNum, expiryDate, cvs,currentDate);
            paymentMethods.add(newPaymentMethod);
            
            
        }
        session.setAttribute("paymentMethods", paymentMethods);
        request.getRequestDispatcher("PaymentMethod.jsp").forward(request, response);
    }
}