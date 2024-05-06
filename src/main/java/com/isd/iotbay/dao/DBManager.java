package com.isd.iotbay.dao;
import com.isd.assignment1.Customer;
import java.sql.*;
import java.util.ArrayList;

public class DBManager 
{

    private Statement statement;

    public DBManager(Connection conn) throws SQLException 
    {       
       statement = conn.createStatement();   
    }

    public Customer ReadCustomer(String email, String password) throws SQLException 
    {   
        String query = "SELECT * FROM USERDB.Customers WHERE EMAIL= '" + email + "' and PASSWORD='" + password  + "'";
        ResultSet set = statement.executeQuery(query);
        
        while(set.next())
        {
            String customerEmail = set.getString(4);
            String customerPassword = set.getString(5);
            
            if(customerEmail.equals(email) && customerPassword.equals(customerPassword))
            {
                String customerGivenName = set.getString(2);
                String customerSurname = set.getString(3);
                String customerDOB = set.getString(8);
                Customer customer = new Customer(customerGivenName, customerSurname,customerEmail,customerPassword,customerDOB);
            }
        }

       return null;   
    }

    public void AddCustomer(String givenName, String surname,String email, String password, String dob) throws SQLException 
    {               
        String query = "INSERT INTO USERDB.Customers " + "VALUES ('" + givenName +  "', '"  + surname + "', '" +   email + "', '" + password + "', '" + dob + "')";
        statement.executeUpdate(query);
    }

    public void UpdateCustomer( String givenName, String surname,String email, String password, String dob) throws SQLException 
    {       
        String query = "UPDATE USERDB.Customers SET GIVENNAME ='" + givenName + "',SURNAME='" + surname + "',PASSWORD='" + password + "',EMAIL='" + email + "',DOB=" + dob + "' WHERE EMAIL='" + email + "'";
        statement.executeUpdate(query);
    }       

    public void DeleteCustomer(String email) throws SQLException
    {
        String query = "DELET EFROM USERDB.Customers WHERE EMAIL='" + email + "'";
        statement.executeUpdate(query);
    }
    
    public ArrayList<Customer> GetAllCustomers() throws SQLException
    {
        String query = "SELECT * FROM CUSTOMERS";
        ResultSet set = statement.executeQuery(query);
        ArrayList<Customer> customers = new ArrayList<>();
        
        
        while(set.next())
        {
            String givenName = set.getString(2);
            String surname = set.getString(3);
            String email = set.getString(4);
            String password = set.getString(5);
            String dob = set.getString(8);
            Customer customer = new Customer(givenName,surname,email,password,dob);
            customers.add(customer);
        }
        
        return customers;
    }


 

}