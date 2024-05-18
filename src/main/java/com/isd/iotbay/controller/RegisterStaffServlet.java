package com.isd.iotbay.controller;
import com.isd.iotbay.AccessLog;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.isd.iotbay.Staff;
import com.isd.iotbay.dao.DBManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegisterStaffServlet extends HttpServlet 
{
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       

        HttpSession currentSession = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String givenName = request.getParameter("fname");
        String surname = request.getParameter("lname");
        String dob = request.getParameter("dob");
        String confirmPassword = request.getParameter("confirmPassword");
        String password  = request.getParameter("password");
        String phoneNumber = request.getParameter("phone");
        System.out.println(phoneNumber + " phone number");
        DBManager manager = (DBManager)currentSession.getAttribute("manager");
        String currentDate = LocalDate.now().toString();
        String currentTime = LocalTime.now().toString();
        Staff staff = null;
        int logID = -1;
        AccessLog currentLog = null;

        validator.ClearErrors(currentSession);
        try
        {   
            staff = manager.ReadStaff(email, password) == null ? null : manager.ReadStaff(email, password);
        } catch (SQLException ex)
        {
              //Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        
        if (!validator.ValidateEmail(email)) 
        {
            currentSession.setAttribute("emailError", "Email invalid, please try again.");
            request.getRequestDispatcher("StaffRegister.jsp").forward(request,response);
        } else if (!validator.ValidatePassword(password)) 
        {
            currentSession.setAttribute("passwordError", "Password invalid, please try again.");
            request.getRequestDispatcher("StaffRegister.jsp").forward(request,response);
        } else if (!validator.ConfirmPassword(password, confirmPassword))
        {
            currentSession.setAttribute("passwordError", "Passwords do not match.");
            request.getRequestDispatcher("StaffRegister.jsp").forward(request,response);
        } else if (staff != null)
        {
            currentSession.setAttribute("loginError","An account with that email is already registered.");
            request.getRequestDispatcher("StaffRegister.jsp").forward(request,response);
        } else // if no customer was found and all fields are valid, then create and add a new customer.
        {
            try
            {
                int newID = manager.GenerateUniqueStaffID();
                staff = new Staff(newID, givenName,surname, email,password,dob,phoneNumber);
                System.out.println(newID + " new id");
                manager.AddStaff(newID, givenName, surname, email, password, dob, phoneNumber);
                logID = manager.GenerateNewLogID();
                currentLog = new AccessLog(logID,currentDate, currentTime, newID);
                manager.GenerateNewAccessLog(logID, currentDate, currentTime, newID);
                
            } catch (SQLException ex)
            {
                System.out.println(ex);
            }
            
            currentSession.setAttribute("currentLog", currentLog);
            currentSession.setAttribute("staff", staff);
            request.getRequestDispatcher("Main.jsp").forward(request,response);
        }
        
    }

}