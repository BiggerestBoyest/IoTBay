package com.isd.assignment1;

/**
 *
 * @author Owner
 */
public class Customer 
{
    private String _firstName;
    private String _lastName;
    private String _email;
    private String _password;
    private String _dob;
    private String _phone;
    private String _address;
    private String _paymentDetails;

    private int _id;
    

    public Customer(String firstName,String lastName, String email, String password, String dob, String phone )
    {
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _password = password;
        _dob = dob;
        _phone = phone;
    }
    
      public Customer(int id,String firstName,String lastName, String email, String password, String dob, String phone)
    {
        _id = id;
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _password = password;
        _dob = dob;
        _phone = phone;
    }
      
          
      public Customer(int id,String firstName,String lastName, String email, String password, String dob, String phone, String address, String paymentDetails)
    {
        _id = id;
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _password = password;
        _dob = dob;
        _phone = phone;
        _address = address;
        _paymentDetails = paymentDetails;
    }
  

    public String GetName() { return _firstName + " " + _lastName;}
    public String GetFirstName() { return _firstName;}
    public String GetLastName() { return _lastName;}
    public String GetEmail() { return _email;}
    public String GetPassword()  {return _password;}
    public String GetPhone(){return _phone;}
    public String GetDOB() {return _dob;}
    public String GetAddress(){return _address;}
    public String GetPaymentDetails(){return _paymentDetails;}
    public int GetID() {return _id;}
    public void SetID(int newID) {_id = newID;}
    public void SetAddress(String newAddress) {_address = newAddress;}
    public void SetPaymentDetails(String newPaymentDetails) { _paymentDetails = newPaymentDetails;}
    
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
    
    
    public String GetState()
    {
               if(_address == null)
            return "";
        
        if(_address.isEmpty() || _address.equals("null"))
            return "";
        
        String[] split = _address.trim().split("\\s");
        return split[3];
    }
    
      public String GetPostcode()
    {
              if(_address == null)
            return "";
        
        if(_address.isEmpty() || _address.equals("null"))
            return "";
        
        String[] split = _address.trim().split("\\s");
        return split[4];
    }
    
    public String GetCardNumber()
    {
              if(_paymentDetails == null)
            return "";
        
        if(_paymentDetails.isEmpty() || _paymentDetails.equals("null"))
            return "";
        
        String[] split = _paymentDetails.trim().split("\\s");
        return split[0];
    }
    
       public String GetExpiryDate()
    {
               if(_paymentDetails == null)
            return "";
        
        if(_paymentDetails.isEmpty() || _paymentDetails.equals("null"))
            return "";
           
        String[] split = _paymentDetails.trim().split("\\s");
        return split[1];
    }
       
          public String GetCVV()
    {
              if(_paymentDetails == null)
            return "";
        
        if(_paymentDetails.isEmpty() || _paymentDetails.equals("null"))
            return "";
        String[] split = _paymentDetails.trim().split("\\s");
        return split[2];
    }
}
