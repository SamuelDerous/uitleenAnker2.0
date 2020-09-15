/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import databank.TblUitleen;
import databank.dao.UitleenDao;

/**
 *
 * @author zenodotus
 */
public class Uitlening implements Action {
    
    private int uitleen;
    private TblUitleen uitlening;

    public int getUitleen() {
        return uitleen;
    }

    public void setUitleen(int uitleen) {
        this.uitleen = uitleen;
    }

    public TblUitleen getUitlening() {
        return uitlening;
    }

    public void setUitlening(TblUitleen uitlening) {
        this.uitlening = uitlening;
    }
    
    
    
    @Override
    public String execute() {
        UitleenDao uitleenDao = new UitleenDao();
        uitlening = uitleenDao.getUitleningen(uitleen);
        return SUCCESS;
    }
    
    
    
}
