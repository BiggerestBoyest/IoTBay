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
public class AddProductToOrderServlet  extends HttpServlet{
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
        ArrayList<Product> products = new ArrayList();
        session.setAttribute("addedProduct", null);
        
        try
        {
            products = manager.showCollection();
            
            for(Product product : products)
            {
                if(request.getParameter(Integer.toString(product.getProduct_ID())) != null)
                {
                    order.AddProduct(product);
                    System.out.println("added product");
                    manager.decreaseStock(product.getProduct_ID(), 1);
                   // manager.AddProductToOrder(order.GetID(), product.getProduct_ID());
                    session.setAttribute("addedProduct", product.getProduct_name() + " was added to your order.");
                    session.setAttribute("allProducts",manager.showCollection());
                    break;
                }
            }
            
        } catch(SQLException ex){System.out.println(ex);}
        
        session.setAttribute("currentOrder", order);
        request.getRequestDispatcher("Order.jsp").forward(request, response);
  
        
        
        
    }
}
