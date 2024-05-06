/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isd.assignment1;

/**
 *
 * @author Owner
 */
public class Order
{
    private int _orderID;
    private int _customerID;
    private Product[] _products;
    private String _timeOfOrder;
    private String _deliveryAddress;
    private String _invoice;

    public Order(int orderID, int customerID, Product[] products,String timeOfOrder, String deliveryAddress, String invoice)
    {
        _orderID = orderID;
        _customerID = customerID;
        _products = products;
        _timeOfOrder = timeOfOrder;
        _deliveryAddress = deliveryAddress;
        _invoice = invoice;
    }
}
