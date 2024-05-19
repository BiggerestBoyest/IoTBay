/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isd.iotbay.controller;

import com.isd.iotbay.Staff;
import com.isd.iotbay.Customer;
import com.isd.iotbay.Product;
import com.isd.iotbay.dao.*;
import com.isd.iotbay.Order;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @author jogun
 */
public class EditOrderServlet  extends HttpServlet{
//     private DBManager manager;
//    private DBConnector Connector;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) //Get selected order
            throws ServletException, IOException {

 
       HttpSession session = request.getSession();
        //retrieve the product name that was searched for by the user
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        
        String saveOrder = request.getParameter("saveOrder");
        ArrayList<Order> orders = new ArrayList();
        request.setAttribute("saveSubmitOrder", null);
        
            try
            {
                if(customer != null)
                {
                    orders = manager.GetAllCustomerOrders(customer.GetID());
                } else if (staff != null)
                {
                    orders = manager.GetAllStaffOrders(staff.GetID());
                } else 
                {
                    System.out.println("guest ");
                    int guestID = (int)session.getAttribute("guest");
                    orders = manager.GetAllGuestOrders(guestID);
                }
                
            
            for(Order ord : orders)
            {
                if(request.getParameter(Integer.toString(ord.GetID())) != null)
                {
                    System.out.println("found order");
                    session.setAttribute("currentOrder", ord);
                    break;
                }
            }
            }
             catch(SQLException ex)
            {
                System.out.println(ex);
            } 
        

            request.getRequestDispatcher("EditOrder.jsp").forward(request,response);
    }
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 
       HttpSession session = request.getSession();
        //retrieve the product name that was searched for by the user
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        int streetNumber  = request.getParameter("streetNumber").equals("") ? -1 : Integer.parseInt(request.getParameter("streetNumber"));
        String streetAddress  = request.getParameter("streetAddress");
        String streetType  = request.getParameter("streetType");
        String state  = request.getParameter("state");
        String suburb = request.getParameter("suburb");
        int postcode =  request.getParameter("postcode").equals("") ? -1 : Integer.parseInt(request.getParameter("postcode"));
        String saveOrder = request.getParameter("saveOrder");
        ArrayList<Order> orders = new ArrayList();
        request.setAttribute("saveSubmitOrder", null);
        String address = "";
        request.setAttribute("updateStatus", null);
        
                
        if(streetAddress.equals("") || streetType.equals("") || state.equals("") || postcode == -1 || streetNumber == -1)
        {
           address = order.GetAddress();
            

        } else 
        {
            address =  streetNumber + " " + streetAddress + " " + streetType + " " + suburb + " " + state + " " + postcode;
        }
                
                
        if(request.getParameter("orderUpdate") != null)
        {
             try
            {
                    order.SetAddress(address);
                    manager.UpdateOrder(order.GetID(), order.GetDate(), order.GetTime(), address);
            }
             catch(SQLException ex)
            {
                System.out.println(ex);
            } 
        }
        
         
        
            request.setAttribute("updateStatus", "Succesfully updated details");
            request.setAttribute("currentOrder", order);
            request.getRequestDispatcher("EditOrder.jsp").forward(request,response);
    }
    
}
