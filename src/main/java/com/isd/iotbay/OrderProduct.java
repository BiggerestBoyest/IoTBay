/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isd.iotbay;

/**
 *
 * @author Owner
 */
public class OrderProduct 
{
    private int _orderID;
    private int _productID;
    private int _quantity;
    
    public OrderProduct(int orderID, int productID, int quantity)
    {
        _orderID = orderID;
        _productID = productID;
        _quantity = quantity;
    }
    
    public int GetOrderID() {return _orderID;}
    public int GetProductID(){return _productID;}
    public int GetQuantity(){return _quantity;}
    
}
