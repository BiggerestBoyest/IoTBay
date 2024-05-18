package com.isd.iotbay;

import java.io.Serializable;

/**
 *
 * @author Owner
 */
public class Customer extends User implements Serializable
{
 
    public Customer(String firstName, String lastName, String email, String password, String dob, String phone) {
        super(firstName, lastName, email, password, dob, phone);
    }
    
    public Customer(int id,String firstName,String lastName, String email, String password, String dob, String phone)
    {
       super(id,firstName,lastName,email,password,dob,phone);
    }
        
    public Customer(int id,String firstName,String lastName, String email, String password, String dob, String phone, String address, String paymentDetails)
    {
       super(id,firstName,lastName,email,password,dob,phone,address,paymentDetails);
    }
 

   
}
