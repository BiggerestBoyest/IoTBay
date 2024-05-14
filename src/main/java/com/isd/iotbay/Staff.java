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
public class Staff 
{
    private int _id;
    private String _password;
    private String _firstName;
    private String _email;
    private String _surname;
    private String _dob;
    private String _phone;
    private String _address;
    private String _paymentDetails;

    public Staff(int id, String firstName,String surname,String email,String password, String dob, String phone,String address, String paymentDetails)
    {
        _id = id;
        _firstName = firstName;
        _surname = surname;
        _dob = dob;
        _phone = phone;
        _address = address;
        _paymentDetails = paymentDetails;
        _password = password;
        _email = email;
    }
    
        public String GetName() { return _firstName + " " + _surname;}
        public String GetEmail() {return _email;}
    public int GetID() {return _id;}
    public String GeFirstName() {return _firstName;}
    public String GetSurname(){return _surname;}
    public String GetDOB() {return _dob;}
    public String GetPhone(){return _phone;}
    public String GetAddress() {return _address;}
    public String GetPaymentDetails(){return _paymentDetails;}
    public String GetPassword(){return _password;}
    

}
