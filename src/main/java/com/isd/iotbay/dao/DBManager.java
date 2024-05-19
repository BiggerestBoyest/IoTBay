package com.isd.iotbay.dao;
import com.isd.iotbay.Customer;
import com.isd.iotbay.Staff;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import com.isd.iotbay.AccessLog;
import com.isd.iotbay.Order;
import com.isd.iotbay.OrderProduct;
import com.isd.iotbay.Product;

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
                String customerAddress = set.getString(6);
                String customerPaymentDetails = set.getString(7);

                Customer customer = new Customer(customerID,customerGivenName, customerSurname,customerEmail,customerPassword,customerDOB,customerPhone,customerAddress,customerPaymentDetails);
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
     
      
     public ArrayList<AccessLog> ReadAllLogsFromStaff(int staffID) throws SQLException 
    {   
        String query = "SELECT * FROM USERDB.ACCESSLOGS WHERE FK_STAFF_ID= " + staffID;
        ResultSet set = statement.executeQuery(query);
        ArrayList<AccessLog> logs = new ArrayList<>();

        while(set.next())
        {
            int queriedStaffID = set.getInt(7);

            if(staffID == queriedStaffID)
            {
                int logID = set.getInt(1);
                String loginDate = set.getString(2);
                String loginTime = set.getString(3);
                String logoutDate = set.getString(4);
                String logoutTime = set.getString(5);
                AccessLog log = new AccessLog(logID,loginDate,loginTime,logoutDate,logoutTime, queriedStaffID);
                logs.add(log);
            }
        }

       return logs;   
    }
     
       public ArrayList<AccessLog> ReadAllLogsFromCustomerByDate(int customerID, String date) throws SQLException 
    {   
        String query = "SELECT * FROM USERDB.ACCESSLOGS WHERE FK_CUSTOMERID= " + customerID + " AND LOGIN_DATE='" + date + "'";
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
       
    public ArrayList<AccessLog> ReadAllLogsFromStaffByDate(int staffID, String date) throws SQLException 
    {   
        String query = "SELECT * FROM USERDB.ACCESSLOGS WHERE FK_STAFF_ID= " + staffID + " AND LOGIN_DATE='" + date + "'";
        ResultSet set = statement.executeQuery(query);
        ArrayList<AccessLog> logs = new ArrayList<>();

        while(set.next())
        {
            int queriedStaffID = set.getInt(7);

            if(staffID == queriedStaffID)
            {
                int logID = set.getInt(1);
                String loginDate = set.getString(2);
                String loginTime = set.getString(3);
                String logoutDate = set.getString(4);
                String logoutTime = set.getString(5);
                AccessLog log = new AccessLog(logID,loginDate,loginTime,logoutDate,logoutTime,queriedStaffID);
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
                int staffID = set.getInt(7);
                AccessLog log = new AccessLog(logID,customerID,staffID,loginDate,loginTime,logoutDate,logoutTime);
                logs.add(log);
        }

       return logs;   
    }
    
    public void AddCustomer(int ID, String givenName, String surname,String email, String password, String dob, String phoneNumber) throws SQLException 
    {               
        String query = "INSERT INTO USERDB.Customers (ID,GIVENNAME,SURNAME,EMAIL,PASSWORD,PHONE,DOB)" + " VALUES (" + ID + ", '" + givenName +  "', '"  + surname + "', '" +   email + "', '" + password + "', '" + phoneNumber + "', '" + dob + "')";
        statement.executeUpdate(query);
    }
    
     public void AddStaff(int ID, String givenName, String surname,String email, String password, String dob, String phoneNumber) throws SQLException 
    {               
        String query = "INSERT INTO USERDB.Staff (STAFF_ID,GIVENNAME,SURNAME,EMAIL,PASSWORD,PHONE,DOB)" + " VALUES (" + ID + ", '" + givenName +  "', '"  + surname + "', '" +   email + "', '" + password + "', '" + phoneNumber + "', '" + dob + "')";
        statement.executeUpdate(query);
    }

    public void AddCustomer(String givenName, String surname,String email, String password, String dob, String phoneNumber) throws SQLException 
    {               
        String query = "INSERT INTO USERDB.Customers " + " (GIVENNAME,SURNAME,EMAIL,PASSWORD,PHONE,DOB)VALUES ('" + givenName +  "', '"  + surname + "', '" +   email + "', '" + password +    "', '" +  phoneNumber + "', '" + dob + "')";
        statement.executeUpdate(query);
    }

    public void UpdateCustomer(int customerID, String givenName, String surname, String email, String password, String address, String paymentDetails, String dob, String phone ) throws SQLException 
    {       
        String query = "UPDATE USERDB.Customers SET GIVENNAME ='" + givenName + "',SURNAME='" + surname + "',PASSWORD='" + password + "',EMAIL='" + email + "',DOB='" + dob + "' , ADDRESS ='" + address + "' , PAYMENTDETAILS='" + paymentDetails + "', PHONE='" + phone +  "' WHERE ID=" + customerID;
        statement.executeUpdate(query);
    }       
    
      public void UpdateStaff(int staffID, String givenName, String surname, String email, String password, String address, String paymentDetails, String dob, String phone ) throws SQLException 
    {       
        String query = "UPDATE USERDB.Staff SET GIVENNAME ='" + givenName + "',SURNAME='" + surname + "',PASSWORD='" + password + "',EMAIL='" + email + "',DOB='" + dob + "' , ADDRESS ='" + address + "' , PAYMENTDETAILS='" + paymentDetails + "', PHONE='" + phone +  "' WHERE STAFF_ID=" + staffID;
        statement.executeUpdate(query);
    }      

    public void DeleteCustomer(int customerID) throws SQLException
    {
        String query = "UPDATE USERDB.Customers SET GIVENNAME =" + "NULL" + ",SURNAME=" + "NULL"  + ",PASSWORD=" + "NULL"  + ",EMAIL=" + "NULL"  + ",DOB=" + "NULL"  + " , ADDRESS =" + "NULL"  + " , PAYMENTDETAILS=" + "NULL"  + ", PHONE=" + "NULL"  +  " WHERE ID=" + customerID;
        //String query = "DELETE FROM USERDB.Customers WHERE ID= " + customerID ;
        statement.executeUpdate(query);
    }
    
     public void DeleteStaff(int staffID) throws SQLException
    {
        String query = "UPDATE USERDB.Staff SET GIVENNAME =" + "NULL" + ",SURNAME=" + "NULL"  + ",PASSWORD=" + "NULL"  + ",EMAIL=" + "NULL"  + ",DOB=" + "NULL"  + " , ADDRESS =" + "NULL"  + " , PAYMENTDETAILS=" + "NULL"  + ", PHONE=" + "NULL"  +  " WHERE STAFF_ID=" + staffID;
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
    
     public ArrayList<Staff> GetAllStaff() throws SQLException
    {
        String query = "SELECT * FROM STAFF";
        ResultSet set = statement.executeQuery(query);
        ArrayList<Staff> staffs = new ArrayList<>();
        
        while(set.next())
        {
            String givenName = set.getString(2);
            String surname = set.getString(3);
            String email = set.getString(8);
            String password = set.getString(9);
            String dob = set.getString(5);
            String phone = set.getString(9);
            Staff staff = new Staff(givenName,surname,email,password,dob,phone);
            staffs.add(staff);
        }
        
        return staffs;
    }
    
    public void GenerateNewAccessLog(int logID, int customerID, String loginDate, String loginTime) throws SQLException
    {
        
        String subquery = "SELECT ID FROM USERDB.Customers WHERE ID= " + customerID;
        String query = "INSERT INTO USERDB.ACCESSLOGS (LOG_ID, FK_CUSTOMERID, LOGIN_DATE, LOGIN_TIME)" + " VALUES (" + logID + ", " + "(" + subquery + ")"  + ", '" + loginDate +  "', '"  + loginTime + "')";
        System.out.println(logID + " log id");
        statement.executeUpdate(query);
    }
    
      public void GenerateNewAccessLog(int logID,  String loginDate, String loginTime, int staffID) throws SQLException
    {
        
        String subquery = "SELECT STAFF_ID FROM USERDB.Staff WHERE STAFF_ID= " + staffID;
        String query = "INSERT INTO USERDB.ACCESSLOGS (LOG_ID, FK_STAFF_ID, LOGIN_DATE, LOGIN_TIME)" + " VALUES (" + logID + ", " + "(" + subquery + ")"  + ", '" + loginDate +  "', '"  + loginTime + "')";
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
    
       public void UpdateAccessLog(int logID, String logoutDate, String logoutTime, int staffID) throws SQLException 
    {       
        String query = "UPDATE USERDB.ACCESSLOGS SET LOGOUT_DATE='" + logoutDate + "',LOGOUT_TIME='" + logoutTime +  "' WHERE LOG_ID=" + logID + "" + "AND FK_STAFF_ID=" + staffID;
        statement.executeUpdate(query);
    }     
    
    public Staff ReadStaff(String email, String password) throws SQLException
    {
        String query = "SELECT * FROM USERDB.Staff WHERE EMAIL= '" + email + "' and PASSWORD='" + password  + "'";
        ResultSet set = statement.executeQuery(query);
        
        while(set.next())
        {
            int staffID = set.getInt(1);
            String staffEmail = set.getString(8);
            String staffPassword = set.getString(9);

            if(staffEmail.equals(email) && staffPassword.equals(staffPassword))
            {
                String staffGivenName = set.getString(2);
                String staffSurname = set.getString(3);
                String staffDOB = set.getString(5);
                String staffPhone = set.getString(4);
                String staffAddress = set.getString(6);
                String staffPaymentDetails = set.getString(7);

                Staff staff = new Staff(staffID,staffGivenName, staffSurname,staffEmail,staffPassword,staffDOB,staffPhone,staffAddress,staffPaymentDetails);
                return staff;
            }
        }

       return null; 
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

      public int GenerateUniqueStaffID()
    {
        ArrayList<Staff> staff = new ArrayList();
        try
        {
           staff = GetAllStaff();
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

            for(int i = 0; i < staff.size(); i++)
            {
                if (staff.get(i).GetID() == value)
                {
                    duplicateFound = true;
                    break;
                }
                    
            }
            
            if(!duplicateFound)
                return value;
            
        }
        
    }
            
    public void addProduct(String product_name, double cost, int stock, String product_deliveryDate) throws SQLException 
    {

    int temp = 0;
    String query = "INSERT INTO USERDB.PRODUCTS VALUES ("+temp+",'"+product_name+"', "+cost+", "+stock+", '"+product_deliveryDate+"')";
    statement.executeUpdate(query);

    String getid = "UPDATE USERDB.PRODUCTS SET PRODUCT_ID = (SELECT MAX(PRODUCT_ID) FROM USERDB.PRODUCTS) + 1 WHERE PRODUCT_ID = 0";
    statement.executeUpdate(getid);
}

public void deleteItem(String Product_name) throws SQLException {
    String query = "DELETE FROM USERDB.PRODUCTS WHERE (PRODUCT_NAME) = ('"+Product_name+"')";
    statement.executeUpdate(query);
}

public void updateItem(int Product_id, String Product_name,  double cost, int Product_stock, String Product_deliveryDate) throws SQLException {        
    String query = "UPDATE USERDB.PRODUCTS SET PRODUCT_NAME = ('"+Product_name+"'), PRODUCT_COST = ("+cost+"), PRODUCT_STOCK = ("+Product_stock+"), PRODUCT_DELIVERYDATE = ('"+Product_deliveryDate+"') WHERE PRODUCT_ID = ("+Product_id+")";
    statement.executeUpdate(query);

}

public boolean checkItem(String Product_name) throws SQLException {
    String query = "SELECT * FROM USERDB.PRODUCTS WHERE (PRODUCT_NAME) = '"+Product_name+"'";
    ResultSet rs = statement.executeQuery(query);
        System.out.println(Product_name + "<- NAME");

    while (rs.next()) {
        String item_productname = rs.getString(2);
        System.out.println(Product_name + "<- NAME QUERY_NAME ->" + item_productname);
        if (item_productname.equals(Product_name)) {
            return true;
        }
    }
    return false;
}

public int fetchProductid(String Product_name) throws SQLException {
    String query = "SELECT PRODUCT_ID FROM USERDB.PRODUCTS WHERE PRODUCT_NAME = '"+Product_name+"'";
    ResultSet rs = statement.executeQuery(query);
    int productid = 0;

    while (rs.next()) {
        productid = rs.getInt(1);
    }
    //return null;
    return productid;
}

public Product fetchProduct(String Product_name) throws SQLException {
    String query = "SELECT * FROM userdb.PRODUCTS WHERE (PRODUCT_NAME) = '"+Product_name+"'";
    ResultSet rs = statement.executeQuery(query);

    while (rs.next()) {
        String item_productname = rs.getString(2);
        if (item_productname.equals(Product_name)) {
            int item_productid = rs.getInt(1);
            double item_price = rs.getDouble(3);
            int item_stock = rs.getInt(4);
            String item_deliveryDate = rs.getString(5);
            return new Product(item_productid, item_productname, item_price, item_stock, item_deliveryDate);
        }
    }
    return null;
}

public ArrayList<Product> showCollection() throws SQLException {
    String query = "SELECT * FROM USERDB.PRODUCTS ORDER BY PRODUCT_ID";
    ResultSet rs = statement.executeQuery(query);
    ArrayList<Product> inventory = new ArrayList();
        System.out.println("s");

    while (rs.next()) {
        int item_productid = rs.getInt(1);
        String item_productname = rs.getString(2);
        double item_price = rs.getDouble(3);
        int item_stock = rs.getInt(4);
        String item_deliveryDate = rs.getString(5);
        System.out.println(item_productname);

        inventory.add(new Product(item_productid, item_productname, item_price, item_stock, item_deliveryDate));
    }

    return inventory;
}


public ArrayList<Product> ShowAllCustomerOrders(int customerID) throws SQLException 
{
        String query = "SELECT * FROM USERDB.ORDERS ORDER BY PRODUCT_NAME WHERE CUSTOMER_ID=" + customerID;
        return ShowAllOrders(query);
}

public ArrayList<Product> ShowAllStaffOrders(int staffID) throws SQLException 
{
        String query = "SELECT * FROM USERDB.ORDERS ORDER BY PRODUCT_NAME WHERE STAFF_ID=" + staffID;
        return ShowAllOrders(query);
}

public ArrayList<Product> ShowAllGuestOrders(int guestID) throws SQLException 
{
        String query = "SELECT * FROM USERDB.ORDERS ORDER BY PRODUCT_NAME WHERE GUEST_ID=" + guestID;
        return ShowAllOrders(query);
}





public void CreateCustomerOrder(int orderID, int customerID, String orderDate, String orderTime) throws SQLException 
{
    String query = "INSERT INTO USERDB.Orders (ORDER_ID,FK_CUSTOMER_ID, DATE_OF_ORDER, TIME_OF_ORDER)" + " VALUES (" + orderID + ", " + customerID + ", '" + orderDate + "', '" + orderTime + "')";
    statement.executeUpdate(query);
}

public void CreateStaffOrder(int orderID,int staffID, String orderDate, String orderTime) throws SQLException 
{
    String query = "INSERT INTO USERDB.Orders (ORDER_ID,FK_STAFF_ID, DATE_OF_ORDER, TIME_OF_ORDER)" + " VALUES (" + orderID + ", " + staffID + ", '" + orderDate + "', '" + orderTime + "')";
    statement.executeUpdate(query);
}

public void CreateGuestOrder(int orderID, int guestID, String orderDate, String orderTime) throws SQLException 
{
    String query = "INSERT INTO USERDB.Orders (ORDER_ID,GUEST_ID, DATE_OF_ORDER, TIME_OF_ORDER)" + " VALUES (" + orderID + ", " + guestID + ", '" + orderDate + "', '" + orderTime + "')";
     System.out.println("creating guest");
    statement.executeUpdate(query);
}

public int GenerateNewGuestID() throws SQLException 
{
    //String query = "SELECT ORDER_ID FROM USERDB.ORDER_PRODUCT";
   // ResultSet rs = statement.executeQuery(query);
    int value = 0;
    Random random = new Random();
    value = random.nextInt(9999);
    return value;
}


public int GenerateNewOrderID() throws SQLException 
{
    String query = "SELECT ORDER_ID FROM USERDB.ORDERS";
    ResultSet rs = statement.executeQuery(query);
    int count = 0;
    
    while(rs.next())
        count++;
    
    return count + 1; 
}

public ArrayList<Order> GetCustomerOrdersByDate(int customerID, String date) throws SQLException
{
     String query = "SELECT * FROM USERDB.ORDERS WHERE FK_CUSTOMER_ID= " + customerID + " AND DATE_OF_ORDER='" + date + "'";
    ResultSet rs = statement.executeQuery(query);
    ArrayList<Order> orders = new ArrayList();

    while (rs.next()) {
        int orderID = rs.getInt(1);
        int queriedCustomerID = rs.getInt(2);
        String time = rs.getString(6);
        String delivery = rs.getString(7);
        orders.add(new Order(orderID,queriedCustomerID,date,time,delivery));
    }

    return orders;
}


public ArrayList<Order> GetStaffOrdersByDate(int staffID, String date) throws SQLException
{
     String query = "SELECT * FROM USERDB.ORDERS WHERE FK_STAFF_ID= " + staffID + " AND DATE_OF_ORDER='" + date + "'";
    ResultSet rs = statement.executeQuery(query);
    ArrayList<Order> orders = new ArrayList();

    while (rs.next()) {
        int orderID = rs.getInt(1);
        String time = rs.getString(6);
        String delivery = rs.getString(7);
        Order order = new Order(orderID);
        order.SetStaffID(staffID);
        order.SetDate(date);
        order.SetTime(time);
        order.SetAddress(delivery);
        orders.add(order);
    }

    return orders;
}


public ArrayList<Order> GetGuestOrdersByDate(int guestID, String date) throws SQLException
{
     String query = "SELECT * FROM USERDB.ORDERS WHERE GUEST_ID= " + guestID + " AND DATE_OF_ORDER='" + date + "'";
    ResultSet rs = statement.executeQuery(query);
    ArrayList<Order> orders = new ArrayList();

    while (rs.next()) {
        int orderID = rs.getInt(1);
        String time = rs.getString(6);
        String delivery = rs.getString(7);
        Order order = new Order(orderID);
        order.SetGuestID(guestID);
        order.SetDate(date);
        order.SetTime(time);
        order.SetAddress(delivery);
        orders.add(order);
    }

    return orders;
}

public ArrayList<Order> GetAllCustomerOrders(int customerID) throws SQLException
{
      String query = "SELECT * FROM USERDB.ORDERS WHERE FK_CUSTOMER_ID= " + customerID;
    ResultSet rs = statement.executeQuery(query);
    ArrayList<Order> orders = new ArrayList();

    while (rs.next()) {
        int orderID = rs.getInt(1);
        int queriedCustomerID = rs.getInt(2);
        String date = rs.getString(5);
        String time = rs.getString(6);
        String delivery = rs.getString(7);

        orders.add(new Order(orderID,queriedCustomerID,date,time,delivery));
    }

    return orders;
}



public ArrayList<Order> GetAllStaffOrders(int staffID) throws SQLException
{
      String query = "SELECT * FROM USERDB.ORDERS WHERE FK_STAFF_ID= " + staffID;
    ResultSet rs = statement.executeQuery(query);
    ArrayList<Order> orders = new ArrayList();

    while (rs.next()) {
        int orderID = rs.getInt(1);
        int queriedCustomerID = rs.getInt(2);
        String date = rs.getString(5);
        String time = rs.getString(6);
        String delivery = rs.getString(7);

        orders.add(new Order(orderID,queriedCustomerID,date,time,delivery));
    }

    return orders;
}


public ArrayList<Order> GetAllGuestOrders(int guestID) throws SQLException
{
      String query = "SELECT * FROM USERDB.ORDERS WHERE GUEST_ID= " + guestID;
    ResultSet rs = statement.executeQuery(query);
    ArrayList<Order> orders = new ArrayList();

    while (rs.next()) {
        int orderID = rs.getInt(1);
        int queriedCustomerID = rs.getInt(2);
        String date = rs.getString(5);
        String time = rs.getString(6);
        String delivery = rs.getString(7);

        orders.add(new Order(orderID,queriedCustomerID,date,time,delivery));
    }

    return orders;
}

public void UpdateOrder(int orderID, String orderDate, String orderTime, String deliveryAddress) throws SQLException {        
    String query = "UPDATE USERDB.ORDERS SET DATE_OF_ORDER = '" + orderDate + "', TIME_OF_ORDER='" + orderTime +"', DELIVERY_ADDRESS='" +deliveryAddress + "' WHERE ORDER_ID=" + orderID;
    statement.executeUpdate(query);

}



public void SubmitOrder(int orderID) throws SQLException
{
    String update = "UPDATE USERDB.ORDERS SET SUBMITTEDSTATUS= " + 1  + " WHERE ORDER_ID=" + orderID;
    statement.executeUpdate(update);
}

private ArrayList<OrderProduct> GetAllProductsIDsFromOrder(int orderID) throws SQLException
{
      String query = "SELECT * FROM USERDB.ORDER_PRODUCT WHERE ORDER_ID= " + orderID;
    ResultSet rs = statement.executeQuery(query);
    ArrayList<OrderProduct> ids = new ArrayList();

        while (rs.next()) {
        int productID = rs.getInt(2);
        int quantity = rs.getInt(3);
        ids.add(new OrderProduct(orderID,productID,quantity));
     
        }
        
        return ids;
}

public void RemoveProductFromOrder(int orderID, int productID) throws SQLException
{
      String query = "SELECT * FROM USERDB.ORDER_PRODUCT WHERE PRODUCT_ID=" + productID + " AND ORDER_ID=" + orderID;
        ResultSet rs = statement.executeQuery(query);
           int oID ;
            int pID;
            int quantity = -1;
            
        while(rs.next())
        {
         oID = rs.getInt(1);
         pID = rs.getInt(2);
         quantity = rs.getInt(3);
        }
        
        System.out.println("new quantity " + (quantity - 1) );
        
        if ((quantity - 1) <= 0)
        {
            String query2 = "DELETE FROM USERDB.ORDER_PRODUCT WHERE PRODUCT_ID=" + productID + " AND ORDER_ID=" + orderID;
           statement.executeUpdate(query2);
        //String query = "DELETE FROM USERDB.Customers WHERE ID= " + customerID ;
        }  else 
        {
        String query2 = "UPDATE USERDB.ORDER_PRODUCT SET QUANTITY = " + (quantity - 1) + " WHERE ORDER_ID=" + orderID + " AND PRODUCT_ID=" + productID;
           statement.executeUpdate(query2);
        }
            
        
    }


public ArrayList<Product> GetAllProductsFromOrder(int orderID) throws SQLException {        
    ArrayList<OrderProduct> productIDs = GetAllProductsIDsFromOrder(orderID);
    ArrayList<Product> products = new ArrayList();
    
    for(int i = 0; i < productIDs.size(); i++)
    {
        for(int j = 0; j < productIDs.get(i).GetQuantity(); j++)
        {
            String query = "SELECT * FROM USERDB.PRODUCTS WHERE PRODUCT_ID=" + productIDs.get(i).GetProductID();
            ResultSet rs = statement.executeQuery(query);

            int productID;
            String productName;
            double productCost;
            int productStock;
            String productDeliveryDate;
            while(rs.next())
            {
                productID = rs.getInt(1);
                productName=  rs.getString(2);
                productCost = rs.getDouble(3);
                productStock = rs.getInt(4);
                productDeliveryDate = rs.getString(5);
                products.add(new Product(productID,productName,productCost,productStock,productDeliveryDate));
            }
        }
    }

    return products;

}



public void AddProductToOrder(int orderID, int productID) throws SQLException
{
    String query = "SELECT QUANTITY FROM USERDB.ORDER_PRODUCT WHERE ORDER_ID=" + orderID + " AND PRODUCT_ID=" + productID;
    ResultSet set = statement.executeQuery(query);
    boolean foundSet = false;
    int quantity = 1;
    if(set.next())
    {
        System.out.println("next" + set.getInt(1));
        quantity = set.getInt(1);
        foundSet = true;
    }

                String updatedQuery = "";
                if(!foundSet)
                {
                    updatedQuery = "INSERT INTO USERDB.ORDER_PRODUCT (ORDER_ID, PRODUCT_ID, QUANTITY)" + " VALUES (" + orderID + ", " + productID +  ", " + quantity +  ")";
                } else 
                {
                    updatedQuery = "UPDATE USERDB.ORDER_PRODUCT SET QUANTITY= " + (quantity + 1) + " WHERE ORDER_ID= " + orderID + " AND PRODUCT_ID= " + productID;
                }
                
            statement.executeUpdate(updatedQuery);
}

public void UpdateProductsInOrder(int orderID, ArrayList<Product> productIDs) throws SQLException
{
    System.out.println("UPDATE PROR");
     String updateQuery = "DELETE FROM USERDB.ORDER_PRODUCT WHERE ORDER_ID=" + orderID;
    statement.executeUpdate(updateQuery);
        for (Product product : productIDs) 
        {
            AddProductToOrder(orderID,product.getProduct_ID());
        }
    
}

public Order GetOrderFromCustomer(int orderID, int customerID ) throws SQLException
{
    String query = "SELECT * FROM USERDB.ORDERS WHERE ORDER_ID=" + orderID + " AND FK_CUSTOMER_ID=" + customerID;
    ResultSet rs = statement.executeQuery(query);
    
    if(rs.next())
    {
        String date = rs.getString(5);
        String time = rs.getString(6);
        String deliveryAddress = rs.getString(7);
        Order order =  new Order(orderID);
        order.SetCustomerID(customerID);
        order.SetTime(time);
        order.SetDate(date);
        order.SetAddress(deliveryAddress);
        return order;
    }
    return null;
}

public Order GetOrderStaff(int orderID, int staffID ) throws SQLException
{
    String query = "SELECT * FROM USERDB.ORDERS WHERE ORDER_ID=" + orderID + " AND FK_STAFF_ID=" + staffID;
    ResultSet rs = statement.executeQuery(query);
    
    if(rs.next())
    {
        String date = rs.getString(5);
        String time = rs.getString(6);
        String deliveryAddress = rs.getString(7);
        Order order =  new Order(orderID);
        order.SetCustomerID(staffID);
        order.SetTime(time);
        order.SetDate(date);
        order.SetAddress(deliveryAddress);
        return order;
    }
    return null;
}

public Order GetOrderFromGuest(int orderID, int guestID ) throws SQLException
{
    String query = "SELECT * FROM USERDB.ORDERS WHERE ORDER_ID=" + orderID + " AND GUEST_ID=" + guestID;
    ResultSet rs = statement.executeQuery(query);
    
    if(rs.next())
    {
        String date = rs.getString(5);
        String time = rs.getString(6);
        String deliveryAddress = rs.getString(7);
        Order order =  new Order(orderID);
        order.SetCustomerID(guestID);
        order.SetTime(time);
        order.SetDate(date);
        order.SetAddress(deliveryAddress);
        return order;
    }
    return null;
}


private ArrayList<Product> ShowAllOrders(String query) throws SQLException {
    ResultSet rs = statement.executeQuery(query);
    ArrayList<Product> inventory = new ArrayList();
        System.out.println("s");

    while (rs.next()) {
        int item_productid = rs.getInt(1);
        String item_productname = rs.getString(2);
        double item_price = rs.getDouble(3);
        int item_stock = rs.getInt(4);
        String item_deliveryDate = rs.getString(5);
        System.out.println(item_productname);

        inventory.add(new Product(item_productid, item_productname, item_price, item_stock, item_deliveryDate));
    }

    return inventory;
}


public void decreaseStock(int productid, int quantity) throws SQLException {
    String query = "UPDATE USERDB.PRODUCTS SET STOCK = (SELECT STOCK FROM ISDUSER.PRODUCT WHERE PRODUCT_ID = "+productid+") - "+quantity+" WHERE PRODUCT_ID = "+productid+"";
    statement.executeUpdate(query);
}
        
    

 

}