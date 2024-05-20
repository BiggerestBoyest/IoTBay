package com.isd.iotbay.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */


import com.isd.iotbay.Customer;
import com.isd.iotbay.Order;
import com.isd.iotbay.PaymentMethod;
import com.isd.iotbay.dao.DBConnector;
import com.isd.iotbay.dao.DBManager;
import com.isd.iotbay.Shipment;
import com.isd.iotbay.Staff;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class AddShipmentToOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                HttpSession session = request.getSession();
        DBManager manager = (DBManager) session.getAttribute("manager");
        Customer customer = (Customer)session.getAttribute("customer");
        Staff staff = (Staff)session.getAttribute("staff");
        Order order = (Order)session.getAttribute("currentOrder");
        ArrayList<PaymentMethod> methods = new ArrayList();
        Shipment currentShipment = (Shipment)session.getAttribute("currentShipment");
        int shipmentID = Integer.parseInt(request.getParameter("shipmentID"));
        session.setAttribute("updatedShipment", null);
       

        try
        {
            
            if(currentShipment != null)
            {
                System.out.println(currentShipment.getshipmentID());
                manager.removeShipmentFromOrder(currentShipment.getshipmentID(),order.GetID());
            }
            
            manager.addShipmentToOrder(shipmentID, order.GetID());
            currentShipment = manager.findShipmentById(shipmentID);
            
        } catch(SQLException ex){System.out.println(ex);}
        
        session.setAttribute("currentShipment", currentShipment);
        session.setAttribute("updatedShipment", "Shipment added to order");

        request.getRequestDispatcher("viewShipment.jsp").include(request, response);
    }
}

 