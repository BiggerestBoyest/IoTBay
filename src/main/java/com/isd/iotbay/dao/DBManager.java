package com.isd.iotbay.dao;
import com.isd.assignment1.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import com.isd.iotbay.AccessLog;

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
            int customerID = set.getInt(1);
            String customerEmail = set.getString(4);
            String customerPassword = set.getString(5);

            if(customerEmail.equals(email) && customerPassword.equals(customerPassword))
            {
                String customerGivenName = set.getString(2);
                String customerSurname = set.getString(3);
                String customerDOB = set.getString(8);
                String customerPhone = set.getString(9);
                Customer customer = new Customer(customerID,customerGivenName, customerSurname,customerEmail,customerPassword,customerDOB,customerPhone);
                return customer;
            }
        }

       return null;   
    }
    
     public ArrayList<AccessLog> ReadAllLogsFromCustomer(int customerID) throws SQLException 
    {   
        String query = "SELECT * FROM USERDB.ACCESSLOGS WHERE FK_CUSTOMERID= " + customerID;
        ResultSet set = statement.executeQuery(query);
        ArrayList<AccessLog> logs = new ArrayList<>();

        while(set.next())
        {
            int queriedCustomerID = set.getInt(6);

            if(customerID == queriedCustomerID)
            {
                int logID = set.getInt(1);
                String loginDate = set.getString(2);
                String loginTime = set.getString(3);
                String logoutDate = set.getString(4);
                String logoutTime = set.getString(5);
                AccessLog log = new AccessLog(logID,queriedCustomerID,loginDate,loginTime,logoutDate,logoutTime);
                logs.add(log);
            }
        }

       return logs;   
    }
     
       public ArrayList<AccessLog> ReadAllLogs() throws SQLException 
    {   
        String query = "SELECT * FROM USERDB.ACCESSLOGS";
        ResultSet set = statement.executeQuery(query);
        ArrayList<AccessLog> logs = new ArrayList<>();

        while(set.next())
        {
                int logID = set.getInt(1);
                String loginDate = set.getString(2);
                String loginTime = set.getString(3);
                String logoutDate = set.getString(4);
                String logoutTime = set.getString(5);
                int customerID = set.getInt(6);
                AccessLog log = new AccessLog(logID,customerID,loginDate,loginTime,logoutDate,logoutTime);
                logs.add(log);
        }

       return logs;   
    }
    
    public void AddCustomer(int ID, String givenName, String surname,String email, String password, String dob, String phoneNumber) throws SQLException 
    {               
        String query = "INSERT INTO USERDB.Customers (ID,GIVENNAME,SURNAME,EMAIL,PASSWORD,PHONE,DOB)" + " VALUES (" + ID + ", '" + givenName +  "', '"  + surname + "', '" +   email + "', '" + password + "', '" + phoneNumber + "', '" + dob + "')";
        statement.executeUpdate(query);
    }

    public void AddCustomer(String givenName, String surname,String email, String password, String dob, String phoneNumber) throws SQLException 
    {               
        String query = "INSERT INTO USERDB.Customers " + " (GIVENNAME,SURNAME,EMAIL,PASSWORD,PHONE,DOB)VALUES ('" + givenName +  "', '"  + surname + "', '" +   email + "', '" + password +    "', '" +  phoneNumber + "', '" + dob + "')";
        statement.executeUpdate(query);
    }

    public void UpdateCustomer( String givenName, String surname,String email, String password, String dob, String phoneNumber) throws SQLException 
    {       
        String query = "UPDATE USERDB.Customers SET GIVENNAME ='" + givenName + "',SURNAME='" + surname + "',PASSWORD='" + password + "',EMAIL='" + email + "',DOB=" + dob + "' WHERE EMAIL='" + email + "'";
        statement.executeUpdate(query);
    }       

    public void DeleteCustomer(String email) throws SQLException
    {
        String query = "DELETE EFROM USERDB.Customers WHERE EMAIL='" + email + "'";
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
            String phone = set.getString(9);
            Customer customer = new Customer(givenName,surname,email,password,dob,phone);
            customers.add(customer);
        }
        
        return customers;
    }
    
    public void GenerateNewAccessLog(int logID, int customerID, String loginDate, String loginTime) throws SQLException
    {
        
        String subquery = "SELECT ID FROM USERDB.Customers WHERE ID= " + customerID;
        String query = "INSERT INTO USERDB.ACCESSLOGS (LOG_ID, FK_CUSTOMERID, LOGIN_DATE, LOGIN_TIME)" + " VALUES (" + logID + ", " + "(" + subquery + ")"  + ", '" + loginDate +  "', '"  + loginTime + "')";
        System.out.println(logID + " log id");
        statement.executeUpdate(query);
    }
    
    public int GenerateNewLogID() throws SQLException
    {
        ArrayList<AccessLog> logs = ReadAllLogs();
        int logID = -1;
        
        if (!logs.isEmpty())
        {
            System.out.println(logs.size() + " size");
            logID = logs.size() + 1;
        }
        else
        {
            System.out.println("log is empty");
            logID = 1;
        }
        
        return logID;
    }
    
    public void UpdateAccessLog(int logID, int customerID, String logoutDate, String logoutTime) throws SQLException 
    {       
        String query = "UPDATE USERDB.ACCESSLOGS SET LOGOUT_DATE='" + logoutDate + "',LOGOUT_TIME='" + logoutTime +  "' WHERE LOG_ID=" + logID + "" + "AND FK_CUSTOMERID=" + customerID;
        statement.executeUpdate(query);
    }     
    
    public int GenerateUniqueID()
    {
        ArrayList<Customer> customers = new ArrayList();
        try
        {
           customers = GetAllCustomers();
        } catch (SQLException ex)
        {
            System.out.println(ex + " sql error");
        }
        
        Random random = new Random();
        int value = 0;
        boolean duplicateFound = false;
        
        while(true)
        {
            value = random.nextInt(9999);

            for(int i = 0; i < customers.size(); i++)
            {
                if (customers.get(i).GetID() == value)
                {
                    duplicateFound = true;
                    break;
                }
                    
            }
            
            if(!duplicateFound)
                return value;
            
        }
        
    }


 

}