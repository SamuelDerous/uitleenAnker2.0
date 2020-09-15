/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.action;

import com.opensymphony.xwork2.ActionSupport;
import crypto.EncryptionIni;
import databank.TblPersoon;
import databank.dao.PersoonDao;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author zenodotus
 */
public class LoginAction extends ActionSupport {
    
    private static final long serialVersionUID = 6677091252031583948L;
    
    private String gebruikersnaam;
    private String wachtwoord;

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

    @Override
    public void validate() {
        try {
            String decrWachtwoord = EncryptionIni.encrypt(wachtwoord);
            PersoonDao dao = new PersoonDao();
            List<TblPersoon> personen = dao.login(gebruikersnaam, decrWachtwoord);
            HttpSession sessie = ServletActionContext.getRequest().getSession();
            if(!personen.isEmpty()) {
                 sessie.setAttribute("gebruikersnaam", gebruikersnaam);
                 sessie.setAttribute("soort", personen.get(0).getSoort().getSoort());
                 addActionMessage("Welkom " + gebruikersnaam);
                
            } else {
                sessie.setAttribute("gebruikersnaam", "");
                addActionError("Gebruikersnaam of wachtwoord is niet gekend.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
    
    
    
    
}
