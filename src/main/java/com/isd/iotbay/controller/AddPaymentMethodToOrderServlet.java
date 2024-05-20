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

public class AddPaymentMethodToOrderServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
                methods = manager.GetAllPaymentMethodsForCustomer(customer.GetID());
            } else if (staff != null)
            {
                methods = manager.GetAllPaymentMethodsForStaff(staff.GetID());
            }
            
            
            for(PaymentMethod method : methods)
            {
                if(request.getParameter(Integer.toString(method.GetPaymentID())) != null)
                {
                    if(currentPaymentMethod != null)
                    {
                        manager.RemovePaymentFromOrder(currentPaymentMethod.GetPaymentID(),order.GetID());
                    }
                    
                    System.out.println("FOUND PAYMENT");
                    currentPaymentMethod = method;
                    manager.AddPaymentToOrder(currentPaymentMethod.GetPaymentID(), order.GetID());
                    currentPaymentMethod.SetOrderID(order.GetID());
                }
            }
            
            
            
        } catch(SQLException ex){System.out.println(ex);}
        
        session.setAttribute("currentMethodToOrder", currentPaymentMethod);
        session.setAttribute("paymentMethods", paymentMethods);
        response.sendRedirect("PaymentMethod.jsp");
    }
}