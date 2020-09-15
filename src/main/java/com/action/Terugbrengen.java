/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import databank.TblUitleen;
import databank.dao.ProductDao;
import databank.dao.UitleenDao;
import java.util.GregorianCalendar;

/**
 *
 * @author zenodotus
 */
public class Terugbrengen extends ActionSupport {

    private int uitleen;

    public int getUitleen() {
        return uitleen;
    }

    public void setUitleen(int uitleen) {
        this.uitleen = uitleen;
    }
    
    @Override
    public String execute() throws Exception {
        GregorianCalendar cal = new GregorianCalendar();
        
        UitleenDao uitleenDao = new UitleenDao();
        TblUitleen uitlening = uitleenDao.getUitleningen(uitleen);
        uitlening.setTeruggebracht(cal.getTime());
        uitlening.setControle(0);
        uitleenDao.aanpassen(uitlening);
        ProductDao productDao = new ProductDao();
        uitlening.getSpel().setControle(1);
        productDao.aanpassen(uitlening.getSpel());
        addActionError("Controle is " + uitlening.getSpel().getControle());
        return SUCCESS;
    }
    
    
}
