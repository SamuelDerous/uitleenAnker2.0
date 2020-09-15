/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import databank.TblUitleen;
import databank.dao.UitleenDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

/**
 *
 * @author zenodotus
 */
public class Verlengen implements Action {
    private int uitleen;

    public int getUitleen() {
        return uitleen;
    }

    public void setUitleen(int uitleen) {
        this.uitleen = uitleen;
    }

    @Override
    public String execute() throws Exception {
        UitleenDao uitleenDao = new UitleenDao();
        TblUitleen uitlening = uitleenDao.getUitleningen(uitleen);
        
        GregorianCalendar cal = new GregorianCalendar();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if(uitlening.getSpel().getUitleentermijn() != null && !uitlening.getSpel().getUitleentermijn().equals("")) {
            cal.add(GregorianCalendar.DAY_OF_YEAR, (uitlening.getSpel().getUitleentermijn() * 7));
        } else {
            cal.add(GregorianCalendar.DAY_OF_YEAR, 28);
        }
        
        uitlening.setTerugbrengdatum(cal.getTime());
        uitleenDao.aanpassen(uitlening);
        return SUCCESS;
    }
    
    
}
