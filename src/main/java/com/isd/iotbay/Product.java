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
public class Product 
{
    private int _productID;
    private String _productName;
    private float _cost;
    private int _currentStock;
    private String _deliveryDate;

    public Product(int productID, String productName, float cost, int currentStock, String deliveryDate)
    {
        _productID = productID;
        _productName = productName;
        _cost = cost;
        _currentStock = currentStock;
        _deliveryDate = deliveryDate;
    }

}
