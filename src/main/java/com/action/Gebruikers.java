/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import databank.TblPersoon;
import databank.dao.PersoonDao;
import java.util.List;

/**
 *
 * @author zenodotus
 */
public class Gebruikers extends ActionSupport {
    
    private List<TblPersoon> gebruikers;
    
    public String execute() {
        PersoonDao dao = new PersoonDao();
        gebruikers = dao.getAlleGebruikers();
        return SUCCESS;
    }

    public List<TblPersoon> getGebruikers() {
        return gebruikers;
    }

    public void setGebruikers(List<TblPersoon> gebruikers) {
        this.gebruikers = gebruikers;
    }
    
    
}
