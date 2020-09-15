/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import databank.dao.InventarisDao;

/**
 *
 * @author zenodotus
 */
public class InventVerwijderen implements Action {

    private int invent;

    public int getInvent() {
        return invent;
    }

    public void setInvent(int invent) {
        this.invent = invent;
    }
    
    
    @Override
    public String execute() throws Exception {
        InventarisDao inventDao = new InventarisDao();
        if(inventDao.verwijderInvent(invent) != -1) {
            return SUCCESS;
        } else {
            return ERROR;
        }
    }
    
    
}
