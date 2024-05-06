package com.isd.iotbay.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.isd.iotbay.dao.*;

public class ConnServlet extends HttpServlet 
{

       private DBConnector dbConnector;
       private DBManager dbManager;
       private Connection con;


       @Override //Create and instance of DBConnector for the deployment session
       public void init() {

           try {

               dbConnector = new DBConnector();

           } catch (ClassNotFoundException | SQLException ex) {

               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

           }      

       }
      
       @Override
       protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
       {
            response.setContentType("text/html;charset=UTF-8");       
            HttpSession session = request.getSession();
            con = dbConnector.OpenConnection();       
           try {

               dbManager = new DBManager(con);

           } catch (SQLException ex) {

               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);

           }


           session.setAttribute("manager", dbManager);           

       }   
        


        public void Destroy() 
        {
           try 
           {
               dbConnector.CloseConnection();
           } catch (SQLException ex) 
           {
               Logger.getLogger(ConnServlet.class.getName()).log(Level.SEVERE, null, ex);
           }

       }

   }