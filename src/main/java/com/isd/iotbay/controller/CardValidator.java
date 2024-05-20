package com.isd.iotbay.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author thean
 */
public class CardValidator {

    public static String validate(String nameOnCard, String cardNum, String expiryDate, String cvs) {
        StringBuilder errorMessage = new StringBuilder();

        if (nameOnCard == null || nameOnCard.trim().isEmpty()) {
            errorMessage.append("Name on card is required. ");
        }

        if (cardNum == null || cardNum.trim().isEmpty()) {
            errorMessage.append("Card number is required. ");
        } else if (!cardNum.matches("\\d{16}")) {
            errorMessage.append("Card number must be 16 digits. ");
        }

        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            errorMessage.append("Expiry date is required. ");
        }

        if (cvs == null || cvs.trim().isEmpty()) {
            errorMessage.append("CVS is required. ");
        } else if (!cvs.matches("\\d{3}")) {
            errorMessage.append("CVS must be 3 digits. ");
        }

        return errorMessage.toString();
    }
}
