/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package creatie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author zenodotus
 */
public class Password {
    
    private static List<Integer> range = new ArrayList<>();
    //private String randomPass = "";
    
    public static String createRandomPassword() {
        String randomPass = "";
        addRange(33, 57);
        addRange(64, 90);
        addRange(97,122);
        for(int i = 0; i < 8; i++) {
            int randomNum = range.get(new Random().nextInt(range.size()));
            randomPass += ((char) randomNum);
        }
        return randomPass;
        
    }
    
    static void addRange(int min, int max) {
        for (int i = min; i <= max; i++) {
            range.add(i);
        }
    }
}
