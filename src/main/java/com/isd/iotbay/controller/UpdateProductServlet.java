/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isd.iotbay.controller;

import com.isd.iotbay.dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class UpdateProductServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //catch exceptions for the DBConnector
//        try
//        {
//            Connector = new DBConnector();
//        }catch (ClassNotFoundException | SQLException ex){
//            java.util.logging.Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        //catch exceptions for the DBManager
//        try
//        {       
//            manager = new DBManager(Connector.openConnection());  
//        }catch (SQLException ex){
//            java.util.logging.Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
        
        //session
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        //retrieve inputted values from the user
        
        String Product_name = request.getParameter("Product_name");
        Double Cost = Double.parseDouble(request.getParameter("Cost"));
        Integer Product_stock = Integer.parseInt(request.getParameter("Product_stock"));
        String Product_deliveryDate = request.getParameter("Product_deliveryDate");
        String oldProduct = request.getParameter("oldProduct");
        
        
        //database
        session.setAttribute("product", null);
        session.setAttribute("updated", null);
        
        try{
            Boolean exists = manager.checkItem(oldProduct); //check if item exists in the Inventory
            if (exists) {
                manager.updateItem(manager.fetchProductid(oldProduct), Product_name, Cost, Product_stock, Product_deliveryDate);//update the item
                session.setAttribute("updated", "Item has now been updated"); //success message (no error message)

 

                request.getRequestDispatcher("Collection_update.jsp").include(request, response); //request coming from updateItem.jsp
                response.sendRedirect("Collection_update.jsp");//user is redirected back to this page with the success
            }
            else {
                session.setAttribute("updated", "Item has NOT been updated");//error message if item is not found in the Inventory
                request.getRequestDispatcher("Collection_update.jsp").include(request, response);//request is from updateItem.jsp
                response.sendRedirect("Collection_update.jsp");//user is redirected back to the page with the error message displayed
            }  
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage());//SQL error is printed should an error occur
        }
    }
}
