/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isd.iotbay.controller;

/**
 *
 * @author jogun
 */
import com.isd.iotbay.dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 


public class AddProductServlet extends HttpServlet {
    
    
    @Override //Create and instance of DBConnector for the deployment session
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        //catch exceptions for the DBConnector
//        try
//        {
//            Connector = new DBConnector();
//        }catch (ClassNotFoundException | SQLException ex){
//            java.util.logging.Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        
//        //catch exceptions for the DBManager
//        try
//        {       
//            manager = new DBManager(Connector.openConnection());  
//        }catch (SQLException ex){
//            java.util.logging.Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }

 

        //session
        HttpSession session = request.getSession();
        DBManager manager = (DBManager)session.getAttribute("manager");
        
        String product_name = request.getParameter("Product_Name");
        Double cost = Double.parseDouble(request.getParameter("Product_Cost"));
        Integer stock = Integer.parseInt(request.getParameter("Product_Stock"));
        String product_deliveryDate = request.getParameter("Product_DeliveryDate");
        
    
    try {
        if (product_name != null) {
            manager.addProduct(product_name, cost, stock, product_deliveryDate); //call addNewItem and use the inputted values from admin user method in manager
            session.setAttribute("allProducts",manager.showCollection());
            session.setAttribute("added", "Item has been added to Collection"); //set the attribute as successful (no error message)

            request.getRequestDispatcher("Collection_add.jsp").include(request, response); //request comes from the addNewItem.jsp
            response.sendRedirect("Collection_add.jsp"); //the user is redirected back to this page with the success message
        } else {
            session.setAttribute("added", "Item has NOT been added to Collection");//if form is completed incorrectly, display error message
            request.getRequestDispatcher("Collection_add.jsp").include(request, response); //the user is redirected back to this page to display error message
        }
    
    } catch (SQLException ex){
        Logger.getLogger(AddProductServlet.class.getName()).log(Level.SEVERE, null, ex); //if there is an SQL error, it is logged under this Servlet
    }
    
  }
}