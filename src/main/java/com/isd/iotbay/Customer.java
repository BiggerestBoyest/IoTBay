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
    private int _id;
    

    public Customer(String firstName,String lastName, String email, String password, String dob, String phone)
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


    public String GetName() { return _firstName + " " + _lastName;}
    public String GetFirstName() { return _firstName;}
    public String GetLastName() { return _lastName;}
    public String GetEmail() { return _email;}
    public String GetPassword()  {return _password;}
    public String GetPhone(){return _phone;}
    public String GetDOB() {return _dob;}
    public int GetID() {return _id;}
    public void SetID(int newID) {_id = newID;}
}
