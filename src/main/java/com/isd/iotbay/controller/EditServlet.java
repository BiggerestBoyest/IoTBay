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
import java.time.LocalDate;
import java.time.LocalTime;

public class EditServlet extends HttpServlet 
{
    @Override   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException {       

        HttpSession currentSession = request.getSession();
        Validator validator = new Validator();
        DBManager manager = (DBManager)currentSession.getAttribute("manager");
        Customer customer = (Customer)currentSession.getAttribute("customer");
        String email = request.getParameter("email").equals("") ? customer.GetEmail() : request.getParameter("email") ;
        String password  = request.getParameter("password");
        String firstName  = request.getParameter("fname");
        String lastName  = request.getParameter("lname").equals("") ? customer.GetLastName() : request.getParameter("lname") ;
        String dob  = request.getParameter("dob").equals("") ? customer.GetDOB() : request.getParameter("dob") ;
        String phone  = request.getParameter("phone").equals("") ? customer.GetPhone() : request.getParameter("phone") ;
        int streetNumber  = request.getParameter("streetNumber").equals("") ? -1 : Integer.parseInt(request.getParameter("streetNumber"));
        String streetAddress  = request.getParameter("streetAddress");
        String streetType  = request.getParameter("streetType");
        String state  = request.getParameter("state");
        int postcode =  request.getParameter("postcode").equals("") ? -1 : Integer.parseInt(request.getParameter("postcode"));
        String address = "";
        long cardNumber =  request.getParameter("cardNumber").equals("") ? -1 : Long.parseLong(request.getParameter("cardNumber"));
        String expiryDate = request.getParameter("expiryDate");
        int cvv =  request.getParameter("cvv").equals("") ? -1 : Integer.parseInt(request.getParameter("cvv"));
        String paymentDetails = "";
        
        if(streetAddress.equals("") || streetType.equals("") || state.equals("") || postcode == -1 || streetNumber == -1)
        {
            address = customer.GetAddress();
        } else 
        {
            address =  streetNumber + " " + streetAddress + " " + streetType + " " + state + " " + postcode;
        }
        
        if(cardNumber == -1 || expiryDate.equals("") || cvv == -1)
        {
            paymentDetails = customer.GetPaymentDetails();
        } else 
        {
            paymentDetails = cardNumber + " " + expiryDate + " " + cvv;
        }
                           System.out.println(" error on updating customer");

        validator.ClearErrors(currentSession);
        
     
            if (!validator.ValidateEmail(email)) 
            {
                currentSession.setAttribute("emailError", "Email invalid, please try again.");
                request.getRequestDispatcher("EditProfile.jsp").forward(request,response);
            }
         else if (!validator.ValidatePassword(password)) 
        {
            currentSession.setAttribute("passwordError", "Password invalid, please try again.");
            request.getRequestDispatcher("EditProfile.jsp").forward(request,response);
        } else if (customer != null)
        {
            try
            {
                manager.UpdateCustomer(customer.GetID(),firstName, lastName,email, password, address, paymentDetails,dob,phone);
                customer = manager.ReadCustomer(email, password);

            } catch (SQLException ex)
            {
               System.out.println(ex + " error on updating customer");
            }
            
                           System.out.println(" error on updating customer");
                           
               
            currentSession.setAttribute("customer",customer);
            currentSession.setAttribute("editStatus","Profile updated successfully!");
            request.getRequestDispatcher("EditProfile.jsp").forward(request,response);
        } 
        
    }

}