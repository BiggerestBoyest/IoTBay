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
public class Staff extends User
{
  
    public Staff(String firstName, String lastName, String email, String password, String dob, String phone) {
        super(firstName, lastName, email, password, dob, phone);
    }
    
    public Staff(int id,String firstName,String lastName, String email, String password, String dob, String phone)
    {
       super(id,firstName,lastName,email,password,dob,phone);
    }
        
    public Staff(int id,String firstName,String lastName, String email, String password, String dob, String phone, String address, String paymentDetails)
    {
       super(id,firstName,lastName,email,password,dob,phone,address,paymentDetails);
    }
  

}
