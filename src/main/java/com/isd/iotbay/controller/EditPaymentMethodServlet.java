package com.isd.iotbay.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.isd.iotbay.Customer;
import com.isd.iotbay.Order;
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
import java.util.ArrayList;
import java.util.List;

public class EditPaymentMethodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<PaymentMethod> paymentMethods = (List<PaymentMethod>) session.getAttribute("paymentMethods");
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        ArrayList<PaymentMethod> methods = new ArrayList();
        PaymentMethod currentPaymentMethod = (PaymentMethod)session.getAttribute("currentMethodToOrder");
        try
        {
            if (customer != null)
            {
                paymentMethods = manager.GetAllPaymentMethodsForCustomer(customer.GetID());
            } else if (staff != null)
            {
                paymentMethods= manager.GetAllPaymentMethodsForStaff(staff.GetID());
            }
            
            
            for(int i =0 ;i < paymentMethods.size(); i++)
            {
                if(request.getParameter(Integer.toString(paymentMethods.get(i).GetPaymentID())) != null)
                {
                    currentPaymentMethod = paymentMethods.get(i);
                    String cardName = request.getParameter("name" + currentPaymentMethod.GetPaymentID());
                    String cardNumber = request.getParameter("cardNumber" + currentPaymentMethod.GetPaymentID());
                    String expiryDate = request.getParameter("expiryDate" + currentPaymentMethod.GetPaymentID());
                    String cvs = request.getParameter("cvs" + currentPaymentMethod.GetPaymentID());
                    System.out.println(cardName + " cardname");
                    manager.UpdatePaymentMethod(currentPaymentMethod.GetPaymentID(), cardName, cardNumber, expiryDate, Integer.parseInt(cvs));
                    currentPaymentMethod.setNameOnCard(cardName);
                    currentPaymentMethod.setCardNum(cardNumber);
                    currentPaymentMethod.setExpiryDate(expiryDate);
                    currentPaymentMethod.setCvs(cvs);
                    paymentMethods.set(i, currentPaymentMethod);

                }
            }
            
           
            
    
        } catch(SQLException ex ) {System.out.println(ex);}
        
        session.setAttribute("currentMethodToOrder", currentPaymentMethod);
        session.setAttribute("paymentMethods", paymentMethods);
        request.getRequestDispatcher("PaymentMethod.jsp").forward(request, response);
    }
}