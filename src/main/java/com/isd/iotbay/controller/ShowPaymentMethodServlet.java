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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ShowPaymentMethodServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

         
        HttpSession session = request.getSession();
        //retrieve the product name that was searched for by the user
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        List<PaymentMethod> paymentMethods = (List<PaymentMethod>) session.getAttribute("paymentMethods");
        String currentDate = LocalDate.now().toString();
        PaymentMethod currentPaymentMethod = (PaymentMethod)session.getAttribute("currentMethodToOrder");
        Order order = (Order)session.getAttribute("currentOrder");

        if (paymentMethods == null) {
            paymentMethods = new ArrayList<>();
        }
        
        try
        {
            if(customer != null)
            {
                paymentMethods = manager.GetAllPaymentMethodsForCustomer(customer.GetID());
            } else if (staff != null)
            {
                paymentMethods = manager.GetAllPaymentMethodsForStaff(staff.GetID());
            }
            
            if(paymentMethods != null)
            {
                currentPaymentMethod = manager.GetPaymentFromOrder(order.GetID());
            }
            
        } catch (SQLException ex) {System.out.println(ex);}

            
        session.setAttribute("currentMethodToOrder", currentPaymentMethod);
        session.setAttribute("paymentMethods", paymentMethods);
        response.sendRedirect("PaymentMethod.jsp");
    }
}