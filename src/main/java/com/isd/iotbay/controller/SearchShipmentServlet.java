package com.isd.iotbay.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import com.isd.iotbay.Customer;
import com.isd.iotbay.Order;
import com.isd.iotbay.Shipment;
import com.isd.iotbay.Staff;
import com.isd.iotbay.dao.DBConnector;
import com.isd.iotbay.dao.DBManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SearchShipmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
               HttpSession session = request.getSession();

        String shipmentIDParam = request.getParameter("shipmentID");
        String shipmentDate = request.getParameter("shipmentDate");
 DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        try {
            List<Shipment> shipments;

            if (shipmentIDParam != null && !shipmentIDParam.isEmpty()) {
                int shipmentID = Integer.parseInt(shipmentIDParam);
                Shipment shipment = manager.findShipmentById(shipmentID);
                if (shipment != null) {
                    shipments = new ArrayList<>();
                    shipments.add(shipment);
                } else {
                    shipments = new ArrayList<>();
                }
            } else if (shipmentDate != null && !shipmentDate.isEmpty()) {
                shipments = manager.findShipmentsByDate(shipmentDate);
            } else {
                shipments = manager.getAllShipments();
            }

            request.setAttribute("shipments", shipments);
            request.getRequestDispatcher("searchshipmentresults.jsp").forward(request, response);
        } catch (  SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occurred: {0}", e.getMessage());
            request.setAttribute("error", "Unable to retrieve shipment details.");
            request.getRequestDispatcher("searchshipmentresults.jsp").forward(request, response);
        }
    }
}