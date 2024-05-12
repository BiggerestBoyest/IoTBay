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

public class RegisterServlet extends HttpServlet 
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
        Customer customer = null;
        validator.ClearErrors(currentSession);
        try
        {   
            customer = manager.ReadCustomer(email, password) == null ? null : manager.ReadCustomer(email, password);
        } catch (SQLException ex)
        {
              //Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);       
        }
        
        if (!validator.ValidateEmail(email)) 
        {
            currentSession.setAttribute("emailError", "Email invalid, please try again.");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
        } else if (!validator.ValidatePassword(password)) 
        {
            currentSession.setAttribute("passwordError", "Password invalid, please try again.");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
        } else if (!validator.ConfirmPassword(password, confirmPassword))
        {
            currentSession.setAttribute("passwordError", "Passwords do not match.");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
        } else if (customer != null)
        {
            currentSession.setAttribute("loginError","An account with that email is already registered.");
            request.getRequestDispatcher("Register.jsp").forward(request,response);
        } else // if no customer was found and all fields are valid, then create and add a new customer.
        {
            customer = new Customer(givenName,surname, email,password,dob,phoneNumber);
            try
            {
                int newID = manager.GenerateUniqueID();
                System.out.println(newID + " new id");
                manager.AddCustomer(newID, givenName, surname, email, password, dob, phoneNumber);
                
            } catch (SQLException ex)
            {
                System.out.println(ex);
            }
            currentSession.setAttribute("customer", customer);
            request.getRequestDispatcher("Main.jsp").forward(request,response);
        }
        
    }

}