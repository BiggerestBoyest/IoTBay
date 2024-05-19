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
import javax.servlet.RequestDispatcher;
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
public class ExistingOrderServlet  extends HttpServlet{
//     private DBManager manager;
//    private DBConnector Connector;
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException 
    {       
 
        HttpSession session = request.getSession();
        //retrieve the product name that was searched for by the user
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        ArrayList<Product> allProducts = (ArrayList<Product>)session.getAttribute("allProducts");
        try
        {
            
            ArrayList<Product> products = manager.GetAllProductsFromOrder(order.GetID());
            allProducts = manager.showCollection();
            order.SetProducts(products);
        } catch (SQLException ex){System.out.println(ex);}
        
        session.setAttribute("allProducts",allProducts);
       session.setAttribute("currentOrder", order);
       request.getRequestDispatcher("ExistingOrder.jsp").forward(request, response);
        
    }
}

