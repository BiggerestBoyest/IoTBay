package com.isd.iotbay.dao;

import java.sql.Connection;


public class DB 
{
    protected String URL = "jdbc:derby://localhost:1527/";//replace this string with your jdbc:derby local host url   
    protected String userDB = "usersdb";//name of the database   
    protected String dbUsername = "userdb";//db root user   
    protected String dbPassword = "admin"; //db root password   
    protected String driver = "org.apache.derby.jdbc.ClientDriver"; //jdbc client driver - built in with NetBeans   
    protected Connection con; //connection null-instance to be initialized in sub-classes
}
