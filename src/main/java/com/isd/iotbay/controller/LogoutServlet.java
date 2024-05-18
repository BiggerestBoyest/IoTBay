package com.isd.iotbay.controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.isd.iotbay.AccessLog;
import com.isd.iotbay.Customer;
import com.isd.iotbay.Staff;

import com.isd.iotbay.dao.DBManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogoutServlet extends HttpServlet 
{
    @Override   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       

        HttpSession currentSession = request.getSession();
        DBManager manager = (DBManager)currentSession.getAttribute("manager");
        AccessLog log = (AccessLog)currentSession.getAttribute("currentLog");
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().toString();
        
        Customer customer = (Customer)currentSession.getAttribute("customer");
        
        try
        {
            if (customer != null)
                manager.UpdateAccessLog(log.GetLogID(), log.GetCustomerID(), currentDate, currentTime);
            else
                manager.UpdateAccessLog(log.GetLogID(), currentDate, currentTime, log.GetStaffID());

        } catch (SQLException ex)
        {
            System.out.println(ex + " error on update log");
        }
        
        currentSession.invalidate();
        request.getRequestDispatcher("Logout.jsp").forward(request, response);
    }

}