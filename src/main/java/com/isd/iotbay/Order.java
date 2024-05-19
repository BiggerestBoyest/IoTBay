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
        private String _dateOfOrder;
    private String _timeOfOrder;
    private String _address;
    private String _invoice;
    private boolean _isSubmitted = false;

    public Order(int orderID, int customerID,String dateOfOrder,String timeOfOrder, String deliveryAddress)
    {
        _orderID = orderID;
        _customerID = customerID;
        _timeOfOrder = timeOfOrder;
        _address = deliveryAddress;
        _dateOfOrder = dateOfOrder;
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
    public void SetAddress(String address) {_address = address;}
    public void SetTime(String time) {_timeOfOrder = time;}
    public void SetDate(String date) {_dateOfOrder = date;}
    public void AddProduct(Product product) {_products.add(product);}
    public void SetProducts(ArrayList<Product> products) {_products = products;}
    public void RemoveProduct(int productID) 
    {
        Product target = null;
        for(Product product : _products)
        {
            System.out.println("PRODUCTS: " + product.getProduct_name());
            if(product.getProduct_ID() == productID)
                target = product;
        }
        
        _products.remove(target);
    
    }
    public void SetAsSubmitted(){_isSubmitted = true;}
    public String GetAddress(){return _address;}
    public String GetDate(){return _timeOfOrder;}
    public String GetTime(){return _dateOfOrder;}
    public boolean IsSubmitted() {return _isSubmitted;}
    
     public String GetStreetNumber()
    {
        if(_address == null)
            return "";
        
        if(_address.isEmpty() || _address.equals("null"))
            return "";
        
        String[] split = _address.trim().split("\\s");
        System.out.println(split[0] + " street num");
        return split[0];
    }
    
        
    public String GetStreet()
    {
             if(_address == null)
            return "";
        
        if(_address.isEmpty() || _address.equals("null"))
            return "";
        
        String[] split = _address.trim().split("\\s");
        return split[1];
    }
    
    
        
    public String GetStreetType()
    {
              if(_address == null)
            return "";
        
        if(_address.isEmpty() || _address.equals("null"))
            return "";
        
        String[] split = _address.trim().split("\\s");
        return split[2];
    }
    
        public String GetSuburb()
    {
              if(_address == null)
            return "";
        
        if(_address.isEmpty() || _address.equals("null"))
            return "";
        
        String[] split = _address.trim().split("\\s");
        return split[3];
    }
    
    public String GetState()
    {
               if(_address == null)
            return "";
        
        if(_address.isEmpty() || _address.equals("null"))
            return "";
        
        String[] split = _address.trim().split("\\s");
        return split[4];
    }
    
      public String GetPostcode()
    {
              if(_address == null)
            return "";
        
        if(_address.isEmpty() || _address.equals("null"))
            return "";
        
        String[] split = _address.trim().split("\\s");
        return split[5];
    }
}
