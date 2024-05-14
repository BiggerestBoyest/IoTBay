package com.isd.iotbay.controller;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.isd.iotbay.Staff;
import com.isd.iotbay.AccessLog;

import com.isd.iotbay.dao.DBManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class StaffLoginServlet extends HttpServlet 
{
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       

        HttpSession currentSession = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String password  = request.getParameter("password");
        DBManager manager = (DBManager)currentSession.getAttribute("manager");
        currentSession.setAttribute("customer", null);
        Staff staff = null;
        AccessLog log = null;
        validator.ClearErrors(currentSession);
        try
        {   
            staff = manager.ReadStaff(email, password) == null ? null : manager.ReadStaff(email, password);
        } catch (SQLException ex)
        {
              Logger.getLogger(StaffLoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        
        if (!validator.ValidateEmail(email)) 
        {
            currentSession.setAttribute("emailError", "Email invalid, please try again.");
            request.getRequestDispatcher("StaffLogin.jsp").forward(request,response);
        } else if (!validator.ValidatePassword(password)) 
        {
            currentSession.setAttribute("passwordError", "Password invalid, please try again.");
            request.getRequestDispatcher("StaffLogin.jsp").forward(request,response);
        } else if (staff != null)
        {
            int staffID = staff.GetID();
            String currentDate = LocalDate.now().toString();
            String currentTime = LocalTime.now().toString();
            int logID = -1;
            AccessLog currentLog = null;
            try 
            {
                logID = manager.GenerateNewLogID();
                currentLog = new AccessLog(logID,currentDate, currentTime,staffID);
                manager.GenerateNewAccessLog(logID,currentDate,currentTime, staffID);
            } catch (SQLException ex)
            {
                System.out.println(ex + " sql error");
            }
            currentSession.setAttribute("currentLog", currentLog);
            currentSession.setAttribute("staff",staff);
            request.getRequestDispatcher("Main.jsp").forward(request,response);
        } else
        {
            currentSession.setAttribute("loginError", "User was not found");
            request.getRequestDispatcher("StaffLogin.jsp").forward(request,response);
        }
        
    }

}