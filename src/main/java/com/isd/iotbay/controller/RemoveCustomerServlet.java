package com.isd.iotbay.controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.isd.iotbay.Customer;
import com.isd.iotbay.AccessLog;
import com.isd.iotbay.Staff;

import com.isd.iotbay.dao.DBManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class RemoveCustomerServlet extends HttpServlet 
{
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       
        HttpSession currentSession = request.getSession();

        DBManager manager = (DBManager)currentSession.getAttribute("manager");
        Customer customer = (Customer)currentSession.getAttribute("customer");
        Staff staff = (Staff)currentSession.getAttribute("staff");

        AccessLog log = (AccessLog)currentSession.getAttribute("currentLog");
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().toString();
        try
        {   
            if(customer != null)
            {
                manager.UpdateAccessLog(log.GetLogID(), log.GetCustomerID(), currentDate, currentTime);
                manager.DeleteCustomer(customer.GetID());
            } else 
            {
                manager.UpdateAccessLog(log.GetLogID(), log.GetStaffID(), currentDate, currentTime);
                manager.DeleteStaff(staff.GetID());
            }
           
        } catch (SQLException ex)
        {
              Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        
        request.getRequestDispatcher("Logout.jsp").forward(request, response);

        
    }
    
  

}