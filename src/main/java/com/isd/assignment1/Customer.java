package com.isd.assignment1;

/**
 *
 * @author Owner
 */
public class Customer 
{
    private String Name;
    private String Email;
    private String Password;
    private String DOB;

    public Customer(String name, String email, String password, String dob)
    {
        Name = name;
        Email = email;
        Password = password;
        DOB = dob;
    }


    public String GetName() { return Name;}
    public String GetEmail() { return Email;}
    public String GetPassword()  {return Password;}
    public String GetDOB() {return DOB;}

}
