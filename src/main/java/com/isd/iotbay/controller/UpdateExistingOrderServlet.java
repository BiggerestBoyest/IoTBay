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
import java.time.LocalDate;
import java.time.LocalTime;
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
public class UpdateExistingOrderServlet  extends HttpServlet{
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
        request.setAttribute("saveSubmitOrder", null);
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().toString();
        
        order.SetDate(currentDate);
        order.SetTime(currentTime);
        
        
        
            try
            {
               manager.UpdateProductsInOrder(order.GetID(), order.GetProducts());

            }
             catch(SQLException ex)
            {
                System.out.println(ex);
            } 
        
            session.setAttribute("currentOrder",order);
            session.setAttribute("saveSubmitOrder", "Your Order has been successfully saved.");
            request.getRequestDispatcher("ExistingOrder.jsp").forward(request,response);
    }
}
