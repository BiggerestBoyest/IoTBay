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
public class ShowOrderByFilterServlet  extends HttpServlet{
//     private DBManager manager;
//    private DBConnector Connector;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 
       HttpSession session = request.getSession();
        //retrieve the product name that was searched for by the user
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        
        String saveOrder = request.getParameter("saveOrder");
        ArrayList<Order> orders = new ArrayList();
        int targetID = request.getParameter("targetOrderID").equals("") ? 0 : Integer.parseInt(request.getParameter("targetOrderID"));
        String targetDate = request.getParameter("targetDate");
        Order tempOrder = null;
        
        
        if(request.getParameter("GetOrderByID") != null)
        {
            try 
            {
                 if(customer != null)
                {
                    tempOrder = manager.GetOrderFromCustomer(targetID,customer.GetID());
                    if(tempOrder != null)
                        orders.add(tempOrder);
                        else 
                        orders = null;
                } else if (staff != null)
                {
                    tempOrder = manager.GetOrderStaff(targetID,staff.GetID());
                    if(tempOrder != null)
                        orders.add(tempOrder);
                        else 
                        orders = null;
                            
                } else if (session.getAttribute("guest") != null)
                {
                    tempOrder = manager.GetOrderFromGuest(targetID,(Integer)session.getAttribute("guest"));
                    if(tempOrder != null)
                        orders.add(tempOrder);
                    else 
                        orders = null;
                }
            }  catch(SQLException ex)
            {
                System.out.println(ex);
            } 
        } else if(request.getParameter("GetOrderByDate") != null)
        {
            
            try{
             if(customer != null)
                {
                    orders = manager.GetCustomerOrdersByDate(customer.GetID(),targetDate);
                    if(orders.size() <= 0)
                        orders = null;
                } else if (staff != null)
                {
                    orders = manager.GetStaffOrdersByDate(staff.GetID(),targetDate);
                       if(orders.size() <= 0)
                        orders = null;
                } else if (session.getAttribute("guest") != null)
                {
                    orders = manager.GetGuestOrdersByDate((Integer)session.getAttribute("guest"),targetDate);
                       if(orders.size() <= 0)
                        orders = null;
                }
            } catch (SQLException ex){System.out.println(ex);}
        }
        
                session.setAttribute("allOrders", orders);
        

            request.getRequestDispatcher("ShowOrders.jsp").forward(request,response);
    }
}
