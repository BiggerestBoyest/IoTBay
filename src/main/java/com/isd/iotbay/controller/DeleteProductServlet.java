/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.isd.iotbay.controller;


import com.isd.iotbay.Product;
import com.isd.iotbay.dao.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class DeleteProductServlet  extends HttpServlet{
//     private DBManager manager;
//    private DBConnector Connector;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

 

//        //catch exceptions for the DBConnector
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
        //retrieve the product name that was searched for by the user
        Product product = (Product)session.getAttribute("product");
        DBManager manager = (DBManager) session.getAttribute("manager");
        
        try {
            Boolean exists = manager.checkItem(product.getProduct_name()); //check if this item exists in the Inventory
            if (exists) { //if it does...
                manager.deleteItem(product.getProduct_name()); //call the delete function - it is removed from Inventory
                session.setAttribute("deleted", "Product has been deleted."); //success message (no error message)
                request.getRequestDispatcher("Collection_delete.jsp").include(request, response); //request comes from deleteitem.jsp
                response.sendRedirect("Collection_delete_conf.jsp");//the user is taken to the delete confirmation page upon successful deletion
            }
            else {
                session.setAttribute("deleted", "Product has NOT been deleted."); //error message if product does not exist in Inventory
                request.getRequestDispatcher("Collection_delete.jsp").include(request, response); //request comes from deleteItem.jsp
            }
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode() + " and " + ex.getMessage()); //error is stored as DeleteItemServlet should SQL error occur
        }
    }
}
