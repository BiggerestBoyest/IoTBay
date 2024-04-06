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

    public Customer(String firstName,String lastName, String email, String password, String dob)
    {
        _firstName = firstName;
        _lastName = lastName;
        _email = email;
        _password = password;
        _dob = dob;
    }


    public String GetName() { return _firstName + " " + _lastName;}
    public String GetFirstName() { return _firstName;}
    public String GetLastName() { return _lastName;}
    public String GetEmail() { return _email;}
    public String GetPassword()  {return _password;}
    public String GetDOB() {return _dob;}

}
