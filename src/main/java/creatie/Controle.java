/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatie;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author zenodotus
 */
public class Controle {

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isInteger(String str) {
        try {
           int i = Integer.parseInt(str);
        } catch(NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isDate(String str) {
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date d = (Date) formatter.parse(str);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}
