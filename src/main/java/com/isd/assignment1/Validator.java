/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isd.assignment1;

/**
 *
 * @author Owner
 */
public class Validator 
{
    public boolean IsValidLogin(){return true;}

    public boolean IsValidConfirmPassword(String password, String confirmedPassword)
    {
        return password.equals(confirmedPassword);
    }



}
