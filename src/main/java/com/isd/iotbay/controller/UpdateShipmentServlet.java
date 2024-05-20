package com.isd.iotbay.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import com.isd.iotbay.Customer;
import com.isd.iotbay.Order;
import com.isd.iotbay.dao.DBConnector;
import com.isd.iotbay.dao.DBManager;
import com.isd.iotbay.Shipment;
import com.isd.iotbay.Staff;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class UpdateShipmentServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();

        int shipmentID = Integer.parseInt(request.getParameter("shipmentID"));
        String shipmentMethod = request.getParameter("shipmentMethod");
        String shipmentDate = request.getParameter("shipmentDate");
        String shipmentAddress = request.getParameter("shipmentAddress");
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        Shipment currentShipment = (Shipment)session.getAttribute("currentShipment");

        try {
            manager.updateShipment(shipmentID, order.GetID(), shipmentMethod, shipmentDate, shipmentAddress);
            currentShipment = manager.findShipmentById(shipmentID);
            session.setAttribute("currentShipment", currentShipment);

            List<Shipment> shipments = manager.GetAllShipmentsFromOrder(order.GetID());
            System.out.println(shipments.get(0).getshipmentMethod());
            
            request.setAttribute("shipments", shipments);
            request.setAttribute("shipment",currentShipment);
            request.getRequestDispatcher("updateShipment.jsp").include(request, response);
        } catch (SQLException e) {

            e.printStackTrace();
            request.setAttribute("error", "Unable to update shipment details.");
            request.getRequestDispatcher("updateShipment.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
                DBManager manager = (DBManager) session.getAttribute("manager");

        int shipmentID = Integer.parseInt(request.getParameter("shipmentID"));

        try {
            Shipment shipment = manager.findShipmentById(shipmentID);

            request.setAttribute("shipment", shipment);
            request.getRequestDispatcher("updateShipment.jsp").forward(request, response);
        } catch (  SQLException e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "An error occurred: {0}", e.getMessage());;
            request.setAttribute("error", "Unable to retrieve shipment details.");
            request.getRequestDispatcher("updateShipment.jsp").forward(request, response);
        }
    }
}