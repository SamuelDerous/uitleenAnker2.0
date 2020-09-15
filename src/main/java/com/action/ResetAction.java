/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import crypto.EncryptionIni;
import databank.dao.PersoonDao;

/**
 *
 * @author zenodotus
 */
public class ResetAction extends ActionSupport {
    
    private String gebruikersnaam;
    private String wachtwoord;
    private String bevestig;

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getBevestig() {
        return bevestig;
    }

    public void setBevestig(String bevestig) {
        this.bevestig = bevestig;
    }
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    @Override
    public void validate() {
        boolean correct = true;
            if(wachtwoord == null || wachtwoord.equals("") || !wachtwoord.equals(bevestig)) {
                correct = false;
                addActionError("De beide wachtwoorden komen niet overeen.");
            }
            if(correct == true) {
                try {
                    wachtwoord = EncryptionIni.encrypt(wachtwoord);
                    PersoonDao persoonDao = new PersoonDao();
                    persoonDao.aanpassenWachtwoord(gebruikersnaam, wachtwoord);
                } catch(Exception ex) {
                    addActionError("Er is een fout opgetreden bij het verwerken van de aanvraag.");
                    ex.printStackTrace();
                }
            }
    }
}
