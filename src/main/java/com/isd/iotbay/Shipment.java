package com.isd.iotbay;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Brandon
 */
import java.io.Serializable;


public class Shipment implements Serializable {
    private int shipmentID;
    private int orderID;
    private String shipmentMethod;
    private String shipmentDate;
    private String shipmentAddress;

    public Shipment(int shipmentID, int orderID, String shipmentMethod, String shipmentDate, String shipmentAddress) {
        this.shipmentID = shipmentID;
        this.orderID = orderID;
        this.shipmentMethod = shipmentMethod;
        this.shipmentDate = shipmentDate;
        this.shipmentAddress = shipmentAddress;
    }
    public int getshipmentID() {
        return shipmentID;
    }

    public void setshipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public int getorderID() {
        return orderID;
    }

    public void setorderID(int orderID) {
        this.orderID = orderID;
    }

    public String getshipmentMethod() {
        return shipmentMethod;
    }

    public void setshipmentMethod(String shipmentMethod) {
        this.shipmentMethod = shipmentMethod;
    }

    public String getshipmentDate() {
        return shipmentDate;
    }

    public void setshipmentDate(String shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getshipmentAddress() {
        return shipmentAddress;
    }

    public void setshipmentAddress(String shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }
}
