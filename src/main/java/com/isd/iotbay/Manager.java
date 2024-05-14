/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.isd.iotbay;

/**
 *
 * @author Owner
 */
import java.util.List;


public class Manager 
{

    private int _id;
    private String _name;
    private int _hoursWorked;
    private List<Staff> _employees;
    
    public Manager(int id, String name)
    {
        _id = id;
        _name = name;
    }

}
