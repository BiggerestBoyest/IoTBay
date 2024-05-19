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
public class RemoveProductFromOrderServlet  extends HttpServlet{
//     private DBManager manager;
//    private DBConnector Connector;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 
       HttpSession session = request.getSession();
        //retrieve the product name that was searched for by the user
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        Product product = null;


        
            try
            {
                        ArrayList<Product> products = manager.GetAllProductsFromOrder(order.GetID());

                    
            for(Product pro : products)
            {
                if(request.getParameter(Integer.toString(pro.getProduct_ID())) != null)
                {
                   product = pro;
                    break;
                }
            }
               
                if(product != null) {
                    manager.RemoveProductFromOrder(order.GetID(), product.getProduct_ID());
                    order.RemoveProduct(product);
                }
            }
             catch(SQLException ex)
            {
                System.out.println(ex);
            } 
        
            session.setAttribute("currentOrder", order);
           RefreshProducts(request,response);
    }
    
    @Override
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         RefreshProducts(request,response);
    }
     
     private void RefreshProducts(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
        {
         HttpSession session = request.getSession();
        //retrieve the product name that was searched for by the user
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        ArrayList<Product> products = new ArrayList();
        
        if(order != null)
        {
            try 
            {
                products = manager.GetAllProductsFromOrder(order.GetID());
            } catch (SQLException ex)
            {
                System.out.println(ex);
            }
        }
        
        session.setAttribute("productsInOrder", products);
        System.out.println("refreshed");
        request.getRequestDispatcher("UpdateProductsInOrder.jsp").forward(request, response);
        }
     
}
