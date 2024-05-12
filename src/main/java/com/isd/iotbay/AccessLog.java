package com.isd.iotbay;

/**
 *
 * @author Owner
 */
public class AccessLog 
{
    private int _logID;
    private int _customerID;
    private String _loginDate;
    private String _loginTime;
    private String _logoutDate;
    private String _logoutTime;
    
    public AccessLog(int logID, int customerID, String loginDate, String loginTime)
    {
        _logID = logID;
        _customerID = customerID;
        _loginDate = loginDate;
        _loginTime = loginTime;
    }
    
       public AccessLog(int logID, int customerID, String loginDate, String loginTime, String logoutDate, String logoutTime)
    {
        _logID = logID;
        _customerID = customerID;
        _loginDate = loginDate;
        _loginTime = loginTime;
        _logoutDate = logoutDate;
        _logoutTime = logoutTime;
    }
    
    public int GetLogID() {return _logID;}
    public int GetCustomerID(){return _customerID;}
    public String GetLoginDate(){return _loginDate;}
    public String GetLoginTime(){return _loginTime;}
    public String GetLogoutDate(){return _logoutDate;}
    public String GetLogoutTime(){return _logoutTime;}
    
}
