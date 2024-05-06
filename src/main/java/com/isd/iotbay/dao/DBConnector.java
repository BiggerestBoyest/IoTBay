package com.isd.iotbay.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

 

public class DBConnector extends DB
{

    public DBConnector() throws ClassNotFoundException, SQLException 
    {
        Class.forName(driver);
        con = DriverManager.getConnection(URL+userDB, dbUsername, dbPassword);
    }



    public Connection OpenConnection()
    {
        return this.con;
    }



    public void CloseConnection() throws SQLException 
    {
        this.con.close();
    }

}
