package com.isd.iotbay.controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.isd.assignment1.Customer;
import com.isd.iotbay.AccessLog;

import com.isd.iotbay.dao.DBManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class AccessLogServlet extends HttpServlet 
{
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       
        HttpSession currentSession = request.getSession();
        DBManager manager = (DBManager)currentSession.getAttribute("manager");
        Customer customer = (Customer)currentSession.getAttribute("customer");
        ArrayList<AccessLog> logs = new ArrayList();
        try
        {   
            logs = manager.ReadAllLogsFromCustomer(customer.GetID());
            currentSession.setAttribute("allLogs", logs);
        } catch (SQLException ex)
        {
              Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        
        request.getRequestDispatcher("EditProfile.jsp").forward(request, response);

        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException 
    {       
        HttpSession currentSession = request.getSession();
        DBManager manager = (DBManager)currentSession.getAttribute("manager");
        Customer customer = (Customer)currentSession.getAttribute("customer");
        String date = request.getParameter("logFilter");
        currentSession.setAttribute("logError", "");


        if(date.equals(""))
        {
            currentSession.setAttribute("logError", "Invalid date.");
            request.getRequestDispatcher("EditProfile.jsp").forward(request, response);
            return;
        }
        

        ArrayList<AccessLog> logs = new ArrayList();
        try
        {  
            System.out.println(date);
            logs = manager.ReadAllLogsFromCustomerByDate(customer.GetID(),date);
            
            if(logs.size() <= 0) //Alternate case if no value is found by the date, then return all logs instead as a way to "reset" the table
            {
                logs = manager.ReadAllLogsFromCustomer(customer.GetID());
                currentSession.setAttribute("logError", "The date had no logs associated with it.");

            }
            
            currentSession.setAttribute("allLogs", logs);
        } catch (SQLException ex)
        {
              Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        
        request.getRequestDispatcher("EditProfile.jsp").forward(request, response);

    }

}