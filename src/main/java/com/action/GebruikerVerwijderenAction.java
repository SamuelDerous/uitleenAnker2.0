/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import databank.dao.PersoonDao;

/**
 *
 * @author zenodotus
 */
public class GebruikerVerwijderenAction extends ActionSupport {
    
    String gebruikersnaam;

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }
    
    

    @Override
    public String execute() throws Exception {
        PersoonDao dao = new PersoonDao();
        if(dao.verwijderGebruiker(gebruikersnaam) != -1) {
            return SUCCESS;
        } else {
            addActionError("Er is iets fout gegaan bij het verwijderen van de gebruiker " + gebruikersnaam);
            return ERROR;
        }
        
    }
    
    
}
