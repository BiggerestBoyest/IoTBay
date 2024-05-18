/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isd.iotbay;

import java.io.Serializable;

/**
 *
 * @author Owner
 */
public class Product implements Serializable
{
    private int product_ID; 
    private String product_name; 
    private double cost; 
    private int product_stock;; 
    private String product_deliveryDate;

    public Product(int product_ID, String product_name, double cost, int product_stock, String product_deliveryDate) {
        this.product_ID = product_ID;
        this.product_name = product_name;
        this.cost = cost;
        this.product_stock = product_stock;
        this.product_deliveryDate = product_deliveryDate;
    }

    public int getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public String getProduct_deliveryDate() {
        return product_deliveryDate;
    }

    public void setProduct_deliveryDate(String product_deliveryDate) {
        this.product_deliveryDate = product_deliveryDate;
    }

}
