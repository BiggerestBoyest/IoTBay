/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isd.iotbay;

import java.util.ArrayList;

/**
 *
 * @author Owner
 */
public class Order
{
    private int _orderID;
    private int _customerID;
    private int _staffID;
    private int _guestID;
    private ArrayList<Product> _products;
    private String _timeOfOrder;
    private String _deliveryAddress;
    private String _invoice;
    public boolean _isSubmitted = false;

    public Order(int orderID, int customerID, ArrayList<Product> products,String timeOfOrder, String deliveryAddress, String invoice)
    {
        _orderID = orderID;
        _customerID = customerID;
        _products = products;
        _timeOfOrder = timeOfOrder;
        _deliveryAddress = deliveryAddress;
        _invoice = invoice;
    }
    
    public Order(int orderID)
    {
        _orderID = orderID;
        _products = new ArrayList();
    }
    
    public ArrayList<Product> GetProducts(){return _products;}
    public int GetID(){return _orderID;}
    public void SetCustomerID(int newID) {_customerID = newID;}
    public void SetStaffID(int newID) {_staffID = newID;}
    public void SetGuestID(int newID) {_guestID = newID;}
    public void AddProduct(Product product) {_products.add(product);}
    public void RemoveProduct(Product product) {_products.remove(product);}
    public void UpdateOrder(String timeOfOrder, String deliveryAddress, boolean isSubmitted){_timeOfOrder = timeOfOrder; _deliveryAddress = deliveryAddress; _isSubmitted = isSubmitted;}

}
