/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isd.iotbay.controller;

import com.isd.iotbay.dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
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
public class SearchProductServlet extends HttpServlet {
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
        
        //session
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        session.setAttribute("product", null);
        session.setAttribute("found", null);
        
        String productname = request.getParameter("Product_Name");
        
        try {
            boolean check = manager.checkItem(productname);
            if (check) {
                Product product = manager.fetchProduct(productname);
                session.setAttribute("product", product);

                request.getRequestDispatcher("Collection_searchResult.jsp").forward(request, response);
               // response.sendRedirect("Collection_searchResult.jsp");
            } 
            else {
                session.setAttribute("found", "Item does NOT exist in the Collection");                
                request.getRequestDispatcher("Collection_search.jsp").forward(request, response);
             //   response.sendRedirect("Collection_search.jsp");
            }
                            
        } catch (SQLException ex){
            Logger.getLogger(SearchProductServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
