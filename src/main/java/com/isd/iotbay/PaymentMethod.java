/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.isd.iotbay;

public class PaymentMethod {
    private int paymentID;
    private String nameOnCard;
    private String cardNum;
    private String expiryDate;
    private String cvs;
    private int orderID;
    private String dateCreated;
    private int customerID;
    private int staffID;

    public PaymentMethod(int paymentID, String nameOnCard, String cardNum, String expiryDate, String cvs, String dateCreated) {
        this.paymentID = paymentID;
        this.dateCreated = dateCreated;
        this.nameOnCard = nameOnCard;
        this.cardNum = cardNum;
        this.expiryDate = expiryDate;
        this.cvs = cvs;
    }

    public void SetOrderID(int id) {
        orderID = id;
    }
    
    public void SetCustomerID(int id){
        customerID = id;
    }
    
      public void SetStaffID(int id){
        staffID = id;
    }
    
    public int GetOrderID(){return orderID;}
    public int GetCustomerID(){return customerID;}
    public int GetStaffID(){return staffID;}

    public int GetPaymentID() {return paymentID;}
    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCvs() {
        return cvs;
    }

    public void setCvs(String cvs) {
        this.cvs = cvs;
    }
}
