package com.isd.iotbay.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import com.isd.iotbay.dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.isd.iotbay.Product;

/**
 *
 * @author jogun
 */
public class ShowCollectionServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
//        try
//        {
//            Connector = new DBConnector();
//        }catch (ClassNotFoundException | SQLException ex){
//            java.util.logging.Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        
//        try
//        {       
//            manager = new DBManager(Connector.openConnection());  
//        }catch (SQLException ex){
//            java.util.logging.Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE,null,ex);
//        }
//        
        //session
        HttpSession session = request.getSession();
        DBManager manager= (DBManager) session.getAttribute("manager");
        
        try {
            ArrayList<Product> inventory = manager.showCollection();
            if (inventory != null) {
                session.setAttribute("inventory", inventory);
                request.getRequestDispatcher("Collection_show.jsp").include(request, response);
                session.setAttribute("show", "IoTBayInventory");
                response.sendRedirect("Collection_show.jsp");
            }
            else{
                request.getRequestDispatcher("Collection_show.jsp").include(request, response);
                session.setAttribute("show", "Inventory does not exist");
                response.sendRedirect("Collection_manage.jsp");
            }
            
                            
        } catch (SQLException ex){
            Logger.getLogger(ShowCollectionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
