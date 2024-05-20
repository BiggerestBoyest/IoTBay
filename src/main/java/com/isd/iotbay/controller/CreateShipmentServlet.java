package com.isd.iotbay.controller;


 //* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 //* Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 

import com.isd.iotbay.Customer;
import com.isd.iotbay.Order;
import com.isd.iotbay.Shipment;
import com.isd.iotbay.Staff;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.isd.iotbay.dao.DBConnector;
import com.isd.iotbay.dao.DBManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.util.logging.Logger;

public class CreateShipmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String shipmentMethod = request.getParameter("shipmentMethod");
        String shipmentDate = request.getParameter("shipmentDate");
        String shipmentAddress = request.getParameter("shipmentAddress");
        HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        
        
        try {
            int shipmentID = manager.GetShipmentID();
            manager.addShipment(shipmentID,order.GetID(), shipmentMethod, shipmentDate, shipmentAddress);
            session.setAttribute("added", "Shipment added successfully.");
            Shipment shipment = new Shipment(shipmentID,order.GetID(),shipmentMethod,shipmentDate,shipmentAddress);
            request.getRequestDispatcher("addShipment.jsp").forward(request, response);
        } catch (SQLException e) {;
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occurred: {0}", e.getMessage());
            request.setAttribute("error", "Unable to add shipment details.");
            request.getRequestDispatcher("addShipment.jsp").forward(request, response);
        }
    }
}