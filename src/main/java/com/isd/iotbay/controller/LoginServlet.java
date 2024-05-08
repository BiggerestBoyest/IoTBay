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
import com.isd.iotbay.dao.DBManager;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet 
{
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       

        HttpSession currentSession = request.getSession();
        Validator validator = new Validator();
        String email = request.getParameter("email");
        String password  = request.getParameter("password");
        DBManager manager = (DBManager)currentSession.getAttribute("manager");
        Customer customer = null;
        try
        {   if(manager == null)
                System.out.println("null manager");
                
        
                             System.out.println(email);
                System.out.println(password);
   
            customer = manager.ReadCustomer(email, password);
            
        
        } catch (SQLException ex)
        {
              Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        
        if (!validator.ValidateEmail(email)) 
        {
            currentSession.setAttribute("emailError", "Email invalid, please try again.");
            request.getRequestDispatcher("login.jsp").include(request,response);
        } else if (!validator.ValidatePassword(password)) 
        {
            currentSession.setAttribute("passwordError", "Email invalid, please try again.");
            request.getRequestDispatcher("login.jsp").include(request,response);
        } else if (customer != null)
        {
            currentSession.setAttribute("customer",customer);
            request.getRequestDispatcher("Main.jsp").forward(request,response);
        } else
        {
            System.out.println("null test test");
            currentSession.setAttribute("loginError", "User was not found");
            request.getRequestDispatcher("login.jsp").include(request,response);
        }
        
    }

}