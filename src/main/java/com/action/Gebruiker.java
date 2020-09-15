/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.Action;
import static com.opensymphony.xwork2.Action.INPUT;
import databank.TblPersoon;
import databank.dao.PersoonDao;

/**
 *
 * @author zenodotus
 */
public class Gebruiker implements Action {
    private TblPersoon gebruiker;
    private String gebruikersnaam;

    public TblPersoon getGebruiker() {
        return gebruiker;
    }

    public void setGebruiker(TblPersoon gebruiker) {
        this.gebruiker = gebruiker;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    @Override
    public String execute() throws Exception {
        PersoonDao persoonDao = new PersoonDao();
        gebruiker = persoonDao.getGebruiker(gebruikersnaam);
        if(gebruiker != null) {
            return SUCCESS;
        } else {
            return LOGIN;
        }
    }
    
    
}
