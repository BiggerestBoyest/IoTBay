package com.isd.iotbay.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */



import com.isd.iotbay.Customer;
import com.isd.iotbay.Order;
import com.isd.iotbay.Staff;
import com.isd.iotbay.dao.DBConnector;
import com.isd.iotbay.dao.DBManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteShipmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();

        int shipmentID = Integer.parseInt(request.getParameter("shipmentID"));
    DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        try {
            manager.deleteShipment(shipmentID);
            response.sendRedirect("viewShipment.jsp");
        } catch (  SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occurred: {0}", e.getMessage());
            request.setAttribute("error", "Unable to delete shipment details.");
            request.getRequestDispatcher("viewShipment.jsp").forward(request, response);
        }
    }
}