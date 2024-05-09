package com.isd.iotbay.controller;
import java.io.Serializable;
import javax.servlet.http.HttpSession;

public class Validator implements Serializable 
{
    public boolean ValidateEmail(String email)
    {
        if(CheckIfFieldIsEmpty(email))
            return false;
      
        int atSymbol = email.indexOf("@");
        int dotSymbol = email.lastIndexOf(".");
        int spaceSymbol = email.indexOf(" ");
        return atSymbol != -1 && atSymbol != 0 && dotSymbol != -1 && dotSymbol != 0 && spaceSymbol == -1 && email.length() > dotSymbol + 1;
    }
    
    public boolean CheckIfFieldIsEmpty(String field)
    {
        return field.isEmpty();
    }
    
    public boolean ValidatePassword(String password)
    {
        int spaceSymbol = password.indexOf(" "); 
        return spaceSymbol == -1;
    }
    
    public boolean ValidateName(String givenName, String surname)
    {
        int spaceSymbolGivenName = givenName.indexOf(" "); 
        int spaceSymbolSurname = surname.indexOf(" ");
        return spaceSymbolGivenName == -1 &&  spaceSymbolSurname == -1;
    }
    
    public void ClearErrors(HttpSession currentSession)
    {
        currentSession.setAttribute("emailError",null) ;
        currentSession.setAttribute("passwordError",null);
        currentSession.setAttribute("loginError",null);
    }
    
}
